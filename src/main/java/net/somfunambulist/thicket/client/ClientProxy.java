package net.somfunambulist.thicket.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.somfunambulist.thicket.util.CommonProxy;

public class ClientProxy extends CommonProxy {
    @Override
    @OnlyIn(Dist.CLIENT)
    public Level getClientLevel() {
        return Minecraft.getInstance().level;
    }
}
