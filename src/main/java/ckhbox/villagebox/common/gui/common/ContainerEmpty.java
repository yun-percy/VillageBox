package ckhbox.villagebox.common.gui.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerEmpty extends Container{

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
