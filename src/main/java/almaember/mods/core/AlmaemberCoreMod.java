package almaember.mods.core;

import almaember.mods.core.worldgen.oregen.OreGenerationManager;
import almaember.mods.core.worldgen.oregen.OreGenerationTask;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author almaember1098
 * Main mod class.
 * Its main task is to initialize my mods when the game starts.
 */
public class AlmaemberCoreMod implements ModInitializer {
	private static String CONFIG_DIR = FabricLoader.getInstance().getConfigDirectory().getAbsolutePath()
			+ "/almaember";

	@Override
	public void onInitialize() {
		// setup logger
		Logger logger = LogManager.getLogger();
        setupConfig(logger);
        initializeClasses();
        selfTest(logger);
	}

    private void initializeClasses() {
        OreGenerationManager.getInstance().onInitialize();
    }

    private void setupConfig(Logger logger) {
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

    private void selfTest(Logger logger) {
		// world generator test
        OreGenerationManager manager = OreGenerationManager.getInstance();
        manager.createTask(
                new OreGenerationTask(
                        new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE,
                                Blocks.ICE.getDefaultState(), 8),
                        new RangeDecoratorConfig(8, 0, 0, 64)
                )
        );
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
