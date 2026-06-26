package com.rocco.dimpearls;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.rocco.dimpearls.item.DirtPearlItem;
import com.rocco.dimpearls.item.NetherPearlItem;
import com.rocco.dimpearls.item.EndPearlItem;
import com.rocco.dimpearls.item.VoidPearlItem;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import com.rocco.dimpearls.entity.DecayedEndermanEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.minecraft.world.level.biome.MobSpawnSettings;



@Mod(DimPearls.MODID)
public class DimPearls {

    public static final String MODID = "dimpearls";
    public static final Logger LOGGER = LogUtils.getLogger();

    // ITEMS REGISTRY
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MODID);

   public static final DeferredRegister.Entities ENTITIES =
    DeferredRegister.createEntities(MODID);

    // BLOCKS REGISTRY
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MODID);

    // Example: Dirt Pearl (we will replace logic later)
  public static final DeferredItem<DirtPearlItem> DIRT_PEARL =
    ITEMS.registerItem(
        "dirt_pearl",
        DirtPearlItem::new,
        () -> new Item.Properties().stacksTo(1)
        .durability(32)
    );

    public static final DeferredItem<VoidPearlItem> VOID_PEARL =
    ITEMS.registerItem(
        "void_pearl",
        VoidPearlItem::new,
        () -> new Item.Properties().stacksTo(1)
        .durability(8)
    );

    public static final DeferredItem<NetherPearlItem> NETHER_PEARL =
    ITEMS.registerItem(
        "nether_pearl",
        NetherPearlItem::new,
        () -> new Item.Properties().stacksTo(1)
        .durability(32)
    );

    public static final DeferredItem<EndPearlItem> END_PEARL =
    ITEMS.registerItem(
        "end_pearl",
        EndPearlItem::new,
        () -> new Item.Properties().stacksTo(1)
        .durability(16)
    );
    
public static final DeferredBlock<Block> BETWEEN_STONE =
    BLOCKS.registerBlock(
        "between_stone",
        Block::new,
        () -> BlockBehaviour.Properties.of()
            .destroyTime(5.0F)
            .requiresCorrectToolForDrops()
    );
    
    public static final DeferredItem<BlockItem> BETWEEN_STONE_ITEM =
    ITEMS.registerSimpleBlockItem(
        "between_stone",
        BETWEEN_STONE
    );

    public static final DeferredBlock<Block> BETWEEN_CRYSTAL =
    BLOCKS.registerBlock(
        "between_crystal",
        Block::new,
        () -> BlockBehaviour.Properties.of()
            .destroyTime(4.0F)
            .noCollision()
            .lightLevel(state -> 12)
            .destroyTime(7.0F)
            .requiresCorrectToolForDrops()
    );
    
    public static final DeferredItem<BlockItem> BETWEEN_CRYSTAL_ITEM =
    ITEMS.registerSimpleBlockItem(
        "between_crystal",
        BETWEEN_CRYSTAL
    );

public static final DeferredHolder<
    EntityType<?>,
    EntityType<DecayedEndermanEntity>
> DECAYED_ENDERMAN =
    ENTITIES.register(
        "decayed_enderman",
        () -> EntityType.Builder
            .of(
                DecayedEndermanEntity::new,
                MobCategory.MONSTER
            )
            .sized(0.6F, 2.9F)
            .build(
    ResourceKey.create(
        Registries.ENTITY_TYPE,
        Identifier.parse(
            MODID + ":decayed_enderman"
        )
    )
)
    );

    public DimPearls(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENTITIES.register(modEventBus);
        modEventBus.addListener(this::registerAttributes);
        modEventBus.addListener(this::addCreative);
    }

    

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(DIRT_PEARL);
            event.accept(NETHER_PEARL);
        event.accept(END_PEARL);
        event.accept(VOID_PEARL);
        event.accept(BETWEEN_STONE_ITEM);
        event.accept(BETWEEN_CRYSTAL_ITEM);
        }
    }

    private void registerAttributes(
    EntityAttributeCreationEvent event
)
{
    event.put(
        DECAYED_ENDERMAN.get(),
        DecayedEndermanEntity
            .createAttributes()
            .build()
    );
}

private void registerSpawnPlacements(
    RegisterSpawnPlacementsEvent event
)
{
    event.register(
        DECAYED_ENDERMAN.get(),

        SpawnPlacementTypes.ON_GROUND,

        Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,

        Monster::checkMonsterSpawnRules,

        RegisterSpawnPlacementsEvent.Operation.REPLACE
    );
}
    
}