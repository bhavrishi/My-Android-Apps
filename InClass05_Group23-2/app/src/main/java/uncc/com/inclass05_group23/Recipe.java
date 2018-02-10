package uncc.com.inclass05_group23;

import org.json.JSONObject;

/**
 * Created by rosyazad on 25/09/17.
 */

public class Recipe {

    String title;
    String image;
    String url;
    String ingredients;


    public void createRecipe(JSONObject objJsonObject){
        try{
            this.setTitle(objJsonObject.getString("title"));
            if(objJsonObject.getString("thumbnail") != "") {
                this.setImage(objJsonObject.getString("thumbnail"));
            }
            else{
                this.setImage("");
            }
            this.setUrl(objJsonObject.getString("href"));
            this.setIngredients(objJsonObject.getString("ingredients"));


        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}
