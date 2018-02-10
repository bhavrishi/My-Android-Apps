package uncc.com.inclass05_group23;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rosyazad on 25/09/17.
 */

public class RecipeUtil {

    public static ArrayList<Recipe> jsonParsor(String input) throws JSONException {

        ArrayList<Recipe> objRecipeList = new ArrayList<>();

        JSONObject root = new JSONObject(input);
        JSONArray objJsonArray = root.getJSONArray("results");

        for(int i=0;i<objJsonArray.length();i++){
            Recipe objRecipe = new Recipe();
            JSONObject objJsonObject =objJsonArray.getJSONObject(i);
            try {
                objRecipe.createRecipe(objJsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            objRecipeList.add(objRecipe);

        }

        return objRecipeList;
    }
}
