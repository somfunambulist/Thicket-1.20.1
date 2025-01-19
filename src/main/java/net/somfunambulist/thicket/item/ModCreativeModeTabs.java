package net.somfunambulist.thicket.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Thicket.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SETUP_TAB = CREATIVE_MODE_TABS.register("thicket_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HAZEL_NUT.get()))
                    .title(Component.translatable("creativetab.thicket_tab"))
                    .displayItems(((displayParameters, output) -> {

                        output.accept(ModItems.POCKET_KNIFE.get());
                        output.accept(ModBlocks.HAZEL_PLANKS.get());
                        output.accept(ModBlocks.HAZEL_SLAB.get());
                        output.accept(ModBlocks.HAZEL_STAIRS.get());
                        output.accept(ModBlocks.HAZEL_BUTTON.get());
                        output.accept(ModBlocks.HAZEL_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.HAZEL_FENCE.get());
                        output.accept(ModBlocks.HAZEL_FENCE_GATE.get());
                        output.accept(ModBlocks.HAZEL_DOOR.get());
                        output.accept(ModBlocks.HAZEL_TRAPDOOR.get());
                        output.accept(ModBlocks.HAZEL_LOG.get());
                        output.accept(ModBlocks.HAZEL_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_HAZEL_LOG.get());
                        output.accept(ModBlocks.STRIPPED_HAZEL_WOOD.get());
                        output.accept(ModBlocks.CARVED_HAZEL.get());
                        output.accept(ModBlocks.HAZEL_LEAVES.get());
                        output.accept(ModBlocks.HAZEL_SAPLING.get());
                        output.accept(ModItems.DOWSING_ROD.get());
                        output.accept(ModItems.HAZEL_NUT.get());

                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
