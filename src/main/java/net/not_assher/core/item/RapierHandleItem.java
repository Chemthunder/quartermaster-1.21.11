package net.not_assher.core.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.not_assher.core.index.ModItems;

public class RapierHandleItem extends Item {
    public RapierHandleItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());

        if (player != null) {
            if (state.isOf(Blocks.GRINDSTONE)) {
                if (stack.isOf(ModItems.RUINED_HANDLE)) {
                    if (player.isSneaking()) {
                        stack.decrement(1);
                        player.giveItemStack(new ItemStack(ModItems.RAPIER_HANDLE));
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }
}
