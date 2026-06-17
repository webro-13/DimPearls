package com.rocco.dimpearls.worldgen;

import com.rocco.dimpearls.DimPearls;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

@EventBusSubscriber(modid = DimPearls.MODID)
public class BetweenCrystalWorldgen
{
    private static int timer = 0;

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event)
    {
        Level level = event.getLevel();

        if (!(level instanceof ServerLevel serverLevel))
            return;

        // Only run in The Between
        // Create the ResourceKey and compare it (don't call .location())
        ResourceKey<Level> betweenKey = ResourceKey.create(Registries.DIMENSION, 
    Identifier.fromNamespaceAndPath(DimPearls.MODID, "the_between")); 
        // For newer mappings (1.20+), use:
        // ResourceKey<Level> betweenKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(DimPearls.MODID, "the_between"));

        if (!serverLevel.dimension().equals(betweenKey))
            return;

        timer++;

        // Run occasionally
        if (timer < 100)
            return;

        timer = 0;

        RandomSource random = serverLevel.getRandom();

        int x = random.nextInt(1000) - 500;
        int z = random.nextInt(1000) - 500;

        int y = serverLevel.getHeight(
            net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE,
            x,
            z
        );

        BlockPos pos = new BlockPos(x, y, z);

        BlockState crystal =
            DimPearls.BETWEEN_CRYSTAL.get().defaultBlockState();

        if (serverLevel.isEmptyBlock(pos))
        {
            serverLevel.setBlock(pos, crystal, 3);
        }
    }
}