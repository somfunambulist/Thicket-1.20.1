package net.somfunambulist.thicket.client;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.somfunambulist.thicket.Thicket;
import net.somfunambulist.thicket.data.client.ModItemModelProvider;
import net.somfunambulist.thicket.data.client.ModLangProvider;

import java.util.concurrent.CompletableFuture;

@Mod(value = Thicket.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Thicket.MOD_ID, value = Dist.CLIENT)
public class ThicketClient {

    public ThicketClient(IEventBus bus, ModContainer container) {
        bus.addListener(this::gatherData);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {

    }

    public void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(includeClient, new ModLangProvider(packOutput));
        generator.addProvider(includeClient, new ModItemModelProvider(packOutput, fileHelper));
    }
}
