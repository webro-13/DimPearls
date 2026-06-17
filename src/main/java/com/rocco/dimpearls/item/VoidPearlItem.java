package com.rocco.dimpearls.item;

import com.rocco.dimpearls.data.PearlData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.EnumSet;


public class VoidPearlItem extends Item {

public VoidPearlItem(Properties properties) {
    super(properties);
}

@Override

public InteractionResult use(Level level, Player player, InteractionHand hand) {

    if (!(player instanceof ServerPlayer serverPlayer))
        return InteractionResult.PASS;

    ResourceKey<Level> dimensionKey = ResourceKey.create(Registries.DIMENSION, 
    Identifier.fromNamespaceAndPath("dimpearls", "the_between")); 

    ServerLevel between = serverPlayer.level().getServer().getLevel(dimensionKey);

    if (between == null)
        return InteractionResult.FAIL;

    double x = 0;
    double y = 64;
    double z = 0;

    serverPlayer.teleportTo(
        between,
        x,
        y,
        z,
        EnumSet.noneOf(Relative.class),
        serverPlayer.getYRot(),
        serverPlayer.getXRot(),
        true
    );

    serverPlayer.getCooldowns().addCooldown(player.getItemInHand(hand), 100);

ItemStack stack = player.getItemInHand(hand);
stack.setDamageValue(stack.getDamageValue() + 1);

    return InteractionResult.SUCCESS;
}


}
