package net.somfunambulist.thicket.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
            () -> new MistletoeItem( new Item.Properties(), ModBlocks.HANGING_MISTLETOE.get()));
    public static final RegistryObject<Item> ENCHANTED_MISTLETOE = ITEMS.register("enchanted_mistletoe",
            () -> new EnchantedMistletoeItem(new Item.Properties().stacksTo(1).durability(24)));

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
            () -> new CruxwoodBowItem(new Item.Properties().durability(40)));

    public static final RegistryObject<Item> GOLD_SICKLE = ITEMS.register("gold_sickle",
            () -> new SickleItem(new Item.Properties().stacksTo(1).durability(32)));

    public static final RegistryObject<Item> MYRRH_SIGN = ITEMS.register("myrrh_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.MYRRH_SIGN.get(), ModBlocks.MYRRH_WALL_SIGN.get()));
    public static final RegistryObject<Item> MYRRH_HANGING_SIGN = ITEMS.register("myrrh_hanging_sign",
            () -> new HangingSignItem(ModBlocks.MYRRH_HANGING_SIGN.get(), ModBlocks.MYRRH_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));

    //template setup ===================================================================================================
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component APOCRYPHAL_WEAPON_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(Thicket.MOD_ID, "apocryphal_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component APOCRYPHAL_WEAPON_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Thicket.MOD_ID, "smithing_template.apocryphal_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component APOCRYPHAL_WEAPON_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Thicket.MOD_ID, "smithing_template.apocryphal_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component APOCRYPHAL_WEAPON_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Thicket.MOD_ID, "smithing_template.apocryphal_upgrade.base_slot_description")));
    private static final Component APOCRYPHAL_WEAPON_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(Thicket.MOD_ID, "smithing_template.apocryphal_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_MISTLETOE = new ResourceLocation(Thicket.MOD_ID, "item/empty_slot_mistletoe");
    private static final ResourceLocation EMPTY_SLOT_BOW = new ResourceLocation(Thicket.MOD_ID, "item/empty_slot_bow");
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
    //templates ========================================================================================================
    /*
    public static final RegistryObject<Item> APOCRYPHAL_SMITHING_TEMPLATE = ITEMS.register("apocryphal_smithing_template",
            () -> new SmithingTemplateItem(APOCRYPHAL_WEAPON_APPLIES_TO, APOCRYPHAL_WEAPON_INGREDIENTS, APOCRYPHAL_WEAPON_UPGRADE,
                    APOCRYPHAL_WEAPON_BASE_SLOT_DESCRIPTION, APOCRYPHAL_WEAPON_ADDITIONS_SLOT_DESCRIPTION,
                    List.of(EMPTY_SLOT_SWORD, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL, EMPTY_SLOT_BOW),
                    List.of(EMPTY_SLOT_MISTLETOE)));
    */
    public static final RegistryObject<Item> APOCRYPHAL_SMITHING_TEMPLATE = ITEMS.register("apocryphal_smithing_template",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
