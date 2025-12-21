package net.somfunambulist.thicket.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.somfunambulist.thicket.Thicket;
import net.minecraftforge.fml.common.Mod;
import net.somfunambulist.thicket.item.ModItems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Thicket.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.CLERIC) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.MYRRH_RESIN.get(),3),
                    new ItemStack(Items.EMERALD,1),
                    16, 12, 0.05f));
        }
    }

}