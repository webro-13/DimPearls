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
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


@Mod(DimPearls.MODID)
public class DimPearls {

    public static final String MODID = "dimpearls";
    public static final Logger LOGGER = LogUtils.getLogger();

    // ITEMS REGISTRY
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(MODID);

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

    public DimPearls(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(DIRT_PEARL);
            event.accept(NETHER_PEARL);
        event.accept(END_PEARL);
        }
    }

    
}