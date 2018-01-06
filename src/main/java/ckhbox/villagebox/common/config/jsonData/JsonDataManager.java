package ckhbox.villagebox.common.config.jsonData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.GsonBuilder;

public class JsonDataManager {

	private static final String jsonDataFileName = "VillageBoxData.json";

	private static JsonVBData vbData;

	public static JsonVBData GetVBData()
	{
		return vbData;
	}

	public static void LoadData(File dir)
	{
		File file = new File(dir, jsonDataFileName);
			try {
				createDefaultDataFile(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private static void loadDataFromFile(File file) throws IOException
	{
		FileReader reader = new FileReader(file);
		vbData = new GsonBuilder().setPrettyPrinting().create().fromJson(reader, JsonVBData.class);
		reader.close();
	}

	private static void createDefaultDataFile(File file) throws IOException
	{
		//create default data
		vbData = createDefaultData();
		//save to file
		FileWriter writter = new FileWriter(file);
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(vbData);
		//System.out.println(json);
		writter.write(json);
		writter.close();
	}

	private static JsonVBData createDefaultData()
	{
		JsonVBData data = new JsonVBData();

		JsonProfession profession = null;
		//coin: bronze_coin silver_coin gold_coin

		//painter carpetmakercartoony
		// bookseller bowmaker furnituremaker orchardist chef chefdessert
		//====== 0 country postman ======
			//乡村邮差
			//8:1,1:4  鸡蛋，墨囊，甘蔗
			profession = new JsonProfession();
			profession.id = 0;
			//鸡蛋
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,egg,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,egg,4,0"));
			//墨囊
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,dye,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,dye,4,0"));
			//甘蔗
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,1,0"}, "minecraft,reeds,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,reeds,4,0"));
			//交易获取信封和降级卷轴
			profession.quests.add(new JsonQuest(new String[]{"minecraft,bread,1,0"}, new String[]{"villagebox,mail,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"villagebox,reset_scroll,1,0"}));

			profession.upgradeProfessionIDs = new int[]{11, 12, 13, 14,15,16};
			profession.upgradeRequirements = new String[]{"minecraft,book,1,0"};
			profession.holdItems = new String[]{"villagebox,mail,1,0"};
			profession.texture = "villagePostman";
			profession.name = "villagePostman";
			data.professions.add(profession);

		//====== 1 city postman ======
			profession = new JsonProfession();
			profession.id = 1;
			//鸡蛋
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,egg,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,egg,4,0"));
			//墨囊
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,dye,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,dye,4,0"));
			//甘蔗
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,1,0"}, "minecraft,reeds,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,reeds,4,0"));
			//交易获取信封和降级卷轴
			profession.quests.add(new JsonQuest(new String[]{"minecraft,bread,1,0"}, new String[]{"villagebox,mail,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"villagebox,reset_scroll,1,0"}));
			profession.upgradeProfessionIDs = new int[]{21, 22, 23, 24,25};
			profession.upgradeRequirements = new String[]{"minecraft,book,1,0"};
			profession.holdItems = new String[]{"villagebox,mail,1,0"};
			profession.texture = "cityPostman";
			profession.name = "cityPostman";
			data.professions.add(profession);

		//====== 2 shadow postman ======
			profession = new JsonProfession();
			profession.id = 2;
			//鸡蛋
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,egg,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,egg,4,0"));
			//墨囊
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,dye,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,dye,4,0"));
			//甘蔗
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,1,0"}, "minecraft,reeds,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,reeds,4,0"));
			//交易获取信封和降级卷轴
			profession.quests.add(new JsonQuest(new String[]{"minecraft,bread,1,0"}, new String[]{"villagebox,mail,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"villagebox,reset_scroll,1,0"}));
			profession.upgradeProfessionIDs = new int[]{31, 32, 33};
			profession.upgradeRequirements = new String[]{"minecraft,book,1,0"};
			profession.holdItems = new String[]{"villagebox,mail,1,0"};
			profession.texture = "shadowPostman";
			profession.name = "shadowPostman";
			data.professions.add(profession);

		//====== 3 holy postman ======
			profession = new JsonProfession();
			profession.id = 3;
			//鸡蛋
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,egg,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,egg,4,0"));
			//墨囊
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,dye,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,dye,4,0"));
			//甘蔗
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,1,0"}, "minecraft,reeds,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,reeds,4,0"));
			//交易获取信封和降级卷轴
			profession.quests.add(new JsonQuest(new String[]{"minecraft,bread,1,0"}, new String[]{"villagebox,mail,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"villagebox,reset_scroll,1,0"}));
			profession.upgradeProfessionIDs = new int[]{41, 42, 43, 44 , 45 , 46 ,47 };
			profession.upgradeRequirements = new String[]{"minecraft,book,1,0"};
			profession.holdItems = new String[]{"minecraft,reeds,1,0", "minecraft,egg,1,0","minecraft,dye,1,0"};
			profession.texture = "holyPostman";
			profession.name = "holyPostman";
			data.professions.add(profession);

		//====== 11 peasant ======
			profession = new JsonProfession();
			profession.id = 11;
			//小麦
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,wheat,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,wheat,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,wheat,32,0"}, "villagebox,silver_coin,1,0"));
			//胡萝卜
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,carrot,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,carrot,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,carrot,32,0"}, "villagebox,silver_coin,1,0"));
			//马铃薯
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,potato,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,potato,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,potato,32,0"}, "villagebox,silver_coin,1,0"));
			//交易
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,pumpkin,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,melon,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,brown_mushroom,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,red_mushroom,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,cactus,1,0"}));

			profession.upgradeRequirements = new String[]{"villagebox,bronze_coin,16,0", "minecraft,iron_hoe,1,0"};
			profession.holdItems = new String[]{"minecraft,diamond_hoe,1,0"};
			profession.texture = "peasant";
			profession.name = "peasant";
			data.professions.add(profession);

		//====== 12 fisherman ======
			profession = new JsonProfession();
			profession.id = 12;
			//睡莲
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,waterlily,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,waterlily,2,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,waterlily,64,0"));
			//鱼
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,fish,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,fish,2,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,fish,64,0"));
			//交易:海绵，海晶
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sponge,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,prismarine_shard,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,prismarine_crystals,8,0"));
			profession.upgradeRequirements = new String[]{"villagebox,silver_coin,2,0", "minecraft,fishing_rod,1,0"};
			profession.holdItems = new String[]{"minecraft,fishing_rod,1,0"};
			profession.texture = "fisherman";
			profession.name = "fisherman";

		//====== 13 hunter ======
			profession = new JsonProfession();
			profession.id = 13;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,rabbit_hide,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,rabbit,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,mutton,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,wool,1,3"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,chicken,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,feather,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,porkchop,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,beef,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,leather,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,bone,1,0"));
			//任务获取
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,cooked_beef,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,cooked_porkchop,1,0"}));
			profession.upgradeRequirements = new String[]{"villagebox,silver_coin,1,0", "minecraft,golden_sword,1,0"};
			profession.holdItems = new String[]{"minecraft,bow,1,0"};
			profession.texture = "hunter";
			profession.name = "hunter";
			data.professions.add(profession);

		//====== 14 lumberjack ======
			profession = new JsonProfession();
			profession.id = 14;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sapling,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sapling,1,1"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sapling,1,2"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sapling,1,3"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sapling,1,4"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sapling,1,5"));
			//任务获取苹果
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,apple,16,0"}));
			profession.upgradeRequirements = new String[]{"villagebox,silver_coin,1,0", "minecraft,golden_hoe,1,0"};
			profession.holdItems = new String[]{"minecraft,diamond_axe,1,0"};
			profession.texture = "lumberjack";
			profession.name = "lumberjack";
			data.professions.add(profession);

		//====== 15 stonemason ========
			profession = new JsonProfession();
			profession.id = 15;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,cobblestone,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,cobblestone,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,granite,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,granite,4,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,diorite,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,diorite,4,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,andesite,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,andesite,4,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"minecraft,mossy_cobblestone,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,16,0"}, new String[]{"minecraft,obsidian,8,0"}));
			profession.upgradeRequirements = new String[]{"villagebox,bronze_coin,32,0", "minecraft,crafting_table,1,0"};
			profession.holdItems = new String[]{"minecraft,diamond_shovel,1,0"};
			profession.texture = "stonemason";
			profession.name = "stonemason";
			data.professions.add(profession);

		//====== 16 florist ======
			profession = new JsonProfession();
			profession.id = 16;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bonsai,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,1"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,2"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,3"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,4"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,5"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,6"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,7"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,red_flower,1,8"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,double_plant,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,double_plant,1,1"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,double_plant,1,2"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,double_plant,1,3"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,double_plant,1,4"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,double_plant,1,5"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,2,0"}, "minecraft,yellow_flower,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"villagebox,flower_heartmushroom,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"villagebox,flower_gardenia,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"villagebox,flower_hydrangeas,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"villagebox,flower_plumblossom,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"villagebox,flower_ranunculus,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"villagebox,flower_redrose,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"villagebox,flower_rosyspiraea,8,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,flower_pot,2,0", "minecraft,dirt,16,0", "villagebox,silver_coin,2,0"};
			profession.holdItems = new String[]{"villagebox,flower_redrose,1,0"};
			profession.texture = "florist";
			profession.name = "florist";
			data.professions.add(profession);

		//====== 21 miner ======
			profession = new JsonProfession();
			profession.id = 21;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,coal,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,coal,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,coal_block,32,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,coal,64,0"}, "villagebox,silver_coin,2,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,coal,64,1"}, "villagebox,silver_coin,2,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,iron_ore,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,iron_ore,2,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,iron_ore,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,iron_ore,8,0"}, "villagebox,silver_coin,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,redstone,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,redstone,4,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,redstone,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,redstone,16,0"}, "villagebox,silver_coin,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,32,0"}, new String[]{"minecraft,diamond,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,8,0"}, new String[]{"minecraft,lapis_ore,8,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,iron_pickaxe,1,0", "minecraft,torch,12,0", "villagebox,silver_coin,2,0"};
			profession.holdItems = new String[]{"minecraft,diamond_pickaxe,1,0"};
			profession.texture = "miner";
			profession.name = "miner";
			data.professions.add(profession);

		//====== 22 banker ======
			profession = new JsonProfession();
			profession.id = 22;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,64,0"}, "villagebox,silver_coin,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,64,0"}, "villagebox,gold_coin,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bronze_coin,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "villagebox,silver_coin,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,2,0"}, "minecraft,emerald,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,gold_ingot,1,0"}, "villagebox,gold_coin,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,gold_coin,8,0"}, new String[]{"minecraft,ender_pearl,8,0"}));
			profession.upgradeRequirements = new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"};
			profession.holdItems = new String[]{"villagebox,gold_coin,1,0"};
			profession.texture = "banker";
			profession.name = "banker";
			data.professions.add(profession);

		//====== 23 builder ======
			profession = new JsonProfession();
			profession.id = 23;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,4,0"}, "villagebox,buildbox_small,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,8,0"}, "villagebox,buildbox_medium,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,16,0"}, "villagebox,buildbox_large,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,32,0"}, "villagebox,buildbox_exlarge,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,clay_ball,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,clay_ball,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,clay_ball,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,clay_ball,32,0"}, "villagebox,silver_coin,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,sand,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,sand,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,sand,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,sand,32,0"}, "villagebox,silver_coin,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,4,0"}, "minecraft,gravel,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,gravel,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,gravel,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,gravel,32,0"}, "villagebox,silver_coin,1,0"));

			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,soul_sand,1,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,stonebrick,32,0", "minecraft,planks,32,0", "villagebox,silver_coin,2,0"};
			profession.holdItems = new String[]{"minecraft,stonebrick,1,0"};
			profession.texture = "builder";
			profession.name = "builder";
			data.professions.add(profession);

		//====== 24 carpenter ======
			profession = new JsonProfession();
			profession.id = 24;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,25,0"}, "villagebox,bow,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,1,0"}, "minecraft,arrow,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "villagebox,table_acacia,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "villagebox,table_birch,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "villagebox,table_big_oak,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "villagebox,table_jungle,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "villagebox,table_oak,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "villagebox,table_spruce,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,chair_acacia,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,chair_birch,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,chair_big_oak,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,chair_jungle,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,chair_oak,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,chair_spruce,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bench_acacia,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bench_birch,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bench_big_oak,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bench_jungle,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bench_oak,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,bench_spruce,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,armor_stand,1,0"}));
			profession.upgradeProfessionIDs = new int[]{10, 17};
			profession.upgradeRequirements = new String[]{"minecraft,iron_axe,1,0", "villagebox,silver_coin,2,0"};
			profession.holdItems = new String[]{"minecraft,log,1,0"};
			profession.texture = "carpenter";
			profession.name = "carpenter";
			data.professions.add(profession);

		//====== 25  carpetmaker ======
			profession = new JsonProfession();
			profession.id = 25;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_0,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_1,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_2,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_3,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_4,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_5,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_6,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_7,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_8,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_9,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_10,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_11,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_12,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_13,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_14,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0", "minecraft,wool,1,0"}, "villagebox,carpet_wool_15,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_20,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_21,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_22,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_23,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_24,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_25,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_26,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_27,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_28,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_29,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_30,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_31,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_0,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_1,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_2,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_3,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_4,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_5,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_6,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_7,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_8,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_9,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_10,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_11,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_12,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_13,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_14,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_15,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_16,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_17,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_18,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,32,0", "minecraft,wool,1,0"}, "villagebox,carpet_19,1,0"));
			profession.upgradeRequirements = new String[]{"villagebox,silver_coin,2,0", "minecraft,wool,16,0"};
			profession.holdItems = new String[]{"minecraft,wool,1,0"};
			profession.texture = "carpetmaker";
			profession.name = "carpetmaker";
			data.professions.add(profession);


		//====== 31 ShadowMiner ======
			profession = new JsonProfession();
			profession.id = 31;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,quartz,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,quartz,4,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,quartz,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,glowstone_dust,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,glowstone_dust,2,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,glowstone_dust,64,0"));
			profession.upgradeRequirements = new String[]{"minecraft,glowstone_dust,4,0", "minecraft,quartz,4,0", "villagebox,silver_coin,2,0"};
			profession.holdItems = new String[]{"minecraft,quartz,1,0"};
			profession.texture = "shadowMiner";
			profession.name = "shadowMiner";
			data.professions.add(profession);

		//====== 32 shadowTrader ======
			profession = new JsonProfession();
			profession.id = 32;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,nether_star,1,0"}, "villagebox,gold_coin,4,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,dragon_egg,1,0"}, "villagebox,gold_coin,8,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,chorus_fruit,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,magma_cream,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,gold_coin,8,0"}, new String[]{"minecraft,nether_star,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,gold_coin,64,0"}, new String[]{"minecraft,dragon_egg,1,0"}));
			profession.upgradeRequirements = new String[]{"villagebox,gold_coin,2,0"};
			profession.holdItems = new String[]{"minecraft,nether_star,1,0"};
			profession.texture = "shadowTrader";
			profession.name = "shadowTrader";
			data.professions.add(profession);

		//====== 33 shadowBuilder ======
			profession = new JsonProfession();
			profession.id = 33;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,netherrack,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,netherrack,4,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,netherrack,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,8,0"}, "minecraft,soul_sand,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,soul_sand,4,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,soul_sand,64,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,bronze_coin,16,0"}, "minecraft,magma,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,magma,2,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,gold_coin,1,0"}, "minecraft,magma,64,0"));
			profession.upgradeRequirements = new String[]{"minecraft,soul_sand,4,0", "minecraft,netherrack,4,0", "villagebox,silver_coin,2,0"};
			profession.holdItems = new String[]{"minecraft,netherrack,1,0", "minecraft,soul_sand,1,0"};
			profession.texture = "shadowBuilder";
			profession.name = "shadowBuilder";
			data.professions.add(profession);

		//====== 41 holyTrader =======
			profession = new JsonProfession();
			profession.id = 41;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,lava_bucket,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,water_bucket,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,snowball,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,lead,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "minecraft,name_tag,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,4,0"}, "minecraft,saddle,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,8,0"}, "minecraft,iron_horse_armor ,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,saddle,1,0"}, "villagebox,silver_coin,32,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,4,0"}, "minecraft,golden_carrot,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,4,0"}, "minecraft,golden_apple,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,gold_coin,1,0"}, new String[]{"minecraft,diamond_horse_armor,1,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,coal,10,0", "minecraft,iron_ingot,2,0", "villagebox,silver_coin,2,0"};
			profession.holdItems = new String[]{"minecraft,golden_apple,1,0", "minecraft,diamond_horse_armor,1,0"};
			profession.texture = "holyTrader";
			profession.name = "holyTrader";
			data.professions.add(profession);

		//====== 42 scholar ======
			profession = new JsonProfession();
			profession.id = 42;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,reset_scroll,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0"}, "villagebox,dismissal_scroll,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,treasure_hunt_book_0,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,16,0"}, "villagebox,treasure_hunt_book_1,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "villagebox,painting,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,1,0"}, new String[]{"minecraft,writable_book,1,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,gold_coin,1,0"}, new String[]{"villagebox,treasure_hunt_book_2,1,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,book,1,0", "villagebox,silver_coin,1,0"};
			profession.holdItems = new String[]{"minecraft,book,1,0", "villagebox,reset_scroll,1,0"};
			profession.texture = "scholar";
			profession.name = "scholar";
			data.professions.add(profession);

		//====== 43 mage ======
			profession = new JsonProfession();
			profession.id = 43;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,blaze_rod,4,0", "villagebox,silver_coin,1,0"}, "villagebox,fire_shard,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,snowball,4,0", "villagebox,silver_coin,1,0"}, "villagebox,water_shard,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"minecraft,wheat_seeds,16,0", "villagebox,silver_coin,1,0"}, "villagebox,nature_shard,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"minecraft,obsidian,4,0", "villagebox,silver_coin,1,0"}, new String[]{"villagebox,dark_shard,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"minecraft,glowstone_dust,16,0", "villagebox,silver_coin,1,0"}, new String[]{"villagebox,light_shard,8,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,book,1,0", "villagebox,silver_coin,3,0"};
			profession.holdItems = new String[]{"villagebox,fire_shard,1,0", "villagebox,water_shard,1,0"};
			profession.texture = "mage";
			profession.name = "mage";
			data.professions.add(profession);

		//====== 44 shaman ======
			profession = new JsonProfession();
			profession.id = 44;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,8,0", "villagebox,fire_shard,4,0", "minecraft,log,1,0"}, "villagebox,fire_totem,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,8,0", "villagebox,water_shard,4,0", "minecraft,log,1,0"}, "villagebox,water_totem,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,8,0", "villagebox,nature_shard,4,0", "minecraft,log,1,0"}, "villagebox,nature_totem,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0", "minecraft,log,1,0"}, "villagebox,totempole_0,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0", "minecraft,log,1,0"}, "villagebox,totempole_1,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,2,0", "minecraft,log,1,0"}, "villagebox,totempole_2,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,2,0", "villagebox,fire_shard,1,0"}, new String[]{"villagebox,sun_stone,8,0"}));
			profession.quests.add(new JsonQuest(new String[]{"villagebox,silver_coin,2,0", "villagebox,water_shard,1,0"}, new String[]{"villagebox,rain_stone,8,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,lava_bucket,1,0", "minecraft,water_bucket,1,0", "villagebox,silver_coin,12,0"};
			profession.holdItems = new String[]{"villagebox,sun_stone,1,0", "villagebox,rain_stone,1,0"};
			profession.texture = "shaman";
			profession.name = "shaman";
			data.professions.add(profession);

		//====== 45 alchemist ======
			profession = new JsonProfession();
			profession.id = 45;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,potion,1,8193"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,potion,1,8194"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,potion,1,8197"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,potion,1,8201"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,potion,1,8203"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,1,0"}, "minecraft,potion,1,8205"));
			profession.upgradeRequirements = new String[]{"minecraft,cauldron,1,0", "villagebox,silver_coin,15,0"};
			profession.holdItems = new String[]{"minecraft,glass_bottle,1,0", "minecraft,potion,1,8205"};
			profession.texture = "alchemist";
			profession.name = "alchemist";
			data.professions.add(profession);

		//====== 46 weaponsmith ======
			profession = new JsonProfession();
			profession.id = 46 ;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,fire_shard,2,0", "villagebox,diamond_sword,1,0"}, "villagebox,fire_sword,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,water_shard,2,0", "villagebox,diamond_sword,1,0"}, "villagebox,water_sword,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,nature_shard,2,0", "villagebox,diamond_sword,1,0"}, "villagebox,nature_sword,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,light_shard,2,0", "villagebox,diamond_sword,1,0"}, "villagebox,light_sword,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,dark_shard,2,0", "villagebox,diamond_sword,1,0"}, "villagebox,dark_sword,1,0"));

			profession.upgradeRequirements = new String[]{"minecraft,gold_ingot ,1,0", "villagebox,silver_coin,16,0"};
			profession.holdItems = new String[]{"minecraft,coal,1,0", "minecraft,lava_bucket,1,0"};
			profession.texture = "weaponsmith";
			profession.name = "weaponsmith";
			data.professions.add(profession);

		//====== 47 staffcrafter ======
			profession = new JsonProfession();
			profession.id = 47;
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "minecraft,stick,1,0", "minecraft,glass,1,0"}, "villagebox,staff,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,staff,1,0", "villagebox,fire_shard,2,0"}, "villagebox,fire_staff,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,staff,1,0", "villagebox,water_shard,2,0"}, "villagebox,water_staff,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,staff,1,0", "villagebox,nature_shard,2,0"}, "villagebox,nature_staff,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,staff,1,0", "villagebox,light_shard,2,0"}, "villagebox,light_staff,1,0"));
			profession.tradingRecipes.add(new JsonTradingRecipe(new String[]{"villagebox,silver_coin,10,0", "villagebox,staff,1,0", "villagebox,dark_shard,2,0"}, "villagebox,dark_staff,1,0"));
			profession.quests.add(new JsonQuest(new String[]{"minecraft,stick,16,0"}, new String[]{"villagebox,bronze_coin,8,0"}));
			profession.upgradeRequirements = new String[]{"minecraft,stick,1,0", "villagebox,silver_coin,15,0"};
			profession.holdItems = new String[]{"villagebox,staff,1,0"};
			profession.texture = "staffcrafter";
			profession.name = "staffcrafter";
			data.professions.add(profession);


		return data;
	}
}
