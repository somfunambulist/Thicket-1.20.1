package net.somfunambulist.thicket.content.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.somfunambulist.thicket.registry.ModRecipes;

public class PocketKnifeOnItemRecipe extends SingleItemRecipe {

    public PocketKnifeOnItemRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.POCKET_KNIFE_ITEM.get(), ModRecipes.POCKET_KNIFE_ITEM_SERIALIZER.get(), group, ingredient, result);
    }

    @Override
    public boolean matches(SingleRecipeInput singleRecipeInput, Level level) {
        return this.ingredient.test(singleRecipeInput.item());
    }

    public static class Serializer extends SingleItemRecipe.Serializer<PocketKnifeOnItemRecipe> {
        public Serializer() {
            super(PocketKnifeOnItemRecipe::new);
        }
    }
}
