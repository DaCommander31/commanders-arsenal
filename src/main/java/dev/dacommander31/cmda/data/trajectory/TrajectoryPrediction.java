package dev.dacommander31.cmda.data.trajectory;

import dev.dacommander31.cmda.event.TrajectoryEndCallback;
import dev.dacommander31.cmda.event.TrajectoryStepCallback;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public record TrajectoryPrediction(Vec3 startPos, Vec3 initVel, Vec3 accel, ClipContext.Fluid fluidContext) {
    // P(t) = P0 + V0t + ½at²
    public Vec3 getPointWithTime(float time) {
        Vec3 displacementFromVelocity = initVel.scale(time);
        Vec3 displacementFromAcceleration = accel.scale(0.5 * time * time);
        return startPos.add(displacementFromVelocity).add(displacementFromAcceleration);
    }

    public Vec3 getEndpoint(Level level, Entity contextEntity, int maxSteps, float timeStep) {
        Vec3 currentPos = startPos;
        Vec3 vel = initVel;

        for (int i = 0; i < maxSteps; i++) {
            Vec3 nextVel = vel.add(accel.scale(timeStep));
            Vec3 nextPos = currentPos.add(vel.scale(timeStep));

            ClipContext context = new ClipContext(currentPos, nextPos, ClipContext.Block.COLLIDER, fluidContext, contextEntity);

            HitResult result = level.clip(context);

            if (result.getType() != HitResult.Type.MISS) {
                return result.getLocation();
            }

            currentPos = nextPos;
            vel = nextVel;
        }

        return currentPos;
    }

    public void trace(Level level, Entity contextEntity, int maxSteps, float timeStep, TrajectoryStepCallback stepCallback, TrajectoryEndCallback endCallback) {
        Vec3 pos = startPos;
        Vec3 vel = initVel;

        for (int i = 0; i < maxSteps; i++) {
            Vec3 nextVelocity = vel.add(accel.scale(timeStep));
            Vec3 nextPosition = pos.add(vel.scale(timeStep));

            ClipContext context = new ClipContext(pos, nextPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, contextEntity);
            HitResult hit = level.clip(context);

            if (hit.getType() != HitResult.Type.MISS) {
                endCallback.onEnd(pos, vel, i);
                break;
            }

            boolean shouldContinue = stepCallback.onStep(pos, vel, i);
            if (!shouldContinue) {
                endCallback.onEnd(pos, vel, i);
                break;
            };

            pos = nextPosition;
            vel = nextVelocity;
        }


    }

}