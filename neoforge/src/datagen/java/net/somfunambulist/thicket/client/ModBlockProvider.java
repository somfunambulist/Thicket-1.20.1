package net.somfunambulist.thicket.client;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.somfunambulist.thicket.ThicketHelper;

public class ModBlockProvider extends BlockStateProvider {

    public ModBlockProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ThicketHelper.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (WoodType wt : WoodTypeRegistry.INSTANCE) {
            if (!wt.isVanilla()) continue;
            carving(wt);
        }
    }

    private void carving(WoodType woodType) {
        var carvingBlock = woodType.getBlockOfThis("thicket:carving");
        if (carvingBlock == null) return;

        var strippedBlock = woodType.getBlockOfThis("stripped_log");
        if (strippedBlock == null) return;

        var side = blockTexture(carvingBlock).withSuffix("_side");
        var end = blockTexture(strippedBlock).withSuffix("_top");


        var horizontalModel = models().cubeColumnHorizontal(name(carvingBlock) + "_horizontal", side, end);
        var normalModel = models().cubeColumn(name(carvingBlock), side, end);

        getVariantBuilder(carvingBlock).forAllStates(state -> {
            Direction dir = state.getValue(BlockStateProperties.FACING);
            if (dir.getAxis().isVertical()) {
                return ConfiguredModel.builder().modelFile(normalModel).build();
            } else {
                return ConfiguredModel.builder()
                        .modelFile(horizontalModel)
                        .rotationX(90)
                        .rotationY((((int) dir.toYRot()) + 180) % 360)
                        .build();
            }
        });

        simpleBlockItem(carvingBlock, normalModel);
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
