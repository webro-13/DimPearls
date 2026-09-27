package com.rocco.dimpearls.worldgen;

import com.rocco.dimpearls.DimpearlsMod;
import com.rocco.dimpearls.init.DimpearlsModBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DimpearlsMod.MODID)
public class BetweenTerrainHandler {

@SubscribeEvent
public static void onChunkLoad(ChunkEvent.Load event)
{
    if (!(event.getLevel() instanceof ServerLevel serverLevel))
        return;

    ResourceKey<Level> betweenKey = ResourceKey.create(
        Registries.DIMENSION,
        new ResourceLocation(DimpearlsMod.MODID, "the_between")
    );

    // Only run in The Between
    if (!serverLevel.dimension().equals(betweenKey))
        return;

    LevelChunk chunk = (LevelChunk) event.getChunk();

    ChunkPos chunkPos = chunk.getPos();

    // Stable deterministic random
    RandomSource random =
        RandomSource.create(chunkPos.hashCode());

    generatePillars(serverLevel, chunkPos, random);

    generateCrystalPatches(serverLevel, chunkPos, random);

    generateVoidPit(serverLevel, chunkPos, random);

    generateIsland(serverLevel, chunkPos, random);
}

    private static void generatePillars(
        ServerLevel level,
        ChunkPos chunkPos,
        RandomSource random
    ) {

        // 20% chance per chunk
        if (random.nextFloat() > 0.2F)
            return;

        int x = chunkPos.getMiddleBlockX();
        int z = chunkPos.getMiddleBlockZ();

        int baseY = 45 + random.nextInt(15);
        int height = 15 + random.nextInt(25);
        int radius = 2 + random.nextInt(3);

        for (int y = baseY; y < baseY + height; y++) {

            int currentRadius = Math.max(
                1,
                radius - ((y - baseY) / 10)
            );

            for (int dx = -currentRadius; dx <= currentRadius; dx++) {

                for (int dz = -currentRadius; dz <= currentRadius; dz++) {

                    if (dx * dx + dz * dz <= currentRadius * currentRadius) {

                        BlockPos pos = new BlockPos(
                            x + dx,
                            y,
                            z + dz
                        );

                        BlockState state =
                            DimpearlsModBlocks.BETWEENSTONE.get()
                                .defaultBlockState();

                        level.setBlock(pos, state, 2);
                    }
                }
            }
        }
    }

    private static void generateCrystalPatches(
        ServerLevel level,
        ChunkPos chunkPos,
        RandomSource random
    ) {

        int count = 2 + random.nextInt(3);

        for (int i = 0; i < count; i++) {

            int x =
                chunkPos.getMinBlockX()
                + random.nextInt(16);

            int z =
                chunkPos.getMinBlockZ()
                + random.nextInt(16);

            int y = 50 + random.nextInt(20);

            BlockPos pos = new BlockPos(x, y, z);

            if (level.isEmptyBlock(pos)) {

                BlockState state =
                    DimpearlsModBlocks.BETWEENCRYSTAL.get()
                        .defaultBlockState();

                level.setBlock(pos, state, 2);
            }
        }
    }

private static void generateVoidPit(
        ServerLevel level,
        ChunkPos chunkPos,
        RandomSource random
    ) {

        // Rare
        if (random.nextFloat() > 0.08F)
            return;

        // Keep the entire operation inside this chunk.
        int radius = 4 + random.nextInt(5); // 4-8 blocks

        int centerX =
            chunkPos.getMinBlockX()
            + 4
            + random.nextInt(8);

        int centerZ =
            chunkPos.getMinBlockZ()
            + 4
            + random.nextInt(8);

        // Limit the vertical work.
        int minY = 20;
        int maxY = 90;

        for (int x = chunkPos.getMinBlockX();
             x < chunkPos.getMinBlockX() + 16;
             x++) {

            for (int z = chunkPos.getMinBlockZ();
                 z < chunkPos.getMinBlockZ() + 16;
                 z++) {

                int dx = x - centerX;
                int dz = z - centerZ;

                if (dx * dx + dz * dz > radius * radius)
                    continue;

                for (int y = minY; y <= maxY; y++) {

                    BlockPos pos = new BlockPos(x, y, z);

                    level.setBlock(
                        pos,
                        Blocks.AIR.defaultBlockState(),
                        2
                    );
                }
            }
        }
    }

    private static void generateIsland(
        ServerLevel level,
        ChunkPos chunkPos,
        RandomSource random
    ) {

        if (random.nextFloat() > 0.05F)
            return;

        int centerX =
            chunkPos.getMiddleBlockX();

        int centerZ =
            chunkPos.getMiddleBlockZ();

        int centerY = 90 + random.nextInt(40);

        int radius = 6 + random.nextInt(8);

        for (int x = -radius; x <= radius; x++) {

            for (int z = -radius; z <= radius; z++) {

                for (int y = -radius / 2; y <= radius / 2; y++) {

                    double dist =
                        (x * x)
                        + (z * z)
                        + (y * y * 2);

                    if (dist <= radius * radius) {

                        BlockPos pos = new BlockPos(
                            centerX + x,
                            centerY + y,
                            centerZ + z
                        );

                        BlockState state =
                            DimpearlsModBlocks.BETWEENSTONE.get()
                                .defaultBlockState();

                        level.setBlock(pos, state, 2);
                    }
                }
            }
        }
    }
}