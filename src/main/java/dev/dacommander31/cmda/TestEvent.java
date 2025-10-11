package dev.dacommander31.cmda;

import com.lowdragmc.photon.client.fx.EntityEffectExecutor;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = CommandersArsenal.MOD_ID)
public class TestEvent {
    @SubscribeEvent
    public static void onRightButton(PlayerInteractEvent.RightClickItem event) {
        CommandersArsenal.LOGGER.info("fx");
        Level level = event.getLevel();
        FX fx = FXHelper.getFX(ResourceLocation.fromNamespaceAndPath(CommandersArsenal.MOD_ID, "emp_shockwave"));
        new EntityEffectExecutor(fx, level, event.getEntity(), EntityEffectExecutor.AutoRotate.NONE);
    }
}
