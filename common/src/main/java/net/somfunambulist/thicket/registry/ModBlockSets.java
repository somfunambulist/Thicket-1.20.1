package net.somfunambulist.thicket.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.ThicketHelper;
import net.somfunambulist.thicket.content.blocks.SculptureBlock;

import java.util.Map;

public class ModBlockSets {

    public static final Map<WoodType, SculptureBlock> SCULPTURE_BLOCKS = new Object2ObjectOpenHashMap<>();
    public static final Map<WoodType, BlockItem> SCULPTURE_BLOCK_ITEMS = new Object2ObjectOpenHashMap<>();

    private static void regSculptureBlocks(Registrator<Block> event) {
        for (WoodType wood : WoodTypeRegistry.INSTANCE) {
            var id = ThicketHelper.modPrefix(wood.getVariantId("sculpture"));
            var block = new SculptureBlock(wood.copyProperties());
            event.register(id, block);
            SCULPTURE_BLOCKS.put(wood, block);
            wood.addChild(ThicketHelper.modPrefix("sculpture").toString(), block);
        }
    }

    //TODO maybe add burn time stuff
    private static void regSculptureItems(Registrator<Item> event) {
        for (var entry : SCULPTURE_BLOCKS.entrySet()) {
            var woodType = entry.getKey();
            var block = entry.getValue();
            if (woodType == null) continue;
            var item = new BlockItem(block, new Item.Properties()); //TODO do we need specific properties?
            event.register(Utils.getID(block), item);
            SCULPTURE_BLOCK_ITEMS.put(woodType, item);
        }
    }

    public static void init() {
        BlockSetAPI.addDynamicRegistration(ThicketHelper.MOD_ID, ModBlockSets::regSculptureBlocks, BuiltInRegistries.BLOCK);
        BlockSetAPI.addDynamicRegistration(ThicketHelper.MOD_ID, ModBlockSets::regSculptureItems, BuiltInRegistries.ITEM);
    }
}
