package com.example.demo;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        DB_Operations db_operations=DB_Operations.getInstance();
        User u1=new User("u1","Zohaib Abbas", "zabbas@experlabs.com", "1234");
        User u2=new User("u2","Ali Ahmed", "ali@experlabs.com", "2345");
        Recipe r=new Recipe("rec1", "Biryani By Zohaib", "Rice, Chicken, Potato, Spices", "u1");
        Recipe r2=new Recipe("rec2", "Coffee By Ali", "Milk, Water, Coffee", "u1");
        Review rw=new Review("rw3","u1","rec1",5,"Very Nice Recipe!");


    }

}
