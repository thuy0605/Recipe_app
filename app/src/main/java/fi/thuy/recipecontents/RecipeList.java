package fi.thuy.recipecontents;

import java.util.ArrayList;

/**
 * Stores the list of Recipes.
 */
public class RecipeList {
    private final ArrayList<Recipe> listOfRecipes;
    private static final RecipeList recipes = new RecipeList();

    //provides instance of the RecipeList class
    public static RecipeList getInstance(){
        return recipes;
    }

    // new ArrayList for storing the recipe
    private RecipeList(){
        this.listOfRecipes = new ArrayList<>();
    }

    //adds the recipe in the arraylist
    public void addRecipe(Recipe recipe){
        this.listOfRecipes.add(recipe);
    }

    //return the list of recipe which are stored in the arrayList
    public ArrayList<Recipe> getListOfRecipes(){
        return listOfRecipes;
    }

    public void clear() {
        this.listOfRecipes.clear();
    }
}
