package dev.dacommander31.cmda;

import dev.dacommander31.cmda.particle.CAParticles;
import dev.dacommander31.cmda.particle.EMPStunParticle;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = CommandersArsenal.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = CommandersArsenal.MOD_ID, value = Dist.CLIENT)
public class CommandersArsenalClient {

    public CommandersArsenalClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(CAParticles.EMP_STUN.get(), EMPStunParticle.Provider::new);
    }
}
