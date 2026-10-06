package net.somfunambulist.thicket.server.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.content.recipes.PocketKnifeOnBlockRecipe;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static net.somfunambulist.thicket.ThicketHelper.path;

public class PocketKnifeOnBlockRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Block result;
    private final Block input;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    private String group;

    public PocketKnifeOnBlockRecipeBuilder(RecipeCategory category, Block input, Block result) {
        this.category = category;
        this.input = input;
        this.result = result;
    }

    public PocketKnifeOnBlockRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public PocketKnifeOnBlockRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    public Item getResult() {
        return this.result.asItem();
    }

    public void save(RecipeOutput recipeOutput) {
        var resultLocation = BuiltInRegistries.BLOCK.getKey(this.result);
        this.save(recipeOutput, path("pocket_knife_block/" + resultLocation.getPath()));
    }

    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancement$builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        var inputLocation = BuiltInRegistries.BLOCK.getKey(this.input);
        var resultLocation = BuiltInRegistries.BLOCK.getKey(this.result);

        var recipe = new PocketKnifeOnBlockRecipe(Objects.requireNonNullElse(this.group, ""), inputLocation, resultLocation);
        recipeOutput.accept(id, recipe, advancement$builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}
