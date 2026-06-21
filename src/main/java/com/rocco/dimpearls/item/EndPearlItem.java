package com.rocco.dimpearls.item;

import com.rocco.dimpearls.data.PearlData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.List;
import net.minecraft.world.item.TooltipFlag;
import java.util.EnumSet;


public class EndPearlItem extends Item {


public EndPearlItem(Properties properties) {
    super(properties);
}

@Override
public InteractionResult use(Level level, Player player, InteractionHand hand) {

    if (!(player instanceof ServerPlayer serverPlayer))
        return InteractionResult.PASS;

    if (player.isShiftKeyDown()) {

        PearlData.saveLocation(serverPlayer, "end");

        serverPlayer.sendSystemMessage(
            Component.literal("End location saved!")
        );

        return InteractionResult.SUCCESS;
    }

    BlockPos pos = PearlData.getLocation(serverPlayer, "end");

    if (pos == null) {
        serverPlayer.sendSystemMessage(
            Component.literal("No End location saved!")
        );
        return InteractionResult.SUCCESS;
    }

        ResourceKey<Level> dimensionKey = ResourceKey.create(Registries.DIMENSION, 
    Identifier.fromNamespaceAndPath("minecraft", "the_end")); 

    ResourceKey<Level> playerDim = player.level().dimension();

    ServerLevel end = serverPlayer.level().getServer().getLevel(dimensionKey);

    if (end == null){
        return InteractionResult.FAIL;
    }

    if(playerDim != dimensionKey){
        
        serverPlayer.teleportTo(
        end,
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

return InteractionResult.SUCCESS;

}
}
