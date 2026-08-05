package net.somfunambulist.thicket;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ThicketHelper {

    public static final String MOD_ID = "thicket";
    public static final String MOD_NAME = "Thicket";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static ResourceLocation modPrefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static String toTitleCase(String path) {
        final StringBuilder builder = new StringBuilder();

        for (String part : path.split("_")) {
            if (!builder.isEmpty()) {
                builder.append(" ");
            }
            builder.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return builder.toString();
    }

    public static List<Item> getAllModItems() {
        var list = new ArrayList<Item>();
        var modLocations = BuiltInRegistries.ITEM.keySet().stream().filter(r -> r.getNamespace().equals(MOD_ID)).toList();
        for (ResourceLocation location : modLocations) {
            var item = BuiltInRegistries.ITEM.get(location);
            list.add(item);
        }
        return list;
    }
}
