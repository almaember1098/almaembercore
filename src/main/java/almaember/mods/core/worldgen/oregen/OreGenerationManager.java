package almaember.mods.core.worldgen.oregen;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class OreGenerationManager {
    private static OreGenerationManager instance;
    private Logger logger = LogManager.getLogger();
    private ArrayList<OreGenerationTask> oreGenerationTasks = new ArrayList<>();

    static {
        instance = new OreGenerationManager();
    }

    public static OreGenerationManager getInstance() {
        return instance;
    }

    private void addOreToOverworld(Biome biome, OreGenerationTask task) {
        if(biome.getCategory()==Biome.Category.THEEND || biome.getCategory()==Biome.Category.NETHER) {
            return;
        }
        biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
                Feature.ORE.configure(task.getOreFeatureConfig())
                    .createDecoratedFeature(Decorator.COUNT_RANGE.configure(task.getRangeDecoratorConfig()
                    )));
    }

    public void onInitialize() {
        // runs when the game is initializing
        for(OreGenerationTask task:oreGenerationTasks) {
            Registry.BIOME.forEach(
                    biome -> addOreToOverworld(biome, task)
            );
        }
    }

    public void createTask(OreGenerationTask oreGenerationTask) {
        oreGenerationTasks.add(oreGenerationTask);
        logger.info("Created ore generation task: " + oreGenerationTask.toString());
    }

    public void flushTasks() {
        oreGenerationTasks.clear();
    }
}
