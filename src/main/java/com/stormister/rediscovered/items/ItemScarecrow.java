package com.stormister.rediscovered.items;

import com.stormister.rediscovered.Rediscovered;
import com.stormister.rediscovered.entity.EntityScarecrow;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemScarecrow extends Item {
	private final String name = "Scarecrow";

	public ItemScarecrow() {
		super();
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(Rediscovered.modid + "_" + name);
		maxStackSize = 1;
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			EntityScarecrow scarecrow = (EntityScarecrow) ItemMonsterPlacer.spawnCreature(world,
					EntityList.classToStringMapping.get(EntityScarecrow.class), pos.getX() + 0.5, pos.getY() + 1,
					pos.getZ() + 0.5);

			scarecrow.rotationYaw = player.rotationYaw;
			scarecrow.setRotationYawHead(player.rotationYaw);
			scarecrow.setRenderYawOffset(player.rotationYaw);

			--itemStack.stackSize;
		} else {
			return false;
		}
		return true;
	}
}
