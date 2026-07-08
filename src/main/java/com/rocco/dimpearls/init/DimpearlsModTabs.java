/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.rocco.dimpearls.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import com.rocco.dimpearls.DimpearlsMod;

@EventBusSubscriber
public class DimpearlsModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DimpearlsMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DIMENSION_ITEMS = REGISTRY.register("dimension_items",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.dimpearls.dimension_items")).icon(() -> new ItemStack(DimpearlsModItems.DIRT_PEARL.get())).displayItems((parameters, tabData) -> {
				tabData.accept(DimpearlsModItems.DIRT_PEARL.get());
				tabData.accept(DimpearlsModItems.NETHER_PEARL.get());
				tabData.accept(DimpearlsModItems.END_PEARL.get());
				tabData.accept(DimpearlsModBlocks.BETWEENSTONE.get().asItem());
				tabData.accept(DimpearlsModItems.VOID_PEARL.get());
				tabData.accept(DimpearlsModItems.BANISH_HAMMER_NETHER.get());
				tabData.accept(DimpearlsModItems.BANISH_HAMMER_END.get());
				tabData.accept(DimpearlsModItems.BANISH_HAMMER_DIRT.get());
				tabData.accept(DimpearlsModItems.BANISH_HAMMER_VOID.get());
				tabData.accept(DimpearlsModBlocks.BETWEENCRYSTAL.get().asItem());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(DimpearlsModItems.DIRT_PEARL.get());
			tabData.accept(DimpearlsModItems.NETHER_PEARL.get());
			tabData.accept(DimpearlsModItems.END_PEARL.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(DimpearlsModBlocks.BETWEENSTONE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
			tabData.accept(DimpearlsModBlocks.BETWEENSTONE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(DimpearlsModBlocks.BETWEENSTONE.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(DimpearlsModItems.DECAYED_ENDERMAN_SPAWN_EGG.get());
			tabData.accept(DimpearlsModItems.HEROBRINE_SPAWN_EGG.get());
		}
	}
}