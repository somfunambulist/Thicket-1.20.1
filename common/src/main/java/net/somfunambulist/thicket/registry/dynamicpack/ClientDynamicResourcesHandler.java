package net.somfunambulist.thicket.registry.dynamicpack;

import net.mehvahdjukaar.moonlight.api.events.AfterLanguageLoadEvent;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicClientResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.textures.Palette;
import net.mehvahdjukaar.moonlight.api.resources.textures.Respriter;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureImage;
import net.mehvahdjukaar.moonlight.api.resources.textures.TextureOps;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.somfunambulist.thicket.registry.ModBlockSets;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static net.somfunambulist.thicket.ThicketHelper.LOGGER;
import static net.somfunambulist.thicket.ThicketHelper.path;

public class ClientDynamicResourcesHandler extends DynamicClientResourceProvider {

    public ClientDynamicResourcesHandler() {
        super(path("generated_pack"), PackGenerationStrategy.REGEN_ON_EVERY_RELOAD); //TODO usually this would be turned into a config it seems
    }

    @Override
    public boolean needsToRegenerate() {
        return super.needsToRegenerate() || PlatHelper.isDev();
    }

    @Override
    protected Collection<String> gatherSupportedNamespaces() {
        return List.of("minecraft", "thicket");
    }

    @Override
    protected void regenerateDynamicAssets(Consumer<ResourceGenTask> executor) {
        executor.accept(((manager, sink) -> {
            StaticResource carvingItemModel = StaticResource.getOrThrow(manager, ResType.ITEM_MODELS.getPath(path("carving_oak")));
            StaticResource carvingBlockModel = StaticResource.getOrThrow(manager, ResType.BLOCK_MODELS.getPath(path("carving_temp")));
            StaticResource carvingHorizontalBlockModel = StaticResource.getOrThrow(manager, ResType.BLOCK_MODELS.getPath(path("carving_temp_horizontal")));

            StaticResource carvingBlockstate = StaticResource.getOrThrow(manager, ResType.BLOCKSTATES.getPath(path("carving_oak")));

            ModBlockSets.CARVING_BLOCKS.forEach(((wood, carving) -> {
                if (wood == VanillaWoodTypes.OAK) return;
                String id = Utils.getID(carving).getPath();
                var strippedBlock = wood.getBlockOfThis("stripped_log");
                if (strippedBlock == null) return;

                ResourceLocation strippedLocTop;
                ResourceLocation strippedLocSide;
                try {
                    strippedLocTop = RPUtils.findFirstBlockTextureLocation(manager, strippedBlock, s -> s.endsWith("_top"));
                    strippedLocSide = RPUtils.findFirstBlockTextureLocation(manager, strippedBlock, s -> !s.endsWith("_top"));

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    sink.addSimilarJsonResource(manager, carvingItemModel, "carving_oak", id);
                    sink.addSimilarJsonResource(manager, carvingBlockstate, "carving_oak", id);
                    sink.addSimilarJsonResource(manager, carvingBlockModel, string -> string
                            .replace("carving_temp", id)
                            .replace("top_temp", strippedLocTop.toString())
                    );
                    sink.addSimilarJsonResource(manager, carvingHorizontalBlockModel, string -> string
                            .replace("carving_temp", id)
                            .replace("top_temp", strippedLocTop.toString())
                    );

                } catch (Exception e) {
                    LOGGER.error("Failed to generate carving models for {} : ", carving, e);
                }

                var textureRL = path("block/" + id + "_side");
                sink.addTextureUnlessPresent(manager, textureRL, () -> {
                    try (TextureImage baseTexture = TextureImage.open(manager, strippedLocSide);
                         TextureImage overlayTexture = TextureImage.open(manager, path("block/compat/generic_carving"))
                    ) {
                        Respriter respriter = Respriter.of(overlayTexture);
                        Palette palette = Palette.fromImage(baseTexture);

                        TextureImage newImage = baseTexture.makeCopy();
                        boolean wasOpen = newImage.isAllocated();
                        TextureOps.applyOverlay(newImage, respriter.recolor(palette));
                        if (wasOpen && !newImage.isAllocated()) {
                            throw new RuntimeException("Texture was closed during overlay application");
                        }
                        return newImage;

                    } catch (Exception ex) {
                        LOGGER.error("Could not generate carving block texture for {}", wood);
                    }
                    return null;
                });
            }));
        }));
    }

    @Override
    public void addDynamicTranslations(AfterLanguageLoadEvent lang) {
        ModBlockSets.CARVING_BLOCKS.forEach((wood, carving) ->
            LangBuilder.addDynamicEntry(lang, "block.thicket.carving", wood, carving)
        );
    }
}
