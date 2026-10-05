package net.somfunambulist.thicket.content.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.somfunambulist.thicket.registry.ModRecipes;

public class PocketKnifeOnBlock extends SingleItemRecipe {

    public PocketKnifeOnBlock(String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.POCKET_KNIFE_BLOCK.get(), ModRecipes.POCKET_KNIFE_BLOCK_SERIALIZER.get(), group, ingredient, result);
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }

    public static class Serializer extends SingleItemRecipe.Serializer<PocketKnifeOnBlock> {
        private static final MapCodec<PocketKnifeOnBlock> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(recipe -> recipe.group),
                        Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").validate(ingredient -> {
                            var items = ingredient.getItems();
                            if (items.length > 1) return DataResult.error(() -> "Failed to parse pocket_knife_block recipe because the ingredient has more than one item", ingredient);
                            var firstItem = items[0].getItem();
                            if (!(firstItem instanceof BlockItem)) {
                                var itemName = BuiltInRegistries.ITEM.getKey(firstItem);
                                return DataResult.error(() -> "Failed to parse pocket_knife_block recipe because " + itemName + " is not a block item", ingredient);
                            } else {
                                return DataResult.success(ingredient);
                            }
                        }).forGetter(p_301068_ -> p_301068_.ingredient),
                        ItemStack.SINGLE_ITEM_CODEC.fieldOf("result").validate(stack -> {
                            if (stack.getItem() instanceof BlockItem) {
                                return DataResult.success(stack);
                            } else {
                                var itemName = BuiltInRegistries.ITEM.getKey(stack.getItem());
                                return DataResult.error(() -> "Failed to parse pocket_knife_block recipe because " + itemName + " is not a block item", stack);
                            }
                        }).forGetter(p_302316_ -> p_302316_.result)
                ).apply(instance, PocketKnifeOnBlock::new));

        public Serializer() {
            super(PocketKnifeOnBlock::new);
        }

        @Override
        public MapCodec<PocketKnifeOnBlock> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PocketKnifeOnBlock> streamCodec() {
            return ByteBufCodecs.fromCodecWithRegistries(CODEC.codec());
        }
    }
}
