package com.rocco.dimpearls.item;

import java.util.Set;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;

public class BanishHammerItem extends MaceItem
{
    private final String targetDimension;

public BanishHammerItem(
    Properties properties,
    String dimension
)
{
    super(properties);

    this.targetDimension =
        dimension;
}

    @Override
    public void hurtEnemy(
        ItemStack stack,
        LivingEntity target,
        LivingEntity attacker
    )
    {
        if (
            !attacker.level().isClientSide() &&
            attacker.level() instanceof ServerLevel
        )
        {
            String dimensionId =
                this.targetDimension;

            if (
    stack.has(
        net.minecraft.core.component.DataComponents.CUSTOM_DATA
    )
)
{
    var customData =
        stack.get(
            net.minecraft.core.component.DataComponents.CUSTOM_DATA
        );

    if (
        customData != null &&
        customData.copyTag().contains(
            "target_dimension"
        )
    )
    {
        dimensionId =
            customData
                .copyTag()
                .getString(
    "target_dimension"
).orElse(
    "dimpearls:the_between"
);
    }
}

            ServerLevel currentLevel =
                (ServerLevel) attacker.level();

            ResourceKey<Level> targetKey =
                ResourceKey.create(
                    Registries.DIMENSION,
                    Identifier.parse(
                        dimensionId
                    )
                );

            ServerLevel targetLevel =
                currentLevel
                    .getServer()
                    .getLevel(targetKey);

            if (targetLevel != null)
            {
                target.teleportTo(
                    targetLevel,
                    0,
                    80,
                    0,
                    Set.of(),
                    target.getYRot(),
                    target.getXRot(),
                    true
                );
            }
        }

        super.hurtEnemy(
            stack,
            target,
            attacker
        );

        InteractionHand hand = InteractionHand.MAIN_HAND;
        ItemStack stackhand;
        if (attacker instanceof Player) {
            stackhand = ((Player) attacker).getItemInHand(hand);
        } else {
            stackhand = attacker.getItemInHand(hand);
        }

        stackhand.setDamageValue(stackhand.getDamageValue() + 1);
        if (stackhand.getDamageValue() >= stackhand.getMaxDamage()) {
            stackhand.shrink(1);
        }
    }
}