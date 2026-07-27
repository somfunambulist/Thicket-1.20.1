package net.somfunambulist.thicket;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.somfunambulist.thicket.registry.ModBlocks;
import net.somfunambulist.thicket.registry.ModCreativeTabs;
import net.somfunambulist.thicket.registry.ModItems;
import org.slf4j.Logger;

@Mod(Thicket.MOD_ID)
public class Thicket {
    public static final String MOD_ID = "thicket";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Thicket(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.REG.register(modEventBus);
        ModBlocks.REG.register(modEventBus);
        ModCreativeTabs.REG.register(modEventBus);
    }
}
