package net.somfunambulist.thicket.client;

import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.somfunambulist.thicket.ThicketHelper;

public class ModBlockProvider extends BlockStateProvider {

    public ModBlockProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ThicketHelper.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (WoodType wt : WoodTypeRegistry.INSTANCE) {
            sculpture(wt);
        }
    }

    private void sculpture(WoodType woodType) {
        var sculptureBlock = woodType.getBlockOfThis("thicket:sculpture");
        if (sculptureBlock == null) return;

        var strippedBlock = woodType.getBlockOfThis("stripped_log");
        if (strippedBlock == null) return;

        var modelFile = models().cubeColumn(name(sculptureBlock), blockTexture(sculptureBlock).withSuffix("_side"), blockTexture(strippedBlock).withSuffix("_top"));
        simpleBlock(sculptureBlock, modelFile);
    }

    private String name(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
