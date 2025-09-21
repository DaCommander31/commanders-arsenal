package dev.dacommander31.cmda.data.trajectory;

import dev.dacommander31.cmda.data.event.TrajectoryEndCallback;
import dev.dacommander31.cmda.data.event.TrajectoryStepCallback;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TraceJob {
    private final Level level;
    private Vec3 pos;
    private Vec3 vel;
    private final Vec3 accel;
    private final int maxSteps;
    private final float timeStep;
    private final ClipContext.Fluid fluidContext;
    private final Entity shooter;
    private final TrajectoryStepCallback stepCallback;
    private final TrajectoryEndCallback endCallback;
    private int step = 0;


    protected TraceJob(Level level, Vec3 pos, Vec3 vel, Vec3 accel, int maxSteps, float timeStep, ClipContext.Fluid fluidContext, Entity shooter, TrajectoryStepCallback stepCallback, TrajectoryEndCallback endCallback) {
        this.level = level;
        this.pos = pos;
        this.vel = vel;
        this.accel = accel;
        this.maxSteps = maxSteps;
        this.timeStep = timeStep;
        this.fluidContext = fluidContext;
        this.shooter = shooter;
        this.stepCallback = stepCallback;
        this.endCallback = endCallback;
    }

    public boolean tickStep() {
        if (step >= maxSteps) {
            endCallback.onEnd(pos, vel, step);
            return false;
        }

        Vec3 nextVel = vel.add(accel.scale(timeStep));
        Vec3 nextPos = pos.add(vel.scale(timeStep));

        BlockHitResult hit = level.clip(new ClipContext(pos, nextPos,
                ClipContext.Block.OUTLINE, fluidContext, shooter));
        if (hit.getType() != HitResult.Type.MISS) {
            pos = hit.getLocation();
            endCallback.onEnd(pos, vel, step);
            return false;
        }

        boolean shouldContinue = stepCallback.onStep(nextPos, nextVel, step);

        pos = nextPos;
        vel = nextVel;
        step++;

        if (!shouldContinue) endCallback.onEnd(pos, vel, step);

        return shouldContinue && step < maxSteps;
    }


}
