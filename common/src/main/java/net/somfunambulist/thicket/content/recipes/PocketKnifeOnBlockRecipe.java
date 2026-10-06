package net.somfunambulist.thicket.content.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.registry.ModRecipes;

public class PocketKnifeOnBlockRecipe implements Recipe<BlockLocationRecipeInput> {
    protected String group;
    protected ResourceLocation input;
    protected ResourceLocation result;

    public PocketKnifeOnBlockRecipe(String group, ResourceLocation input, ResourceLocation result) {
       this.group = group;
       this.input = input;
       this.result = result;
    }

    @Override
    public boolean matches(BlockLocationRecipeInput input, Level level) {
        return input.test(this.input);
    }

    @Override
    public ItemStack assemble(BlockLocationRecipeInput input, HolderLookup.Provider registries) {
        var block = BuiltInRegistries.BLOCK.get(this.result);
        return block.asItem().getDefaultInstance().copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        var block = BuiltInRegistries.BLOCK.get(this.result);
        return block.asItem().getDefaultInstance();
    }

    public Block getResultBlock() {
        return BuiltInRegistries.BLOCK.get(this.result);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.POCKET_KNIFE_BLOCK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.POCKET_KNIFE_BLOCK.get();
    }

    public static class Serializer implements RecipeSerializer<PocketKnifeOnBlockRecipe> {
        private static final MapCodec<PocketKnifeOnBlockRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                        ResourceLocation.CODEC.fieldOf("input").forGetter(recipe -> recipe.input),
                        ResourceLocation.CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                ).apply(instance, PocketKnifeOnBlockRecipe::new));

        public Serializer() {
            super();
        }

        @Override
        public MapCodec<PocketKnifeOnBlockRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PocketKnifeOnBlockRecipe> streamCodec() {
            return ByteBufCodecs.fromCodecWithRegistries(CODEC.codec());
        }
    }
}
