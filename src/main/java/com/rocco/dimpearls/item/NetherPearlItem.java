package com.rocco.dimpearls.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class NetherPearlItem extends Item {

    public NetherPearlItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {
            player.sendSystemMessage(Component.literal("Nether Pearl works!"));
        }

        return InteractionResult.SUCCESS;
    }
}