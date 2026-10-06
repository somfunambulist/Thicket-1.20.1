package net.somfunambulist.thicket.registry.dynamicpack;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicServerResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.minecraft.resources.ResourceLocation;
import net.somfunambulist.thicket.ThicketHelper;
import net.somfunambulist.thicket.registry.ModBlockSets;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static net.somfunambulist.thicket.ThicketHelper.path;

//TODO also add client resource provider
public class ServerDynamicResourcesHandler extends DynamicServerResourceProvider {

    public ServerDynamicResourcesHandler() {
        super(path("generated_pack"), PackGenerationStrategy.REGEN_ON_EVERY_RELOAD); //TODO usually this would be turned into a config it seems
    }

    @Override
    public boolean needsToRegenerate() {
        return super.needsToRegenerate() || PlatHelper.isDev();
    }

    @Override
    protected Collection<String> gatherSupportedNamespaces() {
        return List.of("minecraft");
    }

    //TODO doesn't seem to work yet
    @Override
    protected void regenerateDynamicAssets(Consumer<ResourceGenTask> consumer) {
        consumer.accept((resourceManager, resourceSink) -> {
            AtomicInteger am = new AtomicInteger();
            ModBlockSets.CARVING_BLOCKS.forEach((wood, block) -> {
                if (wood == VanillaWoodTypes.OAK) return; //Because we use oak as the template for recipe generation
                if (wood.getChild("carving") == null) {
                    ThicketHelper.LOGGER.debug("Could not find carving for wood {}. Skipping carving recipe generation", wood);
                    return;
                }
                try {
                    am.addAndGet(1);
                    resourceSink.addBlockTypeSwapRecipe(resourceManager, path("pocket_knife_block/carving_oak"), VanillaWoodTypes.OAK, wood,
                            path("pocket_knife_block/carving" + "_" + wood.getTypeName()));
                } catch (Exception e) {
                    ThicketHelper.LOGGER.error("Failed to generate recipe for carving {}:", block, e);
                }
            });
            ThicketHelper.LOGGER.info("Generated {} carving recipes", am.get());
        });
    }
}
