package fi.thuy.recipecontents;

/**
 * This class will provide the detail information about the recipe.
 */
public class Recipe {
    private int id;
    private String recipeName;
    private String mealType;
    private String instructions;
    private String ingredients;
    private String tags;
    private int image;
    private String serving;
    private String time;
    private String calories;
    private String category;

    /**
     *
     * @param id id of each recipe
     * @param recipeName title or name of the recipe
     * @param mealType type of meal, whether it is Breakfast, lunch or dinner
     * @param instructions Instructions for making the recipe
     * @param ingredients required ingredients for making the recipe
     * @param tags specific word for the ingredients
     * @param image image of the recipe
     * @param serving how many peole can be served
     * @param time cooking time of the recipe
     * @param calories energy that is received from the recipe
     * @param category category indicates the origin country of the recipe
     */
    public Recipe( int id, String recipeName, String mealType, String instructions, String ingredients, String tags, int image,String serving,  String time,String calories, String category) {
        this.id = id;
        this.recipeName = recipeName;
        this.mealType = mealType;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.tags = tags;
        this.image = image;
        this.serving = serving;
        this.time = time;
        this.calories = calories;
        this.category = category;
    }

    public Recipe(String recipeName, String mealType, String time, String serving, String calories, String ingredients, String instructions){
        this.recipeName = recipeName;
        this.mealType = mealType;
        this.instructions = instructions;
        this.calories = calories;
        this.ingredients = ingredients;
        this.serving = serving;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getMealType() {
        return mealType;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getTags() {
        return tags;
    }

    public int getImage() {
        return image;
    }

    public String getServing() {
        return serving;
    }

    public String getTime() {
        return time;
    }

    public String getCalories() {
        return calories;
    }

    public String getCategory() {
        return category;
    }

}

