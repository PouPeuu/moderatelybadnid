package com.poupeuu.moderatelybadnid.entities;

import com.ibm.icu.text.Normalizer2;
import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.blocks.GlortBlock;
import com.poupeuu.moderatelybadnid.blocks.blockEntities.GlortBlockEntity;
import com.poupeuu.moderatelybadnid.registers.MBNBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SopoEntity extends Animal implements NeutralMob {
    //private int

    public SopoEntity(EntityType type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    @Override
    protected void registerGoals(){
        goalSelector.addGoal(0, new SopoEntity.SopoFeastUponTheFlowerGoal());
        goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new PanicGoal(this, 2.0));
        goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.GLOW_BERRIES), false));
        goalSelector.addGoal(4, new TemptGoal(this, 1.25, Ingredient.of(Items.SWEET_BERRIES), false));
        goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_21672_) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    class SopoFeastUponTheFlowerGoal extends Goal {
        private BlockPos flowerPos = null;
        private boolean hasDelicious = false;

        public SopoFeastUponTheFlowerGoal(){

        }

        private BlockPos findFlower(float distance) {
            BlockPos blockPos = SopoEntity.this.blockPosition();
            Level level = SopoEntity.this.level();
            int centerX = blockPos.getX();  // Replace with the actual center coordinates
            int centerY = blockPos.getY();
            int centerZ = blockPos.getZ();
            int cubeSize = 10;

            List<BlockPos> blocks = new ArrayList<BlockPos>();

            for (int x = centerX - cubeSize/2; x <= centerX + cubeSize/2; x++) {
                for (int y = centerY - cubeSize/2; y <= centerY + cubeSize/2; y++) {
                    for (int z = centerZ - cubeSize/2; z <= centerZ + cubeSize/2; z++) {
                        BlockPos block = new BlockPos(x, y, z);
                        BlockState blockState = level.getBlockState(block);
                        if (blockState.is(MBNBlocks.GLORT.get()) && blockState.getValue(GlortBlock.HALF) == DoubleBlockHalf.UPPER && ((GlortBlockEntity)level.getBlockEntity(block)).getGleemAmount() != 0){
                            blocks.add(block);
                        }
                    }
                }
            }

            double smallestDistance = 999;
            BlockPos closestBlock = null;
            for (int i = 0; i < blocks.size(); i++) {
                double dist = blockPos.distSqr(blocks.get(i));
                if (dist < smallestDistance) {
                    smallestDistance = dist;
                    closestBlock = blocks.get(i);
                }
            }

            return closestBlock;
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public void tick(){
            Level level = SopoEntity.this.level();
            if (flowerPos == null) {
                flowerPos = findFlower(10);
            } else {
                BlockState blockState = level.getBlockState(flowerPos);
                if (blockState.is(MBNBlocks.GLORT.get()) && blockState.getValue(GlortBlock.HALF) == DoubleBlockHalf.UPPER) {
                    Vec3 center = flowerPos.getCenter();
                    SopoEntity.this.navigation.moveTo(SopoEntity.this.navigation.createPath(flowerPos, 0), 1.0D);
                    if (center.distanceToSqr(SopoEntity.this.position()) <= 3) {
                        GlortBlockEntity glortBlockEntity = (GlortBlockEntity)level.getBlockEntity(flowerPos);
                        if (glortBlockEntity.getGleemAmount() == 0) {
                            flowerPos = null;
                        } else {
                            float gleemExtracted = glortBlockEntity.extractGleem((float) Math.random() * 10);
                        }
                    }
                } else {
                    flowerPos = null;
                }
            }
        }

    }
}
