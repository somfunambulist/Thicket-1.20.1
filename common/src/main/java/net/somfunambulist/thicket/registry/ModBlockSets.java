package net.somfunambulist.thicket.registry;

import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.ThicketHelper;
import net.somfunambulist.thicket.content.blocks.SculptureBlock;

public class ModBlockSets {

    private static void registerSculptures(Registrator<Block> event) {
        for (WoodType wood : WoodTypeRegistry.INSTANCE) {
            var id = ThicketHelper.modPrefix(wood.getVariantId("sculpture"));
            var block = new SculptureBlock(wood.copyProperties());
            event.register(id, block);

            wood.addChild(ThicketHelper.modPrefix("sculpture").toString(), block);
        }
    }

    public static void init() {
        BlockSetAPI.addDynamicRegistration(ThicketHelper.MOD_ID, ModBlockSets::registerSculptures, BuiltInRegistries.BLOCK);
    }
}
