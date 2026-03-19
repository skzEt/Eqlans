package net.skzEt.eqlans.registries;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties DUMPLING = new FoodProperties.Builder().nutrition(4)
            .saturationModifier(0.1f).build();
    public static final FoodProperties DUMPLING_SUN = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(0.1f).build();
    public static final FoodProperties OVERCOOKED_DUMPLING = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.1f).build();
    public static final FoodProperties GINGER_BREAD = new FoodProperties.Builder().nutrition(3)
            .saturationModifier(0.1f).build();


}
