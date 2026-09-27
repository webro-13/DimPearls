package com.rocco.dimpearls.worldgen;

import com.rocco.dimpearls.DimpearlsMod;
import com.rocco.dimpearls.init.DimpearlsModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DimpearlsMod.MODID)
public class BetweenCrystalWorldgen {

    private static int timer = 0;

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {

        // Only run at the end of the level tick
        if (event.phase != TickEvent.Phase.END)
            return;

        Level level = event.level;

        // Server-side only
        if (!(level instanceof ServerLevel serverLevel))
            return;

        // Only run in The Between
        ResourceKey<Level> betweenKey = ResourceKey.create(
            Registries.DIMENSION,
            new ResourceLocation(DimpearlsMod.MODID, "the_between")
        );

        if (!serverLevel.dimension().equals(betweenKey))
            return;

        timer++;

        // Run every 100 ticks (5 seconds)
        if (timer < 100)
            return;

        timer = 0;

        RandomSource random = serverLevel.getRandom();

        int x = random.nextInt(1000) - 500;
        int z = random.nextInt(1000) - 500;

        int y = serverLevel.getHeight(
            Heightmap.Types.WORLD_SURFACE,
            x,
            z
        );

        BlockPos pos = new BlockPos(x, y, z);

        BlockState crystal =
            DimpearlsModBlocks.BETWEENCRYSTAL.get().defaultBlockState();

        if (serverLevel.isEmptyBlock(pos)) {
            serverLevel.setBlock(pos, crystal, 3);
        }
    }
}