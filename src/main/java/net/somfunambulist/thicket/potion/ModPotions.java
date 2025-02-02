package net.somfunambulist.thicket.potion;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.somfunambulist.thicket.Thicket;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, Thicket.MOD_ID);

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
