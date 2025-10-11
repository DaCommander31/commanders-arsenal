package dev.dacommander31.cmda.entity;

import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;


public class LaserBoltEntity extends AbstractHurtingProjectile {

    public LaserBoltEntity(EntityType<? extends LaserBoltEntity> entityType, Level level) {
        super(entityType, level);
    }
    public LaserBoltEntity(
            EntityType<? extends AbstractHurtingProjectile> entityType,
            double x,
            double y,
            double z,
            Level level
    ) {
        super(entityType, level);
        this.setPos(x, y, z);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    protected void onHit(HitResult result) {
        this.kill();
    }

    @Override
    public void tick() {
        this.level().addParticle(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
    }
}
