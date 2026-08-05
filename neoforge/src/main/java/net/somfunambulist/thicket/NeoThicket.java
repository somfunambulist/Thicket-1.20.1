package net.somfunambulist.thicket;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ThicketHelper.MOD_ID)
public class NeoThicket {


    public NeoThicket(IEventBus bus) {
        ThicketMain.init();
    }
}
