package net.somfunambulist.thicket.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.block.ModBlocks;
import net.somfunambulist.thicket.entity.custom.ModBoatEntity;
import net.somfunambulist.thicket.item.custom.*;

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

    public static final RegistryObject<Item> HAZEL_SIGN = ITEMS.register("hazel_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.HAZEL_SIGN.get(), ModBlocks.HAZEL_WALL_SIGN.get()));
    public static final RegistryObject<Item> HAZEL_HANGING_SIGN = ITEMS.register("hazel_hanging_sign",
            () -> new HangingSignItem(ModBlocks.HAZEL_HANGING_SIGN.get(), ModBlocks.HAZEL_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> HAZEL_BOAT = ITEMS.register("hazel_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.HAZEL, new Item.Properties()));
    public static final RegistryObject<Item> HAZEL_CHEST_BOAT = ITEMS.register("hazel_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.HAZEL, new Item.Properties()));

    public static final RegistryObject<Item> MISTLETOE = ITEMS.register("mistletoe",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENCHANTED_MISTLETOE = ITEMS.register("enchanted_mistletoe",
            () -> new EnchantedMistletoeItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CRUXWOOD_SWORD = ITEMS.register("cruxwood_sword",
            () -> new SwordItem(ModToolTiers.CRUXWOOD,3,-2.4F,new Item.Properties()));
    public static final RegistryObject<Item> CRUXWOOD_PICKAXE = ITEMS.register("cruxwood_pickaxe",
            () -> new PickaxeItem(ModToolTiers.CRUXWOOD,1,-2.8F,new Item.Properties()));
    public static final RegistryObject<Item> CRUXWOOD_SHOVEL = ITEMS.register("cruxwood_shovel",
            () -> new ShovelItem(ModToolTiers.CRUXWOOD,1.5F,-3.0F,new Item.Properties()));
    public static final RegistryObject<Item> CRUXWOOD_AXE = ITEMS.register("cruxwood_axe",
            () -> new AxeItem(ModToolTiers.CRUXWOOD,6,-3.2F,new Item.Properties()));
    public static final RegistryObject<Item> CRUXWOOD_HOE = ITEMS.register("cruxwood_hoe",
            () -> new HoeItem(ModToolTiers.CRUXWOOD,0,-3.0F,new Item.Properties()));
    public static final RegistryObject<Item> CRUXWOOD_BOW = ITEMS.register("cruxwood_bow",
            () -> new BowItem(new Item.Properties().durability(120)));

    public static final RegistryObject<Item> GOLD_SICKLE = ITEMS.register("gold_sickle",
            () -> new SickleItem(new Item.Properties().stacksTo(1).durability(32)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
