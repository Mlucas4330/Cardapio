package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FoodController {
    @Autowired
    private FoodRepository repository;
    @GetMapping
    public ResponseEntity<List<Food>> findAll(){
        List<Food> foods = repository.findAll();
        return ResponseEntity.ok(foods);
    }

    @GetMapping("{id}")
    public ResponseEntity<Food> findFood(@PathVariable("id") Long id) {
        Optional<Food> foodOptional = repository.findById(id);

        if (foodOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Food food = foodOptional.get();
        return ResponseEntity.ok(food);
    }

    @PostMapping
    public ResponseEntity<Food> saveFood(@RequestBody Food _food){
        try {
            Food food = repository.save(_food);
            return ResponseEntity.ok(food);
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") Long id, @RequestBody Food _food) {
        Optional<Food> optionalFood = repository.findById(id);

        if(optionalFood.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Food food = optionalFood.get();
        food.setTitle(_food.getTitle());
        food.setPrice(_food.getPrice());
        food.setImage(_food.getImage());

        try {
            Food updatedFood = repository.save(food);
            return ResponseEntity.ok(updatedFood);
        } catch(Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> destroyFood(@PathVariable("id") Long id){
        try {
            repository.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
