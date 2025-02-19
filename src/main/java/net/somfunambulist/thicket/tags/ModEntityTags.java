package net.somfunambulist.thicket.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.somfunambulist.thicket.Thicket;

import javax.swing.text.html.parser.Entity;

public class ModEntityTags {
    public static final TagKey<EntityType<?>> OCCULT_TARGET = TagKey.create(Registries.ENTITY_TYPE, Thicket.modPrefix("occult_target"));

}
