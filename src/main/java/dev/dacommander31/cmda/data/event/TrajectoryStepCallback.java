package dev.dacommander31.cmda.data.event;

import net.minecraft.world.phys.Vec3;

@FunctionalInterface
public interface TrajectoryStepCallback {
    boolean onStep(Vec3 pos, Vec3 vel, int tick);
}
