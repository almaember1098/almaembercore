package almaember.mods.core.worldgen.oregen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OreGenerationTask {
    private OreFeatureConfig oreFeatureConfig;
    private RangeDecoratorConfig rangeDecoratorConfig;

    public OreGenerationTask(OreFeatureConfig oreFeatureConfig,
                             RangeDecoratorConfig rangeDecoratorConfig) {
        this.oreFeatureConfig = oreFeatureConfig;
        this.rangeDecoratorConfig = rangeDecoratorConfig;
    }

    public OreFeatureConfig getOreFeatureConfig() {
        return oreFeatureConfig;
    }

    public RangeDecoratorConfig getRangeDecoratorConfig() {
        return rangeDecoratorConfig;
    }
}
