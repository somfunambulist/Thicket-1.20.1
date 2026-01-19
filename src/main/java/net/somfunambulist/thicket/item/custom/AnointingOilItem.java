package net.somfunambulist.thicket.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.somfunambulist.thicket.effect.ModEffects;

import java.util.List;

public class AnointingOilItem extends Item {

    public AnointingOilItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (entity.level().isClientSide) return InteractionResult.PASS;
        if (entity.isBaby()) {
            if (!entity.hasEffect(ModEffects.ANOINTED_EFFECT.get())) {
                entity.addEffect(new MobEffectInstance(MobEffects.GLOWING,40,0,true,false));
                var effect = new MobEffectInstance(ModEffects.ANOINTED_EFFECT.get(),-1,0,false,false);
                effect.setCurativeItems(List.of());
                entity.addEffect(effect);
                if (entity instanceof Mob) ((Mob) entity).setPersistenceRequired();
                var level = player.level();
                level.playSound((Player) null, BlockPos.containing(entity.position()), SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS, 0.3F, 0.7F);
                level.playSound((Player) null, BlockPos.containing(entity.position()), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.5F, 0.2F);
                player.setItemInHand(hand, Items.GLASS_BOTTLE.getDefaultInstance());
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

}
