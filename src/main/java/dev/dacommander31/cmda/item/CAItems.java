package dev.dacommander31.cmda.item;

import dev.dacommander31.cmda.CommandersArsenal;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CAItems {
    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(CommandersArsenal.MOD_ID);
    
    public static DeferredItem<Item> HEART_OF_THE_WARDEN = ITEMS.registerSimpleItem("heart_of_the_warden");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
