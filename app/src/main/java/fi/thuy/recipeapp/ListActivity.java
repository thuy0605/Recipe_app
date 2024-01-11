package fi.thuy.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;


import java.util.ArrayList;

import fi.thuy.recipecontents.Recipe;
import fi.thuy.recipecontents.RecipeList;
import fi.thuy.recipecontents.RecipeListAdapterListView;

/**
 * This activity will list all the recipe that the user wants too see.
 * View of this activity will be updated based on the user selection.
 */
public class ListActivity extends AppCompatActivity implements RecipeListAdapterListView.OnCardListener{
    RecyclerView recyclerView;
    SearchView searchView;
    RecipeList recipes = RecipeList.getInstance();
    RecipeListAdapterListView recipeListAdapter;
    ArrayList<Recipe> searchList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        Intent intentBack = new Intent(this, StartActivity.class );
        ImageButton btnHome = findViewById(R.id.buttonHome);
        searchView = findViewById(R.id.searchViewList);

        Intent intent = getIntent();
        String message = intent.getStringExtra( "key");
        String messageSearch = intent.getStringExtra("keySearch");

        TextView textView = findViewById(R.id.textViewLookingFor);

        if(message == null){
            if(messageSearch == null){
                textView.setText(getString(R.string.looking_for, "Recipes"));
            }else {
                textView.setText(getString(R.string.looking_for, messageSearch));
            }
        }else{
            textView.setText(getString(R.string.looking_for, message));
        }

        btnHome.setOnClickListener(view -> {
            startActivity(intentBack);
            intentBack.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        });

        //sets the user input in searchView
        searchView.setQuery(messageSearch, true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                ArrayList<String> tags = new ArrayList<>();

                String[] parts = s.split(" ");

                for (int i = 0; i < recipes.getListOfRecipes().size(); i++) {
                    Recipe recipe = recipes.getListOfRecipes().get(i);
                    String[] tag = recipe.getTags().split(",");

                    //if user search matches the recipe, it will be added in temporary arraylist (searchList)
                    if (recipe.getRecipeName().toLowerCase().contains(s.toLowerCase())) {
                        if (!(searchList.contains(recipe))) {
                            searchList.add(recipe);
                        }
                    } else {
                        //Adds all the tags for a specific recipe in an arraylist
                        for (String t : tag) {
                            t = t.replace("\"", "");
                            t = t.replace("[", "");
                            t = t.replace("]", "");
                            tags.add(t);
                        }

                        //if user search matches ingredients, recipe will be added in temporary arraylist (searchList)
                        for (int j = 0; j < tags.size(); j++) {
                            if (s.contains(tags.get(j))) {
                                if (!(searchList.contains(recipe))) {
                                    searchList.add(recipe);
                                }
                            }
                        }
                        textView.setText(getString(R.string.looking_for, parts[0]));
                        tags.clear();
                    }

                }

                System.out.println(searchList.size());

                //call the constructor of RecipeListAdapterListView to send the reference and data to Adapter
                recipeListAdapter = new RecipeListAdapterListView(ListActivity.this, searchList, this::onQueryTextChange);
                recyclerView.setAdapter(recipeListAdapter);
                return false;
            }

            //this method will be called when the user clicks the search results.

            /**
             *
             * @param i indicates the position of each item of the search results.
             * When the user clicks the items in search results, it will open the
             * detail of each item.
             */
            public void onQueryTextChange(int i) {
                ArrayList<String> ingredients = new ArrayList<>();
                ArrayList<String> instructions = new ArrayList<>();
                Recipe recipe = searchList.get(i);
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);

                intent.putExtra("Image",recipe.getImage());
                intent.putExtra("Title", recipe.getRecipeName() );
                intent.putExtra("Instructions", recipe.getInstructions());
                intent.putExtra("Serving", recipe.getServing());
                intent.putExtra("Time", recipe.getTime());
                intent.putExtra("calories", recipe.getCalories());

                String[] partsIngredients =  recipe.getIngredients().split(",");
                String[] partsInstructions = recipe.getInstructions().split(",");

                for(String p: partsIngredients) {
                    p = p.replace("\"", "");
                    p = p.replace("[","");
                    p = p.replace("]","");
                    ingredients.add(p);
                }

                for(String ins: partsInstructions) {
                    ins = ins.replace("\"", "");
                    ins = ins.replace("[","");
                    ins = ins.replace("]","");
                    instructions.add(ins);
                }

                intent.putExtra("Ingredients",ingredients);
                intent.putExtra("Instructions",instructions);
                startActivity(intent);
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchList.clear();
                return true;
            }

        });

        // get the reference of RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //call the constructor of RecipeListAdapter to send the reference and data to Adapter
        recipeListAdapter = new RecipeListAdapterListView(ListActivity.this, recipes.getListOfRecipes(), this);

        //set the adapter to recycler view
        recyclerView.setAdapter(recipeListAdapter);

    }

    /**
     *
     * @param position the position of each item/recipe in the list.
     * When each individual card is clicked, onClickListener will
     * get the position of the recipe and will display the details of specific recipe.
     */
    @Override
    public void onCardClick(int position) {

        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> instructions = new ArrayList<>();
        Recipe recipe = recipes.getListOfRecipes().get(position);
        Intent intent = new Intent(this, DetailActivity.class );
        intent.putExtra("Image", recipe.getImage());
        intent.putExtra("Title", recipe.getRecipeName() );
        intent.putExtra("Instructions", recipe.getInstructions());
        intent.putExtra("Serving", recipe.getServing());
        intent.putExtra("Time", recipe.getTime());
        intent.putExtra("calories", recipe.getCalories());

        String[] partsIngredients =  recipe.getIngredients().split(",");
        String[] partsInstructions = recipe.getInstructions().split(",");


        /*
          Split the ingredients, remove unnecessary character, store it in arrayList
          and send it to another activity
        */
        for(String p: partsIngredients) {
            p = p.replace("\"", "");
            p = p.replace("[","");
            p = p.replace("]","");
            ingredients.add(p);
        }

        /*
          Split the instructions, remove unnecessary character, store it in arrayList
          and send it to another activity
         */
        for(String ins: partsInstructions) {
            ins = ins.replace("\"", "");
            ins = ins.replace("[","");
            ins = ins.replace("]","");
            instructions.add(ins);
        }

        intent.putExtra("Ingredients",ingredients);
        intent.putExtra("Instructions",instructions);
        startActivity(intent);
    }


}