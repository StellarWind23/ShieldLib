package com.github.stellarwind22.shieldlib.lib.object;

import com.github.stellarwind22.shieldlib.lib.component.ShieldDataComponents;
import com.github.stellarwind22.shieldlib.lib.component.ShieldInformation;
import com.github.stellarwind22.shieldlib.lib.config.ShieldLibConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Everything in this class will remain binary-compatible to the maximum extent.
 */
public class ShieldLibUtils {

    public static final ShieldInformation VANILLA_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("vanilla", List.of("config"));

    public static final ShieldInformation TOWER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("tower", List.of());
    public static final ShieldInformation CONFIG_TOWER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("tower", List.of("config"));
    public static final ShieldInformation SPIKED_TOWER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("tower", List.of("spiked"));
    public static final ShieldInformation CONFIG_SPIKED_TOWER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("tower", List.of("spiked", "config"));

    public static final ShieldInformation BUCKLER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("buckler", List.of());
    public static final ShieldInformation CONFIG_BUCKLER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("buckler", List.of("config"));
    public static final ShieldInformation SPIKED_BUCKLER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("buckler", List.of("spiked"));
    public static final ShieldInformation CONFIG_SPIKED_BUCKLER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("buckler", List.of("spiked", "config"));

    public static final ShieldInformation HEATER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("heater", List.of());
    public static final ShieldInformation CONFIG_HEATER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("heater", List.of("config"));
    public static final ShieldInformation SPIKED_HEATER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("heater", List.of("spiked"));
    public static final ShieldInformation CONFIG_SPIKED_HEATER_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("heater", List.of("spiked", "config"));

    public static final ShieldInformation TARGE_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("targe", List.of());
    public static final ShieldInformation CONFIG_TARGE_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("targe", List.of("config"));
    public static final ShieldInformation SPIKED_TARGE_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("targe", List.of("spiked"));
    public static final ShieldInformation CONFIG_SPIKED_TARGE_SHIELD_INFORMATION_COMPONENT = new ShieldInformation("targe", List.of("spiked", "config"));

    public static BlocksAttacks vanillaShieldBlocksAttacks(HolderLookup.Provider ctx) { return createBlocksAttacks(ctx, ShieldLibConfig.vanilla_shield_blocking_angle); }
    public static BlocksAttacks towerShieldBlocksAttacks(HolderLookup.Provider ctx)   { return createBlocksAttacks(ctx, ShieldLibConfig.tower_blocking_angle); }
    public static BlocksAttacks bucklerShieldBlocksAttacks(HolderLookup.Provider ctx) { return createBlocksAttacks(ctx, ShieldLibConfig.buckler_blocking_angle); }
    public static BlocksAttacks heaterShieldBlocksAttacks(HolderLookup.Provider ctx)  { return createBlocksAttacks(ctx, ShieldLibConfig.heater_blocking_angle); }
    public static BlocksAttacks targeShieldBlocksAttacks(HolderLookup.Provider ctx)   { return createBlocksAttacks(ctx, ShieldLibConfig.targe_blocking_angle); }

    /**
     * @param itemStack stack to check.
     * @return true if itemStack can be considered a shield.
     */
    public static boolean isShieldItem(ItemStack itemStack) {
        return itemStack.has(DataComponents.BLOCKS_ATTACKS);
    }

    /**
     * @param item item to check
     * @return true if item can be considered a shield.
     */
    public static boolean isShieldItem(Item item) {
        return item.components().has(DataComponents.BLOCKS_ATTACKS);
    }

    /**
     * @param itemStack stack to check.
     * @return true if the item (a shield) can support banner.
     */
    public static boolean supportsBanner(ItemStack itemStack) {
        return supportsBanner(itemStack.getItem());
    }

    /**
     * @param item item to check
     * @return true if the item (a shield) can support banner.
     */
    @SuppressWarnings("deprecation")
    public static boolean supportsBanner(Item item) {
        return item.builtInRegistryHolder().is(ShieldLibTags.SUPPORTS_BANNER);
    }

    @SuppressWarnings("unused")
    public static Item.Properties vanillaShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(vanillaShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), VANILLA_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties vanillaShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::vanillaShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), VANILLA_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties towerShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(towerShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), TOWER_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties towerShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::towerShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_TOWER_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties heaterShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(heaterShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), HEATER_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties heaterShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::heaterShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_HEATER_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties bucklerShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(bucklerShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), BUCKLER_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties bucklerShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::bucklerShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_BUCKLER_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties targeShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(targeShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), TARGE_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties targeShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::targeShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_TARGE_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties spikedTowerShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(towerShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), SPIKED_TOWER_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties spikedTowerShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::towerShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_SPIKED_TOWER_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties spikedHeaterShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(heaterShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), SPIKED_HEATER_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties spikedHeaterShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::heaterShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_SPIKED_HEATER_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties spikedBucklerShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(bucklerShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), SPIKED_BUCKLER_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties spikedBucklerShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::bucklerShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_SPIKED_BUCKLER_SHIELD_INFORMATION_COMPONENT));
    }

    @SuppressWarnings("unused")
    public static Item.Properties spikedTargeShieldProperties(Item.Properties properties, float cooldownSeconds) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS,
                        ctx -> withCooldownSeconds(targeShieldBlocksAttacks(ctx), cooldownSeconds))
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), SPIKED_TARGE_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties spikedTargeShieldProperties(Item.Properties properties) {
        return defaultShieldProperties(properties
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, ShieldLibUtils::targeShieldBlocksAttacks)
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), CONFIG_SPIKED_TARGE_SHIELD_INFORMATION_COMPONENT));
    }

    public static Item.Properties buildShieldProperties(Item.Properties properties, ShieldInformation shieldInformation,
                                                        Function<HolderLookup.Provider, BlocksAttacks> blocksAttacks) {
        return defaultShieldProperties(properties
                .component(ShieldDataComponents.SHIELD_INFORMATION.get(), shieldInformation)
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, blocksAttacks::apply)
        );
    }

    public static Item.Properties defaultShieldProperties(Item.Properties properties) {
        return properties
                .equippableUnswappable(EquipmentSlot.OFFHAND)
                .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK);
    }

    @SuppressWarnings("unused")
    public static Item.Properties withShieldComponent(Item.Properties properties,
                                                      Function<HolderLookup.Provider, BlocksAttacks> blocksAttacks) {
        return properties
                .equippableUnswappable(EquipmentSlot.OFFHAND)
                .delayedComponent(DataComponents.BLOCKS_ATTACKS, blocksAttacks::apply);
    }

    /**
     * @param in BlocksAttacks component going in.
     * @param cooldownSeconds cooldown seconds to be added.
     * @return component with that many cooldown seconds.
     */
    public static BlocksAttacks withCooldownSeconds(BlocksAttacks in, float cooldownSeconds) {
        return new BlocksAttacks(
                in.blockDelaySeconds(),
                cooldownSeconds / 5.0F,
                in.damageReductions(),
                in.itemDamage(),
                in.bypassedBy(),
                in.blockSound(),
                in.disableSound()
        );
    }

    /**
     * @param in BlocksAttacks component going in.
     * @param cooldownTicks cooldown ticks to be added.
     * @return component with that many cooldown ticks.
     */
    @Deprecated
    public static BlocksAttacks withCooldownTicks(BlocksAttacks in, int cooldownTicks) {
        return new BlocksAttacks(
                in.blockDelaySeconds(),
                (float) cooldownTicks / 100.0F,
                in.damageReductions(),
                in.itemDamage(),
                in.bypassedBy(),
                in.blockSound(),
                in.disableSound()
        );
    }

    /**
     * @param in ShieldInformation component going in.
     * @param features features to be added.
     * @return component with added features.
     */
    @SuppressWarnings("unused")
    public static ShieldInformation withAddedFeatures(ShieldInformation in, String...features) {
        List<String> newFeatures = in.features();
        newFeatures.addAll(List.of(features));
        return new ShieldInformation(
                in.type(),
                newFeatures
        );
    }

    @SuppressWarnings("unused")
    public static BlocksAttacks withHorizontalAngle(BlocksAttacks in, float angle) {

        List<BlocksAttacks.DamageReduction> reductions = new ArrayList<>(in.damageReductions());
        List<BlocksAttacks.DamageReduction> newReductions = new ArrayList<>();

        for(BlocksAttacks.DamageReduction reduction : reductions) {
            newReductions.add(
                    new BlocksAttacks.DamageReduction(
                            angle,
                            reduction.type(),
                            reduction.base(),
                            reduction.factor()
                    )
            );
        }

        return new BlocksAttacks(
                in.blockDelaySeconds(),
                in.blockDelaySeconds(),
                newReductions,
                in.itemDamage(),
                in.bypassedBy(),
                in.blockSound(),
                in.disableSound()
        );
    }

    public static int getEnchantmentLevel(Identifier enchantmentId, ItemStack itemStack) {

        if(itemStack == null) return 0;

        ItemEnchantments enchants = itemStack.getEnchantments();
        AtomicInteger result = new AtomicInteger();

        for (Holder<Enchantment> holder : enchants.keySet()) {

            holder.unwrapKey().ifPresent(key -> {
                if(key.identifier().equals(enchantmentId)) {
                    result.set(enchants.getLevel(holder));
                }
            });
        }
        return result.get();
    }

    public static String getTranslationKey(TagKey<Item> key) {
        Identifier location = key.location();
        return "tag.item." + location.getNamespace() + "." + location.getPath().replace('/', '.');
    }

    private static BlocksAttacks createBlocksAttacks(HolderLookup.Provider context, float angle) {
        return new BlocksAttacks(
                0.25F,
                1.0F,
                List.of(new BlocksAttacks.DamageReduction(angle, Optional.empty(), 0.0F, 1.0F)),
                new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                Optional.of(SoundEvents.SHIELD_BLOCK),
                Optional.of(SoundEvents.SHIELD_BREAK)
        );
    }
}
