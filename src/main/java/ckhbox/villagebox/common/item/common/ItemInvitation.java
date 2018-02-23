package ckhbox.villagebox.common.item.common;

import java.util.List;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemInvitation extends Item{

    public ItemInvitation(){
        this.setUnlocalizedName(PathHelper.full("invitation"));
        this.setCreativeTab(ModItems.tabVB);
        this.setMaxStackSize(64);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        String info = I18n.format(PathHelper.full("invitation.item.info"));
        tooltip.add(info);
    }
}
