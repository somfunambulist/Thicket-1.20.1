package net.somfunambulist.thicket.content.recipes;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.block.Block;

public record BlockLocationRecipeInput(Block block) implements RecipeInput {

    public boolean test(ResourceLocation location) {
        var givenBlockLoc = BuiltInRegistries.BLOCK.getKey(this.block);
        return givenBlockLoc.equals(location);
    }

    @Override
    public ItemStack getItem(int index) {
        return this.block.asItem().getDefaultInstance();
    }

    @Override
    public int size() {
        return 1;
    }
}
