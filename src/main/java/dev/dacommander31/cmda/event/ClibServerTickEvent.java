package dev.dacommander31.cmda.event;

import dev.dacommander31.cmda.CommandersArsenal;
import dev.dacommander31.cmda.data.trajectory.TrajectoryTraceManager;
import dev.dacommander31.util.ClibScheduler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = CommandersArsenal.MOD_ID)
public class ClibServerTickEvent {


    @SubscribeEvent
    public static void onTick(ServerTickEvent.Pre event) {
        ClibScheduler.tick();
        TrajectoryTraceManager.tick();
    }

}
