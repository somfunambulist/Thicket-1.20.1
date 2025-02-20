package net.somfunambulist.thicket.entity.custom;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.somfunambulist.thicket.entity.ModEntities;
import net.somfunambulist.thicket.item.ModItems;

public class MistletoeArrow extends AbstractArrow {

    public MistletoeArrow(EntityType<? extends MistletoeArrow> type, Level worldIn) {
        super(type, worldIn);
    }

    public MistletoeArrow(Level worldIn, double x, double y, double z) {
        super(ModEntities.MISTLETOE_ARROW.get(), x, y, z, worldIn);
    }

    public MistletoeArrow(PlayMessages.SpawnEntity spawnEntity, Level world) {
        this(ModEntities.MISTLETOE_ARROW.get(), world);
    }

    public MistletoeArrow(Level worldIn, LivingEntity shooter) {
        super(ModEntities.MISTLETOE_ARROW.get(), shooter, worldIn);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.MISTLETOE.get());
    }

    /*
    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        this.setSoundEvent(this.getDefaultHitGroundSoundEvent());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
    */
}