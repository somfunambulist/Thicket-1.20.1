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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.somfunambulist.thicket.content.recipes.PocketKnifeOnBlock;
import net.somfunambulist.thicket.content.recipes.PocketKnifeOnItem;
import net.somfunambulist.thicket.registry.ModBlockSets;
import net.somfunambulist.thicket.registry.ModItems;

import java.util.concurrent.CompletableFuture;

import static net.somfunambulist.thicket.ThicketHelper.path;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected void buildRecipes(RecipeOutput recipeOutput) {
        pocketKnifeItem(Items.ACACIA_SAPLING, Items.BONE_MEAL, 1, recipeOutput);

        ModBlockSets.CARVING_BLOCKS.forEach((wood, carving) -> {
            var strippedBlock = wood.getBlockOfThis("stripped_log");
            if (strippedBlock != null) {
                pocketKnifeBlock(strippedBlock, carving, recipeOutput);
            }
        });
    }

    public void pocketKnifeItem(ItemLike ingredient, ItemLike result, int count, RecipeOutput recipeOutput) {
        var builder = new SingleItemRecipeBuilder(RecipeCategory.MISC, PocketKnifeOnItem::new, Ingredient.of(ingredient), result, count);
        builder.unlockedBy(getHasName(ingredient), has(ingredient));
        builder.save(recipeOutput, path("pocket_knife/" + getItemName(result)));
    }

    public void pocketKnifeBlock(ItemLike ingredient, ItemLike result, RecipeOutput recipeOutput) {
        var builder = new SingleItemRecipeBuilder(RecipeCategory.MISC, PocketKnifeOnBlock::new, Ingredient.of(ingredient), result, 1);
        builder.unlockedBy(getHasName(ModItems.POCKET_KNIFE.get()), has(ModItems.POCKET_KNIFE.get()));
        builder.save(recipeOutput, path("pocket_knife_block/" + getItemName(result)));
    }
}
