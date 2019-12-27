package almaember.mods.core.recipes.simple;

import net.minecraft.util.Identifier;

public final class SimpleRecipe {
    public SimpleRecipe(Identifier ingredient, Identifier result)
    {
        this.ingredient = ingredient;
        this.result = result;
    }

    private Identifier ingredient, result;
    public Identifier getIngredient() {
        return ingredient;
    }
    public Identifier getResult() {
        return result;
    }
}
