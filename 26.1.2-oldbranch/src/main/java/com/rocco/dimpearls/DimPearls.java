package com.rocco.dimpearls;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.rocco.dimpearls.item.DirtPearlItem;
import com.rocco.dimpearls.item.NetherPearlItem;
import com.rocco.dimpearls.item.EndPearlItem;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.BlockItem;



@Mod(DimPearls.MODID)
public class DimPearls {

    public static final String MODID = "dimpearls";
    public static final Logger LOGGER = LogUtils.getLogger();

    // ITEMS REGISTRY
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MODID);

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
            .destroyTime(10.0F)
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
            .lightLevel(state -> 15)
    );
    
    public static final DeferredItem<BlockItem> BETWEEN_CRYSTAL_ITEM =
    ITEMS.registerSimpleBlockItem(
        "between_crystal",
        BETWEEN_CRYSTAL
    );

    public DimPearls(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        modEventBus.addListener(this::addCreative); 
    }

    

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(DIRT_PEARL);
            event.accept(NETHER_PEARL);
        event.accept(END_PEARL);
        event.accept(BETWEEN_STONE_ITEM);
        event.accept(BETWEEN_CRYSTAL_ITEM);
        }
    }

    
}