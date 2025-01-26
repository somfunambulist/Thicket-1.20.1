package net.somfunambulist.thicket.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties HAZELNUT = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).fast().build();
    public static final FoodProperties HAZELBUTTER = new FoodProperties.Builder().nutrition(4).saturationMod(0.2f).build();
    public static final FoodProperties HAZELBUTTER_TREAT = new FoodProperties.Builder().nutrition(5).saturationMod(0.8f).build();

}
