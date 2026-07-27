package net.somfunambulist.thicket.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.somfunambulist.thicket.Thicket;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REG = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Thicket.MOD_ID);

    public static final Supplier<CreativeModeTab> THICKET_TAB = REG.register("thicket_tab",
            () -> CreativeModeTab.builder()
                    .icon(ModItems.POCKET_KNIFE::toStack) //TODO should be Hazelnut later
                    .title(Component.translatable("thicket.itemGroup"))
                    .displayItems((params, output) -> {
                        ModItems.REG.getEntries().forEach(holder -> output.accept(holder.get()));
                    })
                    .build()
    );
}
