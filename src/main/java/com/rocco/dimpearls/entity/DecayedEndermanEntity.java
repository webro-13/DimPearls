package com.rocco.dimpearls.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;

public class DecayedEndermanEntity extends EnderMan
{
    private long nextAggroChange = 2;
private boolean unstableAggro = false;

    public DecayedEndermanEntity(
        EntityType<? extends EnderMan> type,
        Level level
    )
    {
        
        super(type, level);
        
        this.xpReward = 12;
    }
    
    public boolean isLookingAtMe(Player player)
{
    return false;
}

    @Override

    protected void registerGoals()
{
    super.registerGoals();

    // REMOVE vanilla hostile target goals
    this.targetSelector.removeAllGoals(
        goal ->
            goal.getClass().getSimpleName()
                .contains("NearestAttackableTargetGoal")
    );

    this.targetSelector.removeAllGoals(
        goal ->
            goal.getClass().getSimpleName()
                .contains("ResetUniversalAngerTargetGoal")
    );
}

    public void tick()
{
    super.tick();

    if (!level().isClientSide())
    {

        Player targetPlayer =
    level().getNearestPlayer(
        this,
        16
    );

// if (
//     targetPlayer != null &&
//     targetPlayer.getHealth() <= 6.0F
// )
// {
//     setTarget(null);

//     unstableAggro = false;

//     navigation.stop();
// }

        long time = level().getGameTime();

        if (time >= nextAggroChange)
        {
            nextAggroChange = time + 300 + random.nextInt(500);

            unstableAggro = !unstableAggro;

            if (unstableAggro)
            {
                Player player =
                    level().getNearestPlayer(
                        this,
                        11
                    );

                if (
                    player != null &&
                    random.nextFloat() < 0.35F
                )
                {
                    if (random.nextFloat() < 0.4F)
{
    setTarget(player);
}
else
{
    navigation.stop();
}
                }
            }
        //     else
        //     {
        //         setTarget(null);

        //         setLastHurtByMob(null);

        //         navigation.stop();
                
        //     }
        // }

// if (!unstableAggro)
// {
//     setTarget(null);

//     navigation.stop();

//     if (getTarget() != null)
//     {
//         setTarget(null);
//     }
// }

if (level().getMaxLocalRawBrightness(blockPosition()) > 8)
{
    setTarget(null);

    unstableAggro = false;

    navigation.stop();
}
        
    if (random.nextInt(300) == 0)
{
    teleport();
}
    }
        


if (random.nextInt(200) == 0)
{
    navigation.stop();
}

}
}
    
protected boolean teleport()
{
    // 40% chance to fail
    if (random.nextFloat() < 0.4F)
    {
        return false;
    }

    // short glitch teleport
    if (random.nextFloat() < 0.5F)
    {
        double x =
            getX() +
            (random.nextDouble() - 0.5D) * 6;

        double y =
            getY() +
            (random.nextDouble() - 0.5D) * 2;

        double z =
            getZ() +
            (random.nextDouble() - 0.5D) * 6;

        return randomTeleport(
            x,
            y,
            z,
            true
        );
    }

    return super.teleport();
}

    public static AttributeSupplier.Builder createAttributes()
    {
        return EnderMan.createAttributes()
            .add(Attributes.MAX_HEALTH, 37.0D)
            .add(Attributes.ATTACK_DAMAGE, 8.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }
}