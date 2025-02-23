package net.somfunambulist.thicket.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.block.custom.*;
import net.somfunambulist.thicket.item.ModItems;
import net.somfunambulist.thicket.util.ModWoodTypes;
import net.somfunambulist.thicket.worldgen.ModFeatures;
import net.somfunambulist.thicket.worldgen.tree.HazelTreeGrower;
import net.somfunambulist.thicket.worldgen.tree.SassafrasTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Thicket.MOD_ID);

    //ADD BLOCKS HERE===================================================================================================

    public static final RegistryObject<Block> OAK_CARVING = registerBlock("oak_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> BIRCH_CARVING = registerBlock("birch_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_BIRCH_WOOD)));
    public static final RegistryObject<Block> SPRUCE_CARVING = registerBlock("spruce_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_SPRUCE_WOOD)));
    public static final RegistryObject<Block> JUNGLE_CARVING = registerBlock("jungle_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_JUNGLE_WOOD)));
    public static final RegistryObject<Block> ACACIA_CARVING = registerBlock("acacia_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_ACACIA_WOOD)));
    public static final RegistryObject<Block> DARK_OAK_CARVING = registerBlock("dark_oak_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_DARK_OAK_WOOD)));
    public static final RegistryObject<Block> MANGROVE_CARVING = registerBlock("mangrove_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_MANGROVE_WOOD)));
    public static final RegistryObject<Block> CHERRY_CARVING = registerBlock("cherry_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_CHERRY_WOOD)));
    public static final RegistryObject<Block> CRIMSON_CARVING = registerBlock("crimson_carving",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_CRIMSON_HYPHAE)));
    public static final RegistryObject<Block> WARPED_CARVING = registerBlock("warped_carving",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_HYPHAE)));
    //hazel=============================================================================================================
    public static final RegistryObject<Block> HAZEL_SAPLING = registerBlock("hazel_sapling",
            () -> new SaplingBlock(new HazelTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<ModLeavesBlock> HAZEL_LEAVES = registerBlock("hazel_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> HAZEL_LOG = registerBlock("hazel_log",
            () -> new HazelLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> HAZEL_WOOD = registerBlock("hazel_wood",
            () -> new HazelLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_HAZEL_LOG = registerBlock("stripped_hazel_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_HAZEL_WOOD = registerBlock("stripped_hazel_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<ModPlanksBlock> HAZEL_PLANKS = registerBlock("hazel_planks",
            () -> new ModPlanksBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> HAZEL_CARVING = registerBlock("hazel_carving",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> HAZEL_STAIRS = registerBlock("hazel_stairs",
            () -> new StairBlock(() -> ModBlocks.HAZEL_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> HAZEL_SLAB = registerBlock("hazel_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> HAZEL_PRESSURE_PLATE = registerBlock("hazel_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));
    public static final RegistryObject<Block> HAZEL_BUTTON = registerBlock("hazel_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.OAK,30,true));
    public static final RegistryObject<Block> HAZEL_FENCE = registerBlock("hazel_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> HAZEL_FENCE_GATE = registerBlock("hazel_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> HAZEL_DOOR = registerBlock("hazel_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> HAZEL_TRAPDOOR = registerBlock("hazel_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));
    public static final RegistryObject<Block> HAZEL_SIGN = BLOCKS.register("hazel_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.HAZEL));
    public static final RegistryObject<Block> HAZEL_WALL_SIGN = BLOCKS.register("hazel_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.HAZEL));
    public static final RegistryObject<Block> HAZEL_HANGING_SIGN = BLOCKS.register("hazel_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.HAZEL));
    public static final RegistryObject<Block> HAZEL_WALL_HANGING_SIGN = BLOCKS.register("hazel_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.HAZEL));
    public static final RegistryObject<Block> HAZELNUT_BUNDLE = registerBlock("hazelnut_bundle",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));
    //sassafras=========================================================================================================
    public static final RegistryObject<Block> SASSAFRAS_SAPLING = registerBlock("sassafras_sapling",
            () -> new SaplingBlock(new SassafrasTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<ModLeavesBlock> SASSAFRAS_LEAVES = registerBlock("sassafras_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SASSAFRAS_LOG = registerBlock("sassafras_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> SASSAFRAS_WOOD = registerBlock("sassafras_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_SASSAFRAS_LOG = registerBlock("stripped_sassafras_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_SASSAFRAS_WOOD = registerBlock("stripped_sassafras_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<ModPlanksBlock> SASSAFRAS_PLANKS = registerBlock("sassafras_planks",
            () -> new ModPlanksBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    //other blocks======================================================================================================
    public static final RegistryObject<Block> FIREMILK_MUSHROOM = registerBlock("firemilk_mushroom",
            () -> new FiremilkMushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM), ModFeatures.HUGE_FIREMILK_KEY));
    public static final RegistryObject<HugeMushroomBlock> FIREMILK_MUSHROOM_BLOCK = registerBlock("firemilk_mushroom_block",
            () -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)));
    public static final RegistryObject<HugeMushroomBlock> DARK_FIREMILK_MUSHROOM_BLOCK = registerBlock("dark_firemilk_mushroom_block",
            () -> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM_BLOCK)));
    public static final RegistryObject<MistletoeBlock> BLOOMING_MISTLETOE_LEAVES = registerBlock("blooming_mistletoe_leaves",
            () -> new MistletoeBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<ModLeavesBlock> MISTLETOE_LEAVES = registerBlock("mistletoe_leaves",
            () -> new MistletoeBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<BrushableBlock> SUSPICIOUS_ROOTS = registerBlock("suspicious_roots",
            () -> new ModSusBlock(Blocks.ROOTED_DIRT,BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT),SoundEvents.BRUSH_GRAVEL,SoundEvents.BRUSH_GRAVEL_COMPLETED));

    //==================================================================================================================
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBLockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBLockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}