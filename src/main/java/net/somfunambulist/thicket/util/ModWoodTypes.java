package net.somfunambulist.thicket.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.somfunambulist.thicket.Thicket;

public class ModWoodTypes {
    public static final WoodType HAZEL = WoodType.register(new WoodType(Thicket.MOD_ID + ":hazel", BlockSetType.OAK));
    public static final WoodType MYRRH = WoodType.register(new WoodType(Thicket.MOD_ID + ":myrrh", BlockSetType.OAK));
    public static final WoodType SASSAFRAS = WoodType.register(new WoodType(Thicket.MOD_ID + ":sassafras", BlockSetType.OAK));
}
