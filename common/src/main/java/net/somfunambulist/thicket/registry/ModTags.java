package net.somfunambulist.thicket.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import static net.somfunambulist.thicket.ThicketHelper.path;

public class ModTags {

    public static class Items {
        public static final TagKey<Item> FLINT_LIKE = modTag("flint_like");

        static TagKey<Item> modTag(String id) {
            return createModTag(Registries.ITEM, id);
        }

        static TagKey<Item> commonTag(String id) {
            return createOtherTag(Registries.ITEM, "c", id);
        }
    }

    public static class Blocks {


        static TagKey<Block> modTag(String id) {
            return createModTag(Registries.BLOCK, id);
        }
    }

    public static class Biomes {

        static TagKey<Biome> modTag(String id) {
            return createModTag(Registries.BIOME, id);
        }
    }

    public static <T> TagKey<T> createModTag(ResourceKey<Registry<T>> resourceKey, String id) {
        return TagKey.create(resourceKey, path(id));
    }

    public static <T> TagKey<T> createOtherTag(ResourceKey<Registry<T>> resourceKey, String namespace, String id) {
        return TagKey.create(resourceKey, ResourceLocation.fromNamespaceAndPath(namespace, id));
    }
}
