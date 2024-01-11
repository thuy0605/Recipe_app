package fi.thuy.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

/**
 * This activity will allow the user to remove recipe from their favourites and to choose vegetarian item.
 */
public class ProfileActivity extends AppCompatActivity {
    protected static final String FILE_NAME = "recipeFavourite.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnDeleteFavourite = findViewById(R.id.buttonDelete);

        btnDeleteFavourite.setOnClickListener(view -> deleteFavouriteRecipe());
    }

    /**
     * Delete the favourite recipe saves by the user
     */
    public void deleteFavouriteRecipe(){
        File file = new File(getFilesDir(), FILE_NAME);
        final boolean delete = file.delete();
        Toast.makeText(getApplicationContext(),"Favourite Recipe deleted",Toast.LENGTH_SHORT).show();
    }
}