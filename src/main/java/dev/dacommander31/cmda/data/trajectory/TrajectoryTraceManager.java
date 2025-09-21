package dev.dacommander31.cmda.data.trajectory;

import dev.dacommander31.cmda.event.TrajectoryEndCallback;
import dev.dacommander31.cmda.event.TrajectoryStepCallback;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class TrajectoryTraceManager {
    private static final List<TraceJob> activeTraces = new ArrayList<>();

    public static void startTrace(Level level, TrajectoryPrediction prediction, int maxSteps, float timeStep,
                                  Entity shooter, TrajectoryStepCallback stepCallback, TrajectoryEndCallback endCallback) {
        activeTraces.add(new TraceJob(level, prediction.startPos(), prediction.initVel(), prediction.accel(), maxSteps, timeStep, prediction.fluidContext(), shooter, stepCallback, endCallback));
    }

    public static void tick() {
        activeTraces.removeIf(traceJob -> !traceJob.tickStep());
    }
}
