package com.poupeuu.moderatelybadnid.blocks;

import com.poupeuu.moderatelybadnid.ModeratelyBadNid;
import com.poupeuu.moderatelybadnid.registers.ModBlockEntities;
import com.poupeuu.moderatelybadnid.blocks.blockEntities.ToasterBlockEntity;
import com.poupeuu.moderatelybadnid.registers.ModSounds;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Optional;

public class ToasterBlock extends BaseEntityBlock{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty TOASTING = BooleanProperty.create("toasting");

    protected static final VoxelShape SHAPE_Z = Block.box(4,0,1, 12, 11,15);
    protected static final VoxelShape SHAPE_X = Block.box(1,0,4, 15, 11,12);
    public ToasterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite()).setValue(TOASTING, false);
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TOASTING);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext){
        //return SHAPE;
        switch ((Direction)blockState.getValue(FACING)) {
            case EAST, WEST:
                return SHAPE_X;
            default:
                return SHAPE_Z;
         }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ToasterBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            // todo: add particles
            return null;
        } else {
            return blockState.getValue(TOASTING) ? createTickerHelper(blockEntityType, ModBlockEntities.TOASTER_BLOCK_ENTITY.get(), ToasterBlockEntity::cookTick) : null;
        }
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof ToasterBlockEntity toasterBlockEntity){
            ItemStack itemStack = player.getItemInHand(interactionHand);
            if (itemStack.isEmpty() && !toasterBlockEntity.getItems().isEmpty()) {
                blockState = blockState.setValue(TOASTING, true);
                level.setBlockAndUpdate(blockPos, blockState);
                level.playSound(null, blockPos, ModSounds.TOASTER_START.get(), SoundSource.MASTER);
                return InteractionResult.SUCCESS;
            }
            Optional<CampfireCookingRecipe> optional = toasterBlockEntity.getCookableRecipe(itemStack);
            if(optional.isPresent()) {
                if (!level.isClientSide()) {
                    ItemStack epicStack = itemStack.copy();
                    epicStack.setCount(1);
                    toasterBlockEntity.addToast(epicStack, optional.get().getCookingTime());
                    itemStack.setCount(itemStack.getCount()-1);
                    return InteractionResult.SUCCESS;
                }

                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
