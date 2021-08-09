package com.example.demo;

public class Recipe {
    private String recipe_id;
    private String recipe_name;
    private String recipe_desc;
    private String u_id;

    public Recipe(String recipe_id, String recipe_name, String recipe_desc, String u_id) {
      this.recipe_id = recipe_id;
      this.recipe_name = recipe_name;
      this.recipe_desc = recipe_desc;
      this.u_id = u_id;
    }

    public String getRecipe_id() {
      return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
      this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
      return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
      this.recipe_name = recipe_name;
    }

    public String getRecipe_desc() {
      return recipe_desc;
    }

    public void setRecipe_desc(String recipe_desc) {
      this.recipe_desc = recipe_desc;
    }

    public String getU_id() {
      return u_id;
    }

    public void setU_id(String u_id) {
      this.u_id = u_id;
    }
}
