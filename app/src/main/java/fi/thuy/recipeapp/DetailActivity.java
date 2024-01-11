package fi.thuy.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fi.thuy.recipecontents.Recipe;
import fi.thuy.recipecontents.RecipeList;

public class DetailActivity extends AppCompatActivity {
    protected static final String FILE_NAME = "recipeFavourite.txt";
    TextView textViewTitle;
    ImageButton buttonFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        Bundle bundle = getIntent().getExtras();
        textViewTitle = findViewById(R.id.tv_recipe_name);
        textViewTitle.setText(intent.getStringExtra("Title"));

        ImageView imageView = findViewById(R.id.imageViewRecipe);
        int resId = bundle.getInt("Image");
        imageView.setImageResource(resId);
        //readData();
        buttonFav = findViewById(R.id.iconFavourite);
        buttonFav.setImageResource(R.drawable.favourite_button);
        buttonFav.setOnClickListener(view -> saveFavouriteRecipe());

        TextView textViewIngredients = findViewById(R.id.tv_Ingredients);
        TextView textViewInstructions = findViewById(R.id.tv_Instructions);
        TextView textViewServing = findViewById(R.id.tvServing);
        TextView textViewTime = findViewById(R.id.tvTime);
        TextView textViewCalories = findViewById(R.id.tvCalories);

        textViewServing.setText(intent.getStringExtra("Serving"));
        textViewTime.setText(intent.getStringExtra("Time"));
        textViewCalories.setText(intent.getStringExtra("calories"));

        ArrayList<String> ingredients1 = (ArrayList<String>) getIntent().getSerializableExtra("Ingredients");

        //list each ingredients of a recipe in a new line.

        StringBuilder ingredients = new StringBuilder();
        for(int i = 0 ; i < ingredients1.size(); i++){
            ingredients.append("\u2023  ").append(ingredients1.get(i)).append("\n");
        }
        textViewIngredients.setText(ingredients.toString());

        ArrayList<String> instructions1 = (ArrayList<String>) getIntent().getSerializableExtra("Instructions");

        //list the instructions of a recipe in a separate line
        StringBuilder instructions = new StringBuilder();
        for(int i = 0 ; i < instructions1.size(); i++){
            instructions.append("\u2023  ").append(instructions1.get(i)).append("\n");
        }

        textViewInstructions.setText(instructions.toString());
        readData();
    }

    /**
     * This method is called when the user clicks the favourite
     * button at the top right corner of the page.
     * After that, the specific recipe/item will be added
     * to the favourites.
     */
    public void saveFavouriteRecipe(){
        buttonFav.setImageResource(R.drawable.favourite_button_red);

        String name = textViewTitle.getText().toString() + "\n";
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME,MODE_APPEND);
            fileOutputStream.write(name.getBytes());

            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Saved as Favourite",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFromFavourite(){
        buttonFav.setImageResource(R.drawable.favourite_button);
        File file = new File(getFilesDir(), FILE_NAME);
        final boolean delete = file.delete();
    }

    /**
     * The favourite recipe added by the user are saved in the internal
     * storage of the app. This method will read the recipe/item from the internally
     * saved file and shows all the favourite recipe saved by the user.
     * The favourite recipe can be accessed from the main page of the app.
     */
    public void readData() {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);

            Scanner scan = new Scanner(fileInputStream);
            String name = textViewTitle.getText().toString();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (!line.isEmpty()){
                    System.out.println(name);
                    if(name.trim().equalsIgnoreCase(line.trim())){
                        buttonFav.setImageResource(R.drawable.favourite_button_red);
                    }
                }
            }
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}