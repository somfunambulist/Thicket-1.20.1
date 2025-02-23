package net.somfunambulist.thicket.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.block.ModBlocks;
import net.somfunambulist.thicket.block.compat.DecorativeBlocksBlocks;
import net.somfunambulist.thicket.block.compat.EcologicsBlocks;

import static net.somfunambulist.thicket.ModCompat.DECORATIVE_BLOCKS_ID;
import static net.somfunambulist.thicket.ModCompat.ECOLOGICS_ID;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Thicket.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SETUP_TAB = CREATIVE_MODE_TABS.register("thicket_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HAZELNUT.get()))
                    .title(Component.translatable("creativetab.thicket_tab"))
                    .displayItems(((displayParameters, output) -> {
                        //tools
                        output.accept(ModItems.POCKET_KNIFE.get());
                        output.accept(ModItems.GOLD_SICKLE.get());
                        output.accept(ModItems.APOCRYPHAL_SMITHING_TEMPLATE.get());
                        output.accept(ModItems.CRUXWOOD_PICKAXE.get());
                        output.accept(ModItems.CRUXWOOD_SHOVEL.get());
                        output.accept(ModItems.CRUXWOOD_AXE.get());
                        output.accept(ModItems.CRUXWOOD_HOE.get());
                        output.accept(ModItems.CRUXWOOD_SWORD.get());
                        output.accept(ModItems.CRUXWOOD_BOW.get());

                        //vanilla carving blocks
                        output.accept(ModBlocks.OAK_CARVING.get());
                        output.accept(ModBlocks.BIRCH_CARVING.get());
                        output.accept(ModBlocks.SPRUCE_CARVING.get());
                        output.accept(ModBlocks.JUNGLE_CARVING.get());
                        output.accept(ModBlocks.ACACIA_CARVING.get());
                        output.accept(ModBlocks.DARK_OAK_CARVING.get());
                        output.accept(ModBlocks.MANGROVE_CARVING.get());
                        output.accept(ModBlocks.CHERRY_CARVING.get());
                        output.accept(ModBlocks.CRIMSON_CARVING.get());
                        output.accept(ModBlocks.WARPED_CARVING.get());
                        //hazel=========================================================================================
                        output.accept(ModBlocks.HAZEL_SAPLING.get());
                        output.accept(ModBlocks.HAZEL_LEAVES.get());
                        output.accept(ModBlocks.HAZEL_LOG.get());
                        output.accept(ModBlocks.HAZEL_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_HAZEL_LOG.get());
                        output.accept(ModBlocks.STRIPPED_HAZEL_WOOD.get());
                        output.accept(ModBlocks.HAZEL_PLANKS.get());
                        output.accept(ModBlocks.HAZEL_CARVING.get());
                        output.accept(ModBlocks.HAZEL_SLAB.get());
                        output.accept(ModBlocks.HAZEL_STAIRS.get());
                        output.accept(ModBlocks.HAZEL_BUTTON.get());
                        output.accept(ModBlocks.HAZEL_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.HAZEL_FENCE.get());
                        output.accept(ModBlocks.HAZEL_FENCE_GATE.get());
                        output.accept(ModBlocks.HAZEL_DOOR.get());
                        output.accept(ModBlocks.HAZEL_TRAPDOOR.get());
                        output.accept(ModItems.HAZEL_SIGN.get());
                        output.accept(ModItems.HAZEL_HANGING_SIGN.get());
                        output.accept(ModItems.HAZEL_BOAT.get());
                        output.accept(ModItems.HAZEL_CHEST_BOAT.get());
                        output.accept(ModItems.HAZELNUT.get());
                        output.accept(ModBlocks.HAZELNUT_BUNDLE.get());
                        output.accept(ModItems.HAZELBUTTER.get());
                        output.accept(ModItems.HAZELBUTTER_TREAT.get());
                        output.accept(ModItems.DOWSING_ROD.get());
                        //sassafras=====================================================================================
                        output.accept(ModBlocks.SASSAFRAS_SAPLING.get());
                        output.accept(ModBlocks.SASSAFRAS_LEAVES.get());
                        output.accept(ModBlocks.SASSAFRAS_LOG.get());
                        output.accept(ModBlocks.SASSAFRAS_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_SASSAFRAS_LOG.get());
                        output.accept(ModBlocks.STRIPPED_SASSAFRAS_WOOD.get());
                        output.accept(ModBlocks.SASSAFRAS_PLANKS.get());
                        //other blocks==================================================================================
                        output.accept(ModBlocks.FIREMILK_MUSHROOM.get());
                        output.accept(ModBlocks.FIREMILK_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.DARK_FIREMILK_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.BLOOMING_MISTLETOE_LEAVES.get());
                        output.accept(ModBlocks.MISTLETOE_LEAVES.get());
                        output.accept(ModItems.MISTLETOE.get());
                        output.accept(ModBlocks.HANGING_MISTLETOE.get());
                        output.accept(ModBlocks.SUSPICIOUS_ROOTS.get());
                        //modded compat
                        if (ModList.get().isLoaded(DECORATIVE_BLOCKS_ID)) {
                            output.accept(DecorativeBlocksBlocks.HAZEL_BEAM.get());
                        }
                        if (ModList.get().isLoaded(ECOLOGICS_ID)) {
                            output.accept(EcologicsBlocks.WALNUT_BUNDLE.get());
                        }

                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
