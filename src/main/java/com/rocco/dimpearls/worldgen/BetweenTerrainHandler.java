package com.rocco.dimpearls.worldgen;

import com.rocco.dimpearls.DimPearls;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.LevelChunk;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

@EventBusSubscriber(modid = DimPearls.MODID)
public class BetweenTerrainHandler
{
    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event)
    {
        Level level = (Level) event.getLevel();

        if (!(level instanceof ServerLevel serverLevel))
            return;

        // Only generate in The Between
        if (!serverLevel.dimension().location().toString().equals("dimpearls:the_between"))
            return;

        LevelChunk chunk = (LevelChunk) event.getChunk();

        ChunkPos chunkPos = chunk.getPos();

        // Deterministic random
        RandomSource random = RandomSource.create(
            chunkPos.x * 341873128712L +
            chunkPos.z * 132897987541L
        );

        generatePillars(serverLevel, chunkPos, random);

        generateCrystalPatches(serverLevel, chunkPos, random);

        generateFloatingIslands(serverLevel, chunkPos, random);
    }

    private static void generatePillars(
        ServerLevel level,
        ChunkPos chunkPos,
        RandomSource random
    )
    {
        for (int i = 0; i < 6; i++)
        {
            int x = chunkPos.getMinBlockX() + random.nextInt(16);
            int z = chunkPos.getMinBlockZ() + random.nextInt(16);

            int baseY = 45 + random.nextInt(20);

            int height = 20 + random.nextInt(40);

            int radius = 1 + random.nextInt(3);

            for (int y = baseY; y < baseY + height; y++)
            {
                for (int dx = -radius; dx <= radius; dx++)
                {
                    for (int dz = -radius; dz <= radius; dz++)
                    {
                        BlockPos pos = new BlockPos(
                            x + dx,
                            y,
                            z + dz
                        );

                        level.setBlock(
                            pos,
                            DimPearls.BETWEEN_STONE.get().defaultBlockState(),
                            3
                        );
                    }
                }
            }
        }
    }

    private static void generateCrystalPatches(
        ServerLevel level,
        ChunkPos chunkPos,
        RandomSource random
    )
    {
        for (int i = 0; i < 20; i++)
        {
            int x = chunkPos.getMinBlockX() + random.nextInt(16);

            int z = chunkPos.getMinBlockZ() + random.nextInt(16);

            int y = 50 + random.nextInt(30);

            BlockPos pos = new BlockPos(x, y, z);

            if (level.isEmptyBlock(pos))
            {
                level.setBlock(
                    pos,
                    DimPearls.BETWEEN_CRYSTAL.get().defaultBlockState(),
                    3
                );
            }
        }
    }

    private static void generateFloatingIslands(
        ServerLevel level,
        ChunkPos chunkPos,
        RandomSource random
    )
    {
        // Rare islands
        if (random.nextFloat() > 0.03F)
            return;

        int centerX = chunkPos.getMiddleBlockX();

        int centerZ = chunkPos.getMiddleBlockZ();

        int centerY = 70 + random.nextInt(25);

        int radius = 10 + random.nextInt(15);

        for (int dx = -radius; dx <= radius; dx++)
        {
            for (int dz = -radius; dz <= radius; dz++)
            {
                double dist = Math.sqrt(dx * dx + dz * dz);

                if (dist > radius)
                    continue;

                int height =
                    (int)((radius - dist) * 0.8);

                for (int dy = -height; dy <= 0; dy++)
                {
                    BlockPos pos = new BlockPos(
                        centerX + dx,
                        centerY + dy,
                        centerZ + dz
                    );

                    level.setBlock(
                        pos,
                        DimPearls.BETWEEN_STONE.get().defaultBlockState(),
                        3
                    );
                }

                // Void ring
                if (dist > radius - 2)
                {
                    for (int dy = -4; dy <= 4; dy++)
                    {
                        BlockPos airPos = new BlockPos(
                            centerX + dx,
                            centerY + dy,
                            centerZ + dz
                        );

                        level.setBlock(
                            airPos,
                            Blocks.AIR.defaultBlockState(),
                            3
                        );
                    }
                }
            }
        }
    }
}