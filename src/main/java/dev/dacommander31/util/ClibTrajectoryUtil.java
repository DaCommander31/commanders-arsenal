package dev.dacommander31.util;


import dev.dacommander31.cmda.data.trajectory.TrajectoryPrediction;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.Vec3;

public class ClibTrajectoryUtil {
    public static final Vec3 DEFAULT_ACCEL = new Vec3(0f, -1, 0);
    public static final float DEFAULT_TIME_STEP = 1f / 20f;

    public static TrajectoryPrediction createPrediction(Vec3 startPos, Vec3 initVel, Vec3 accel, ClipContext.Fluid fluidContext) {
        return new TrajectoryPrediction(startPos, initVel, accel, fluidContext);
    }

    public static TrajectoryPrediction createPrediction(Vec3 startPos, Vec3 initVel, Vec3 accel) {
        return new TrajectoryPrediction(startPos, initVel, accel, ClipContext.Fluid.NONE);
    }

}
