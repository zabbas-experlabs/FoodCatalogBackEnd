package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// todo implement user logged in checks
// todo implement recipe removal & update checks that only own recipes be manipulated

public class DB_Operations {
    private String dbURL;
    private String username;
    private String password;

    private static final DB_Operations instance = new DB_Operations();

    private DB_Operations() {
        try {
          dbURL = "jdbc:mysql://localhost:3306/FoodCatalog";
          username = "root";
          password = "arianagrande";
          Connection conn = DriverManager.getConnection(dbURL, username, password);

          if (conn != null) {
            System.out.println("Connected");
          }

        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    }

    public static DB_Operations getInstance(){
        return instance;
    }

    public int insertUser(User u){
      int rowsInserted=-1;
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "INSERT INTO Users(uid,uname,email,password) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, u.getUid());
        statement.setString(2, u.getUname());
        statement.setString(3, u.getEmail());
        statement.setString(4, u.getPassword());

        rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
          System.out.println("A new user was inserted successfully!");
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }

      return rowsInserted;
    }

    public int insertRecipe(Recipe r){
      int rowsInserted=-1;
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "INSERT INTO recipe(recipe_id,recipe_name,recipe_desc,u_id) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, r.getRecipe_id());
        statement.setString(2, r.getRecipe_name());
        statement.setString(3, r.getRecipe_desc());
        statement.setString(4, r.getU_id());

        rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
          System.out.println("A new recipe was inserted successfully!");
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }

      return rowsInserted;
    }

    public int updateRecipe(Recipe r){
      int rowsUpdated=-1;
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "UPDATE recipe SET recipe_desc=? WHERE recipe_id=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, r.getRecipe_desc());
        statement.setString(2, r.getRecipe_id());

        rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
          System.out.println("An existing recipe was updated successfully!");
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return rowsUpdated;
    }

    public int removeRecipe(String rec_id){
        int rowsDeleted=-1;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
          String sql = "DELETE FROM recipe WHERE recipe_id=?";

          PreparedStatement statement = conn.prepareStatement(sql);
          statement.setString(1, rec_id);

          rowsDeleted = statement.executeUpdate();
          if (rowsDeleted > 0) {
            System.out.println("A Recipe was deleted successfully!");
          }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
        return rowsDeleted;
    }

    public Recipe readRecipe(String rid){

      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "SELECT * FROM recipe where recipe_id="+"'"+rid+"'";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
          String recipe_name = result.getString(2);
          String recipe_desc = result.getString(3);
          String recipe_ownerID = result.getString(4);
          return new Recipe(rid,recipe_name,recipe_desc,recipe_ownerID);
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return null;
    }

    public List<Recipe> readRecipesByUser(String uid){
      List<Recipe> recipes=new ArrayList<>();
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "SELECT * FROM recipe where u_id="+"'"+uid+"'";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
          String rid=result.getString(1);
          String recipe_name = result.getString(2);
          String recipe_desc = result.getString(3);
          String recipe_ownerID = result.getString(4);
          recipes.add(new Recipe(rid,recipe_name,recipe_desc,recipe_ownerID));
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return recipes;
    }

    public List<Recipe> readRecipes(String rname){
      List<Recipe> recipes=new ArrayList<>();
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "SELECT * FROM recipe where recipe_name LIKE '%"+rname+"%'";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
          String recipe_id = result.getString(1);
          rname=result.getString(2);
          String recipe_desc = result.getString(3);
          String recipe_ownerID = result.getString(4);
          recipes.add(new Recipe(recipe_id,rname,recipe_desc,recipe_ownerID));
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return recipes;
    }

    public List<Recipe> readAllRecipes(){
      List<Recipe> recipes=new ArrayList<>();
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "SELECT * FROM recipe";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
          String recipe_id= result.getString(1);
          String recipe_name = result.getString(2);
          String recipe_desc = result.getString(3);
          String recipe_ownerID = result.getString(4);
          recipes.add(new Recipe(recipe_id,recipe_name,recipe_desc,recipe_ownerID));
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return recipes;
    }

    public List<Review> readRecipeReviews(String rec_id){
      List<Review> reviews=new ArrayList<>();
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "SELECT * FROM review where rec_id= '"+rec_id+"'";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
          String review_id= result.getString(1);
          Float points = result.getFloat(2);
          String u_id = result.getString(3);
          String review_desc = result.getString(5);
          reviews.add(new Review(review_id,u_id,rec_id,points,review_desc));
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      return reviews;
    }

    public User hasAccount(String email, String pass){
      try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
        String sql = "SELECT * FROM Users where email="+"'"+email+"' and password='"+pass+"'";

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
          String uid= result.getString(1);
          String uname=result.getString(2);
          return new User(uid,uname,email,pass);
        }
      } catch (SQLException ex) {
        System.out.println("Error");
        ex.printStackTrace();
      }
      return null;
    }

    public int insertReview(Review r){
      Recipe tempRecipe=readRecipe(r.getRec_id());
      int rowsInserted=-1;
      if(tempRecipe!=null) {
        if (tempRecipe.getU_id().equals(r.getU_id())) { // if User trying to review his/her own recipe
          return -2;
        }
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
          String sql = "INSERT INTO review(review_id,points,u_id,rec_id,review_desc) VALUES (?, ?, ?, ?, ?)";

          PreparedStatement statement = conn.prepareStatement(sql);
          statement.setString(1, r.getReview_id());
          statement.setFloat(2, r.getPoints());
          statement.setString(3, r.getU_id());
          statement.setString(4, r.getRec_id());
          statement.setString(5, r.getReview_desc());

          rowsInserted = statement.executeUpdate();
          if (rowsInserted > 0) {
            System.out.println("A new review was inserted successfully!");
          }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }

      return rowsInserted;
    }
}
