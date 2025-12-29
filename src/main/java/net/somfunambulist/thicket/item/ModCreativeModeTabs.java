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
import net.somfunambulist.thicket.block.compat.WindsweptBlocks;

import static net.somfunambulist.thicket.ModCompat.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Thicket.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SETUP_TAB = CREATIVE_MODE_TABS.register("thicket_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HAZELNUT.get()))
                    .title(Component.translatable("creativetab.thicket_tab"))
                    .displayItems(((displayParameters, output) -> {
                        //tools
                        output.accept(ModItems.POCKET_KNIFE.get());

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
                        //myrrh=========================================================================================
                        output.accept(ModBlocks.MYRRH_SAPLING.get());
                        output.accept(ModBlocks.MYRRH_LEAVES.get());
                        output.accept(ModBlocks.MYRRH_LOG.get());
                        output.accept(ModBlocks.MYRRH_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_MYRRH_LOG.get());
                        output.accept(ModBlocks.STRIPPED_MYRRH_WOOD.get());
                        output.accept(ModBlocks.MYRRH_PLANKS.get());
                        output.accept(ModBlocks.MYRRH_CARVING.get());
                        output.accept(ModBlocks.MYRRH_SLAB.get());
                        output.accept(ModBlocks.MYRRH_STAIRS.get());
                        output.accept(ModBlocks.MYRRH_BUTTON.get());
                        output.accept(ModBlocks.MYRRH_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.MYRRH_FENCE.get());
                        output.accept(ModBlocks.MYRRH_FENCE_GATE.get());
                        output.accept(ModBlocks.MYRRH_DOOR.get());
                        output.accept(ModBlocks.MYRRH_TRAPDOOR.get());
                        output.accept(ModItems.MYRRH_SIGN.get());
                        output.accept(ModItems.MYRRH_HANGING_SIGN.get());
                        output.accept(ModItems.MYRRH_BOAT.get());
                        output.accept(ModItems.MYRRH_CHEST_BOAT.get());
                        output.accept(ModItems.MYRRH_RESIN.get());
                        output.accept(ModBlocks.MYRRH_BUNDLE.get());
                        //boswellia=========================================================================================
                        output.accept(ModBlocks.BOSWELLIA_SAPLING.get());
                        output.accept(ModBlocks.BOSWELLIA_LEAVES.get());
                        output.accept(ModBlocks.BOSWELLIA_LOG.get());
                        output.accept(ModBlocks.BOSWELLIA_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_BOSWELLIA_LOG.get());
                        output.accept(ModBlocks.STRIPPED_BOSWELLIA_WOOD.get());
                        output.accept(ModBlocks.BOSWELLIA_PLANKS.get());
                        output.accept(ModBlocks.BOSWELLIA_CARVING.get());
                        output.accept(ModBlocks.BOSWELLIA_SLAB.get());
                        output.accept(ModBlocks.BOSWELLIA_STAIRS.get());
                        output.accept(ModBlocks.BOSWELLIA_BUTTON.get());
                        output.accept(ModBlocks.BOSWELLIA_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.BOSWELLIA_FENCE.get());
                        output.accept(ModBlocks.BOSWELLIA_FENCE_GATE.get());
                        output.accept(ModBlocks.BOSWELLIA_DOOR.get());
                        output.accept(ModBlocks.BOSWELLIA_TRAPDOOR.get());
                        output.accept(ModItems.BOSWELLIA_SIGN.get());
                        output.accept(ModItems.BOSWELLIA_HANGING_SIGN.get());
                        output.accept(ModItems.BOSWELLIA_BOAT.get());
                        output.accept(ModItems.BOSWELLIA_CHEST_BOAT.get());
                        output.accept(ModItems.FRANKINCENSE_RESIN.get());
                        output.accept(ModBlocks.FRANKINCENSE_BUNDLE.get());
                        //hazel supporting features
                        output.accept(ModBlocks.FIREMILK_MUSHROOM.get());
                        output.accept(ModBlocks.FIREMILK_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.DARK_FIREMILK_MUSHROOM_BLOCK.get());
                        output.accept(ModBlocks.MISTLETOE_LEAVES.get());
                        output.accept(ModBlocks.BLOOMING_MISTLETOE_LEAVES.get());
                        output.accept(ModItems.MISTLETOE.get());
                        output.accept(ModItems.ENCHANTED_MISTLETOE.get());
                        output.accept(ModItems.GOLD_SICKLE.get());
                        output.accept(ModBlocks.HELIOTROPE.get());
                        //output.accept(ModItems.APOCRYPHAL_SMITHING_TEMPLATE.get());
                        output.accept(ModItems.CRUXWOOD_PICKAXE.get());
                        output.accept(ModItems.CRUXWOOD_SHOVEL.get());
                        output.accept(ModItems.CRUXWOOD_AXE.get());
                        output.accept(ModItems.CRUXWOOD_HOE.get());
                        output.accept(ModItems.CRUXWOOD_SWORD.get());
                        output.accept(ModItems.CRUXWOOD_BOW.get());
                        //sassafras=====================================================================================
                        //output.accept(ModBlocks.SASSAFRAS_SAPLING.get());
                        //output.accept(ModBlocks.SASSAFRAS_LEAVES.get());
                        //output.accept(ModBlocks.SASSAFRAS_LOG.get());
                        //output.accept(ModBlocks.SASSAFRAS_WOOD.get());
                        //output.accept(ModBlocks.STRIPPED_SASSAFRAS_LOG.get());
                        //output.accept(ModBlocks.STRIPPED_SASSAFRAS_WOOD.get());
                        //output.accept(ModBlocks.SASSAFRAS_PLANKS.get());
                        //other blocks==================================================================================
                        //output.accept(ModBlocks.SUSPICIOUS_ROOTS.get());
                        //modded compat
                        if (ModList.get().isLoaded(DECORATIVE_BLOCKS_ID)) {
                            output.accept(DecorativeBlocksBlocks.HAZEL_BEAM.get());
                        }
                        if (ModList.get().isLoaded(ECOLOGICS_ID)) {
                            output.accept(EcologicsBlocks.WALNUT_BUNDLE.get());
                        }
                        if (ModList.get().isLoaded(WINDSWEPT_ID)) {
                            output.accept(WindsweptBlocks.CHESTNUT_CARVING.get());
                            output.accept(WindsweptBlocks.HOLLY_CARVING.get());
                            output.accept(WindsweptBlocks.PINE_CARVING.get());
                        }

                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
