package dev.dacommander31.cmda.item;

import dev.dacommander31.cmda.CommandersArsenal;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CAItems {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(CommandersArsenal.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
