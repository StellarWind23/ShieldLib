package com.github.stellarwind22.shieldlib.lib.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ShieldLibConfig extends MidnightConfig {

    /**
     * Categories
     */
    public static final String MAIN = "main";
    public static final String SHIELDS = "shields";

    /**
     * Main Category
     */
    @Entry(category = MAIN) public static final boolean show_cooldown_tooltip = true;
    @Entry(category = MAIN) public static final boolean show_movement_tooltip = false;
    @SuppressWarnings("unused") @Comment(category = MAIN, centered = true) public static Comment universal_disabling_description;
    @Entry(category = MAIN) public static final boolean universal_disabling = true;

    /**
     * Shields Category
     */
    @SuppressWarnings("unused") @Comment(category = SHIELDS, centered = true) public static Comment vanilla_section;
    @Entry(category = SHIELDS, min = 1) public static final int vanilla_shield_durability = 336;
    @SuppressWarnings("unused") @Comment(category = SHIELDS, centered = true) public static Comment enchantability_convention;
    @Entry(category = SHIELDS, min = 0) public static final int vanilla_shield_enchantability = 14;
    @Entry(category = SHIELDS, min = 0F, max = 2F, isSlider = true) public static final float  vanilla_shield_movement_multiplier = 0.2F;
    @Entry(category = SHIELDS, min = 0F, max = 360F, isSlider = true) public static final float  vanilla_shield_blocking_angle = 90.0F;
    @Entry(category = SHIELDS, min = 0F, max = 20F, isSlider = true) public static final float vanilla_shield_cooldown_seconds = 5.0F;
    @SuppressWarnings("unused") @Comment(category = SHIELDS, centered = true) public static Comment tower_section;
    @Entry(category = SHIELDS, min = 0F, max = 2F, isSlider = true) public static final float  tower_movement_multiplier = 0.2F;
    @Entry(category = SHIELDS, min = 0F, max = 360F, isSlider = true) public static final float  tower_blocking_angle = 90.0F;
    @Entry(category = SHIELDS, min = 0F, max = 20F, isSlider = true) public static final float tower_default_cooldown_seconds = 5.0F;
    @Entry(category = SHIELDS, min = 0) public static final int tower_spiked_hit_damage = 3;
    @Entry(category = SHIELDS, min = 0) public static final int tower_spiked_collide_damage = 2;
    @SuppressWarnings("unused") @Comment(category = SHIELDS, centered = true) public static Comment buckler_section;
    @Entry(category = SHIELDS, min = 0F, max = 2F, isSlider = true) public static final float  buckler_movement_multiplier = 0.75F;
    @Entry(category = SHIELDS, min = 0F, max = 360F, isSlider = true) public static final float  buckler_blocking_angle = 90;
    @Entry(category = SHIELDS, min = 0F, max = 20F, isSlider = true) public static final float buckler_default_cooldown_seconds = 2.5F;
    @Entry(category = SHIELDS, min = 0) public static final int buckler_spiked_hit_damage = 2;
    @Entry(category = SHIELDS, min = 0) public static final int buckler_spiked_collide_damage = 1;
    @Entry(category = SHIELDS) public static final boolean buckler_blocks_arrows = false;
    @SuppressWarnings("unused") @Comment(category = SHIELDS, centered = true) public static Comment heater_section;
    @Entry(category = SHIELDS, min = 0F, max = 2F, isSlider = true) public static final float  heater_movement_multiplier = 0.5F;
    @Entry(category = SHIELDS, min = 0F, max = 360F, isSlider = true) public static final float  heater_blocking_angle = 29.7F;
    @Entry(category = SHIELDS, min = 0F, max = 20F, isSlider = true) public static final float heater_default_cooldown_seconds = 3.5F;
    @Entry(category = SHIELDS, min = 0) public static final int heater_spiked_hit_damage = 2;
    @Entry(category = SHIELDS, min = 0) public static final int heater_spiked_collide_damage = 1;
    @SuppressWarnings("unused") @Comment(category = SHIELDS, centered = true) public static Comment targe_section;
    @Entry(category = SHIELDS, min = 0F, max = 2F, isSlider = true) public static final float  targe_movement_multiplier = 0.1F;
    @Entry(category = SHIELDS, min = 0F, max = 360F, isSlider = true) public static final float  targe_blocking_angle = 110.0F;
    @Entry(category = SHIELDS, min = 0F, max = 20F, isSlider = true) public static final float targe_default_cooldown_seconds = 6.0F;
    @Entry(category = SHIELDS, min = 0) public static final int targe_spiked_hit_damage = 4;
    @Entry(category = SHIELDS, min = 0) public static final int targe_spiked_collide_damage = 2;
}
