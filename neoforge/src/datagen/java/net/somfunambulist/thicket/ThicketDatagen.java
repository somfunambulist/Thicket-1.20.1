package net.somfunambulist.thicket;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.somfunambulist.thicket.client.ModBlockProvider;
import net.somfunambulist.thicket.client.ModItemModelProvider;
import net.somfunambulist.thicket.client.ModLangProvider;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ThicketHelper.MOD_ID, value = Dist.CLIENT)
public class ThicketDatagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        boolean includeClient = event.includeClient();
        boolean includeServer = event.includeServer();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(includeClient, new ModLangProvider(packOutput));

        generator.addProvider(includeClient, new ModBlockProvider(packOutput, fileHelper));
        generator.addProvider(includeClient, new ModItemModelProvider(packOutput, fileHelper));

    }
}
