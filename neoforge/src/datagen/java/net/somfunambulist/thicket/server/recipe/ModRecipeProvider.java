package net.somfunambulist.thicket.server.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.somfunambulist.thicket.content.recipes.PocketKnifeOnItem;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected void buildRecipes(RecipeOutput recipeOutput) {
        pocketKnifeItem(Ingredient.of(Items.ACACIA_SAPLING), Items.BONE_MEAL, 1)
                .unlockedBy(getHasName(Items.ACACIA_SAPLING), has(Items.ACACIA_SAPLING)).save(recipeOutput);
    }

    public static SingleItemRecipeBuilder pocketKnifeItem(Ingredient ingredient, ItemLike result, int count) {
        return new SingleItemRecipeBuilder(RecipeCategory.MISC, PocketKnifeOnItem::new, ingredient, result, count);
    }
}
