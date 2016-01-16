package com.ckhgame.villagebento.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.CLIENT)
public class ModelChair extends ModelBase
{
    public ModelRenderer chair;
    private static final String __OBFID = "CL_00000832";

    public ModelChair()
    {
    	this.chair = new ModelRenderer(this, 0, 0).setTextureSize(16, 16);
    	this.chair.addBox(5, 0, 5, 6, 1, 6);
    	this.chair.addBox(7, 1, 7, 2, 6, 2);
    	this.chair.addBox(4, 7, 4, 8, 1, 8);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {    	
        this.chair.render(p_78088_7_);
    }
}