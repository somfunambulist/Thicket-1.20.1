package net.somfunambulist.thicket.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.entity.custom.ModBoatEntity;
import net.somfunambulist.thicket.item.custom.DowsingRodItem;
import net.somfunambulist.thicket.item.custom.HazelbutterItem;
import net.somfunambulist.thicket.item.custom.ModBoatItem;
import net.somfunambulist.thicket.item.custom.PocketKnifeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Thicket.MOD_ID);

    public static final RegistryObject<Item> POCKET_KNIFE = ITEMS.register("pocket_knife",
            () -> new PocketKnifeItem(new Item.Properties().stacksTo(1).durability(238)));

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> HAZELNUT = ITEMS.register("hazelnut",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HAZELNUT)));
    public static final RegistryObject<Item> HAZELBUTTER = ITEMS.register("hazelbutter",
            () -> new HazelbutterItem(new Item.Properties().food(ModFoodProperties.HAZELBUTTER)));
    public static final RegistryObject<Item> HAZELBUTTER_TREAT = ITEMS.register("hazelbutter_treat",
            () -> new Item(new Item.Properties().food(ModFoodProperties.HAZELBUTTER_TREAT)));

    public static final RegistryObject<Item> HAZEL_BOAT = ITEMS.register("hazel_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.HAZEL, new Item.Properties()));
    public static final RegistryObject<Item> HAZEL_CHEST_BOAT = ITEMS.register("hazel_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.HAZEL, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
