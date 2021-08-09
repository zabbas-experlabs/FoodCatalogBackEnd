package com.example.demo;

import ch.qos.logback.core.status.Status;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang.RandomStringUtils;

@RestController
public class HomeController {
    DB_Operations db_operations=DB_Operations.getInstance();

    @PostMapping("/")
    public User login(String email, String password){
        System.out.println(email+" "+password);
        User u= db_operations.hasAccount(email,password);
        System.out.println(u.getUid());
        return u;
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes(){
        return db_operations.readAllRecipes();
    }

    @PostMapping("/Signup")
    public int registerUser(User newUser) {
        User u=db_operations.hasAccount(newUser.getEmail(), newUser.getPassword());
        if (u!=null) {
            System.out.println("User Already exists!");
            return -1;
        }
        String uid=RandomStringUtils.randomAlphanumeric(10);
        newUser.setUid(uid);
        int insertionStatus=db_operations.insertUser(newUser);
        return insertionStatus;
    }

    @PostMapping("/addRecipe")
    public int addRecipe(Recipe recipe) {
        String rid=RandomStringUtils.randomAlphanumeric(9);
        recipe.setRecipe_id(rid);
        int insertionStatus=db_operations.insertRecipe(recipe);
        return insertionStatus;
    }

    @PostMapping("/updateRecipe")
    public int updateRecipe(Recipe recipe) {
        int insertionStatus=db_operations.updateRecipe(recipe);
        return insertionStatus;
    }

    @DeleteMapping("/removeRecipe/{recipe_id}")
    public int removeRecipe(@PathVariable String recipe_id) {
        int insertionStatus=db_operations.removeRecipe(recipe_id);
        return insertionStatus;
    }

    @GetMapping("/searchRecipes/{recipe_name}")
    public List<Recipe> searchRecipes(@PathVariable String recipe_name) {
        return db_operations.readRecipes(recipe_name);
    }

    @GetMapping("/readRecipeReviews/{recipe_id}")
    public List<Review> readRecipeReviews(@PathVariable String recipe_id) {
        return db_operations.readRecipeReviews(recipe_id);
    }

    @GetMapping("/readUserRecipes/{u_id}")
    public List<Recipe> readUserRecipes(@PathVariable String u_id) {
        return db_operations.readRecipesByUser(u_id);
    }

    @PostMapping("/addReview")
    public int addReview(Review review) {
        String rid=RandomStringUtils.randomAlphanumeric(8);
        review.setReview_id(rid);
        int insertionStatus=db_operations.insertReview(review);
        return insertionStatus;
    }

}
