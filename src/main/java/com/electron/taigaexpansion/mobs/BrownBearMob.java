package com.electron.taigaexpansion.mobs;

import com.electron.taigaexpansion.ModMobs;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class BrownBearMob extends PolarBearEntity{
    private static final DataParameter<Boolean> IS_BEAR_STANDING = EntityDataManager.createKey(BrownBearMob.class, DataSerializers.BOOLEAN);

    public BrownBearMob(EntityType<? extends BrownBearMob> type, World worldIn) {
        super(type, worldIn);
        this.stepHeight = 1.5F;
    }

    public AgeableEntity createChild(AgeableEntity ageable) {
        return ModMobs.BROWN_BEAR.create(this.world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new BrownBearMob.MeleeAttackGoal());
        this.goalSelector.addGoal(1, new BrownBearMob.PanicGoal());
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new BrownBearMob.HurtByTargetGoal());
        this.targetSelector.addGoal(1, new BrownBearMob.GoToBerriesGoal((double)0.8F, 12, 2));
        this.targetSelector.addGoal(2, new BrownBearMob.AttackPlayerGoal());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FoxEntity.class, 4, true, true, (Predicate<LivingEntity>)null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WolfEntity.class, 7, true, true, (Predicate<LivingEntity>)null));
    }
    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_BEAR_STANDING, false);
    }
    public boolean isStanding() {
        return this.dataManager.get(IS_BEAR_STANDING);
    }

    public void setStanding(boolean standing) {
        this.dataManager.set(IS_BEAR_STANDING, standing);
    }

    public class GoToBerriesGoal extends MoveToBlockGoal {
        protected int field_220731_g;

        public GoToBerriesGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
            super(BrownBearMob.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
        }

        public double getTargetDistanceSq() {
            return 4.0D;
        }

        public boolean shouldMove() {
            return this.timeoutCounter % 100 == 0;
        }

        protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
            BlockState blockstate = worldIn.getBlockState(pos);
            return blockstate.getBlock() == Blocks.SWEET_BERRY_BUSH && blockstate.get(SweetBerryBushBlock.AGE) >= 2;
        }

        public void tick() {
            super.tick();
        }

        public boolean shouldExecute() {
            return !BrownBearMob.this.isStanding() && BrownBearMob.this.getAttackTarget() == null && BrownBearMob.this.getRevengeTarget() == null && super.shouldExecute();
        }

        public void startExecuting() {
            this.field_220731_g = 0;
            super.startExecuting();
        }
    }
    class AttackPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        public AttackPlayerGoal() {
            super(BrownBearMob.this, PlayerEntity.class, 20, true, true, (Predicate<LivingEntity>)null);
        }
        public boolean shouldExecute() {
            if (BrownBearMob.this.isChild()) {
                return false;
            } else {
                if (super.shouldExecute()) {
                    for(BrownBearMob brownbearentity : BrownBearMob.this.world.getEntitiesWithinAABB(BrownBearMob.class, BrownBearMob.this.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
                        if (brownbearentity.isChild()) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        protected double getTargetDistance() {
            return super.getTargetDistance() * 0.5D;
        }
    }

    class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
        public HurtByTargetGoal() {
            super(BrownBearMob.this);
        }

        public void startExecuting() {
            super.startExecuting();
            if (BrownBearMob.this.isChild()) {
                this.alertOthers();
                this.resetTask();
            }
        }
        protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
            if (mobIn instanceof BrownBearMob && !mobIn.isChild()) {
                super.setAttackTarget(mobIn, targetIn);
            }
        }
    }
    class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
        public MeleeAttackGoal() {
            super(BrownBearMob.this, 1.15D, true);
        }

        protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
            double d0 = this.getAttackReachSqr(enemy);
            if (distToEnemySqr <= d0 && this.attackTick <= 0) {
                this.attackTick = 30;
                this.attacker.attackEntityAsMob(enemy);
                BrownBearMob.this.setStanding(false);
            } else if (distToEnemySqr <= d0 * 2.0D) {
                if (this.attackTick <= 0) {
                    BrownBearMob.this.setStanding(false);
                    this.attackTick = 20;
                }

                if (this.attackTick <= 10) {
                    BrownBearMob.this.setStanding(true);
                    BrownBearMob.this.playWarningSound();
                }
            } else {
                this.attackTick = 20;
                BrownBearMob.this.setStanding(false);
            }
        }
    }
    class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
        public PanicGoal() {
            super(BrownBearMob.this, 2.0D);
        }

        public boolean shouldExecute() {
            return !BrownBearMob.this.isChild() && !BrownBearMob.this.isBurning() ? false : super.shouldExecute();
        }
    }
}
