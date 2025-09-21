package dev.dacommander31.cmda.data.tweening;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderFrameEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClibTweenManager {
    private static final List<ClibTween<?>> TWEENS = new CopyOnWriteArrayList<>();

    public static void add(ClibTween<?> tween) {
        TWEENS.add(tween);
    }

    public static void tick() {
        TWEENS.removeIf(ClibTween::tick);
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post e) {
        tick();
    }

    @SubscribeEvent
    public static void onRender(RenderFrameEvent.Post e) {
        float pt = e.getPartialTick().getGameTimeDeltaPartialTick(true);
        TWEENS.forEach(tween -> {
            if (tween.usesRealtimeInterp()) tween.renderLerp(pt);
        });
    }
}
