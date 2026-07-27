package net.somfunambulist.thicket.registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.common.item.PocketKnifeItem;

public class ModItems {
    public static final DeferredRegister.Items REG = DeferredRegister.createItems(Thicket.MOD_ID);

    public static final DeferredItem<PocketKnifeItem> POCKET_KNIFE = REG.register("pocket_knife", () -> new PocketKnifeItem(new Item.Properties().stacksTo(1).durability(238)));
}
