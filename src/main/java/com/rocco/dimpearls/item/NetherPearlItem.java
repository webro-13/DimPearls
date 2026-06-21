package com.rocco.dimpearls.item;

import com.rocco.dimpearls.data.PearlData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.List;
import java.util.EnumSet;
import net.minecraft.world.item.TooltipFlag;


public class NetherPearlItem extends Item {

public NetherPearlItem(Properties properties) {
    super(properties);
}

@Override
public InteractionResult use(Level level, Player player, InteractionHand hand) {

    if (!(player instanceof ServerPlayer serverPlayer))
        return InteractionResult.PASS;

    if (player.isShiftKeyDown()) {

        PearlData.saveLocation(serverPlayer, "nether");

        serverPlayer.sendSystemMessage(
            Component.literal("Nether location saved!")
        );

        return InteractionResult.SUCCESS;
    }

    BlockPos pos = PearlData.getLocation(serverPlayer, "nether");

    if (pos == null) {
        serverPlayer.sendSystemMessage(
            Component.literal("No Nether location saved!")
        );
        return InteractionResult.SUCCESS;
    }

    ResourceKey<Level> dimensionKey = ResourceKey.create(Registries.DIMENSION, 
    Identifier.fromNamespaceAndPath("minecraft", "the_nether")); 

    ResourceKey<Level> playerDim = player.level().dimension();

    ServerLevel nether = serverPlayer.level().getServer().getLevel(dimensionKey);

    if (nether == null){
        return InteractionResult.FAIL;
    }

    if(playerDim != dimensionKey){
        
        serverPlayer.teleportTo(
        nether,
        pos.getX() + 0.5,
        pos.getY(),
        pos.getZ() + 0.5,
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
}
