package dev.dacommander31.cmda.event;

import net.minecraft.world.phys.Vec3;

@FunctionalInterface
public interface TrajectoryEndCallback {
    void onEnd(Vec3 finalPos, Vec3 finalVel, int totalSteps);
}
