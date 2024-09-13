package com.gab.gabbospotions;

import com.gab.gabbospotions.modeffects.ModEffects;
import com.gab.gabbospotions.moditems.ModItems;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GabbosPotions implements ModInitializer {
	public static final String MOD_ID = "gabbos-potions";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Registrazione item iniziata");
		ModEffects.registraEffetti();
		ModItems.registraItems();
	}
}