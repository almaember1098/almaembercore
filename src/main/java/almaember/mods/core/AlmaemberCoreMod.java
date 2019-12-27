package almaember.mods.core;

import jdk.vm.ci.meta.Assumptions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.crash.CrashReport;
import org.apache.logging.log4j.*;
import net.minecraft.util.crash.CrashException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author almaember1098
 * Main mod class.
 * Its main task is to initlaize my mods when the game starts.
 */
public class AlmaemberCoreMod implements ModInitializer {
	private static String CONFIG_DIR = FabricLoader.getInstance().getConfigDirectory().getAbsolutePath()
			+ "/almaember";

	@Override
	public void onInitialize() {
		// setup logger
		Logger logger = LogManager.getLogger("almaembercore");
		String modConfigPath = CONFIG_DIR + "/almaembercore.properties";
		// make config files if necessary
		probeForConfig(logger, modConfigPath);
		// loading config
		Properties modProperties = new Properties();
		try {
			modProperties.load(new FileInputStream(modConfigPath));
		} catch (IOException e) {
			// if the mod fails to load the config
			logger.fatal("Failed to load AlmaemberCore config file. Minecraft will stop.");
			throw new CrashException(
					new CrashReport("Failed to load AlmaemberCore config file.", new IOException())
			);
		}
	}

	private void probeForConfig(Logger logger, String modConfigPath) {
		// probing for mod config directory
		if(!(new File(CONFIG_DIR).exists())) {
			new File(CONFIG_DIR).mkdir();
		}
		// probing for config file
		if(!(new File(modConfigPath).exists())) {
			try {
				new File(modConfigPath).createNewFile();
			} catch (IOException e) {
				logger.fatal("Failed to create AlmaemberCore config file. Minecraft will stop.");
				throw new CrashException(
						new CrashReport("Failed to create AlmaemberCore config file.", new IOException())
				);
			}
		}
	}
}
