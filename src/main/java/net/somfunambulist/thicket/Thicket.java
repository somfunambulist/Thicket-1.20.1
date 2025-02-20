package net.somfunambulist.thicket;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.somfunambulist.thicket.block.ModBlocks;
import net.somfunambulist.thicket.block.compat.DecorativeBlocksBlocks;
import net.somfunambulist.thicket.block.compat.EcologicsBlocks;
import net.somfunambulist.thicket.block.entity.ModBlockEntities;
import net.somfunambulist.thicket.enchantment.ModEnchantments;
import net.somfunambulist.thicket.entity.ModEntities;
import net.somfunambulist.thicket.entity.client.MistletoeArrowRenderer;
import net.somfunambulist.thicket.entity.client.ModBoatRenderer;
import net.somfunambulist.thicket.item.ModCreativeModeTabs;
import net.somfunambulist.thicket.item.ModItemProperties;
import net.somfunambulist.thicket.item.ModItems;
import net.minecraft.client.renderer.Sheets;
import net.somfunambulist.thicket.potion.BetterBrewingRecipe;
import net.somfunambulist.thicket.tags.ModBlockTags;
import net.somfunambulist.thicket.util.CommonProxy;
import net.somfunambulist.thicket.client.ClientProxy;
import net.somfunambulist.thicket.util.ModWoodTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Thicket.MOD_ID)
public class Thicket {
    public static final CommonProxy Proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static final String MOD_ID = "thicket";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Thicket() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEnchantments.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        if (ModList.get().isLoaded(ModCompat.DECORATIVE_BLOCKS_ID)) {
            DecorativeBlocksBlocks.register(modEventBus);
        }
        if (ModList.get().isLoaded(ModCompat.ECOLOGICS_ID)) {
            EcologicsBlocks.register(modEventBus);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.HAZELNUT.get(), 0.20F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.HAZEL_SAPLING.get().asItem(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.HAZEL_LEAVES.get().asItem(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.SASSAFRAS_SAPLING.get().asItem(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.SASSAFRAS_LEAVES.get().asItem(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.FIREMILK_MUSHROOM.get().asItem(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ModItems.MISTLETOE.get().asItem(), 0.30F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.MISTLETOE_LEAVES.get().asItem(), 0.30F);

            BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.WATER, ModBlocks.FIREMILK_MUSHROOM.get().asItem(), Potions.AWKWARD));
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        /*
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.DOWSING_ROD);
        }
         */
    }

    public static ResourceLocation modPrefix(String path) {
        return new ResourceLocation(Thicket.MOD_ID, path);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ModItemProperties.addCustomItemProperties();
                Sheets.addWoodType(ModWoodTypes.HAZEL);

                EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
                EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
                EntityRenderers.register(ModEntities.MISTLETOE_ARROW.get(), MistletoeArrowRenderer::new);
            });
        }
    }
}
