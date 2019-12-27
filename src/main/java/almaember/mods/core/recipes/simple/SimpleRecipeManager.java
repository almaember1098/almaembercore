package almaember.mods.core.recipes.simple;

import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleRecipeManager {
    private Logger logger = LogManager.getLogger();
    public void createRecipeVault(Identifier id) {
        recipeBank.put(id, new ArrayList<>());
        logger.info("Created recipe vault: " + id.toString());
    }
    public void addRecipe(Identifier vault, SimpleRecipe recipe) {
        recipeBank.get(vault).add(recipe);
    }

    public ArrayList<SimpleRecipe> getRecipes(Identifier vault) {
        return recipeBank.get(vault);
    }

    public SimpleRecipe getRecipeByIngredient(Identifier vault, Identifier ingredient) {
        for(SimpleRecipe recipe:recipeBank.get(vault)) {
            if(recipe.getIngredient().equals(ingredient)) {
                return recipe;
            }
        }
        // no recipes were found
        return null;
    }

    public void flushRecipes(Identifier vault) {
        recipeBank.get(vault).clear();
    }

    private static SimpleRecipeManager instance = null;
    private HashMap<Identifier, ArrayList<SimpleRecipe>> recipeBank =
            new HashMap<>();

    public static SimpleRecipeManager getInstance() {
        return instance;
    }

    static {
        instance = new SimpleRecipeManager();
    }
}
