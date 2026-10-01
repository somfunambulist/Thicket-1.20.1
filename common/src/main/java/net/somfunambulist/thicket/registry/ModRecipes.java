package net.somfunambulist.thicket.registry;

import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.somfunambulist.thicket.content.recipes.PocketKnifeOnItem;

import java.util.function.Supplier;

import static net.somfunambulist.thicket.ThicketHelper.path;

public class ModRecipes {
    public static final Supplier<RecipeType<PocketKnifeOnItem>> POCKET_KNIFE_ITEM = RegHelper.registerRecipeType(path("pocket_knife_item"));
    public static final Supplier<RecipeType<PocketKnifeOnItem>> POCKET_KNIFE_BLOCK = RegHelper.registerRecipeType(path("pocket_knife_block"));

    public static final RegSupplier<RecipeSerializer<PocketKnifeOnItem>> POCKET_KNIFE_ITEM_SERIALIZER = RegHelper.registerRecipeSerializer(path("pocket_knife_item"), PocketKnifeOnItem.Serializer::new);

    public static void init() {}
}
