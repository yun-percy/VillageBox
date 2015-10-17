package com.ckhgame.villagebento.data;

import java.util.HashMap;

import com.ckhgame.villagebento.config.ConfigData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class DataVillageBento extends WorldSavedData{
	
	public HashMap<Integer,DataVillage> mapDataVillage = new HashMap<Integer,DataVillage>();
	public DataID dataID = new DataID();
	
	public DataVillageBento(String tagName) {
		super(tagName);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		//village list
		mapDataVillage.clear();
		int size = compound.getInteger(ConfigData.KeyDataVillageBentoVillageMapSize);
		for(int i =0;i<size;i++){
			DataVillage dv = new DataVillage();
			dv.readFromNBT((NBTTagCompound)compound.getTag(ConfigData.KeyDataVillageBentoVillageMapPrefix + i));
			mapDataVillage.put(dv.id,dv);
		}
		
		//id
		dataID.readFromNBT((NBTTagCompound)compound.getTag(ConfigData.KeyDataVillageBentoIDs));
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		//village list
		int idx = 0;
		compound.setInteger(ConfigData.KeyDataVillageBentoVillageMapSize, this.mapDataVillage.size());
		for(DataVillage dv:this.mapDataVillage.values()){
			NBTTagCompound c = new NBTTagCompound();
			dv.writeToNBT(c);
			compound.setTag(ConfigData.KeyDataVillageBentoVillageMapPrefix + (idx++), c);
		}
		
		//id
		NBTTagCompound c = new NBTTagCompound();
		dataID.writeToNBT(c);
		compound.setTag(ConfigData.KeyDataVillageBentoIDs, c);
	}
	
	public static DataVillageBento get(World world){

		if(world.mapStorage == null)
			return null;
		
		DataVillageBento data = (DataVillageBento)world.mapStorage.loadData(DataVillageBento.class, ConfigData.KeyDataVillageBentoMapStorage);
		
		if(data == null){
			data = new DataVillageBento(ConfigData.KeyDataVillageBentoMapStorage);
			data.markDirty();
			world.mapStorage.setData(ConfigData.KeyDataVillageBentoMapStorage, data);
		}
		
		return data;
	}

}
