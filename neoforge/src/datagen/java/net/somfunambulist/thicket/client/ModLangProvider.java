package net.somfunambulist.thicket.client;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.somfunambulist.thicket.ThicketHelper;
import net.somfunambulist.thicket.ThicketStringDefinitions;

import java.util.List;

public class ModLangProvider extends LanguageProvider {

    public ModLangProvider(PackOutput output) {
        super(output, ThicketHelper.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        forItems(ThicketHelper.getAllModItems());
        forBlocks(ThicketHelper.getAllModBlocks());
        add(ThicketStringDefinitions.TAB_KEY, "Thicket");

    }

    protected <I extends Item> void forItems(List<I> items) {
        items.forEach(item -> {
            String translation = ThicketHelper.toTitleCase(BuiltInRegistries.ITEM.getKey(item).getPath());
            add(item, translation);
        });
    }

    protected <I extends Block> void forBlocks(List<I> blocks) {
        blocks.forEach(block -> {
            String translation = ThicketHelper.toTitleCase(BuiltInRegistries.BLOCK.getKey(block).getPath());
            add(block, translation);
        });
    }
}
