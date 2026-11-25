package net.somfunambulist.thicket.registry.dynamicpack;

import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.DynamicServerResourceProvider;
import net.mehvahdjukaar.moonlight.api.resources.pack.PackGenerationStrategy;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.somfunambulist.thicket.registry.ModItems;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static net.somfunambulist.thicket.ThicketHelper.path;

//TODO also add client resource provider
public class ServerDynamicResourcesHandler extends DynamicServerResourceProvider {
    public static final Map<ResourceLocation, ResourceLocation> LEAVES_TO_SAPLING_MAP = new HashMap<>();

    public ServerDynamicResourcesHandler() {
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
    protected void regenerateDynamicAssets(Consumer<ResourceGenTask> consumer) {
        consumer.accept((resourceManager, resourceSink) -> {
            for (var leavesType : LeavesTypeRegistry.INSTANCE) {
                var sapling = leavesType.getChild("sapling");
                if (sapling != null) {
                    Block saplingBlock = (Block) sapling;
                    var path = path("blocks/additional_sapling_" + leavesType.getAppendableId().replace("/", "_"));
                    resourceSink.addLootTable(
                            path,
                            LootTable.lootTable().withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1.0F))
                                            .add(LootItem.lootTableItem(saplingBlock.asItem()))
                                            .when(AllOfCondition.allOf(
                                                    LootItemRandomChanceCondition.randomChance(0.03F),
                                                    MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModItems.POCKET_KNIFE.get())))
                                            )
                            ).setParamSet(LootContextParamSets.BLOCK).build()
                    );
                    LEAVES_TO_SAPLING_MAP.put(leavesType.leaves.getLootTable().location(), path);
                }
            }


            /*TODO register RecipeTemplate (that might take a hot minutes)
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

             */


        });
    }
}
