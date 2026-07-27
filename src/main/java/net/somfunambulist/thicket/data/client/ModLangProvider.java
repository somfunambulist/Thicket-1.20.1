package net.somfunambulist.thicket.data.client;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.registry.ModItems;
import net.somfunambulist.thicket.util.ModTextUtil;

import java.util.Collection;

public class ModLangProvider extends LanguageProvider {

    public ModLangProvider(PackOutput output) {
        super(output, Thicket.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        forItems(ModItems.REG.getEntries());
        add("thicket.itemGroup", "Thicket");

    }

    protected <I extends Item> void forItems(Collection<DeferredHolder<I, ? extends I>> items) {
        items.forEach(holder -> {
            String translation = ModTextUtil.toTitleCase(holder.getId().getPath());
            addItem(holder, translation);
        });
    }
}
