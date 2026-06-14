package com.rocco.dimpearls.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class DirtPearlItem extends Item {

    public DirtPearlItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {
            player.sendSystemMessage(Component.literal("Dirt Pearl works!"));
        }

        return InteractionResult.SUCCESS;
    }
}