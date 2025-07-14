package com.data.session05.controller;

import com.data.session05.modal.dto.res.DataResponse;
import com.data.session05.modal.dto.res.FruitDto;
import com.data.session05.modal.entity.Fruit;
import com.data.session05.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    public ResponseEntity<DataResponse<List<FruitDto>>> getAllFruits() {
        return ResponseEntity.ok(new DataResponse<>(fruitService.getAllFruits(), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Fruit>> getFruitById(@PathVariable Long id) {
        Fruit fruit = fruitService.getFruitById(id);
        if (fruit != null) {
            return ResponseEntity.ok(new DataResponse<>(fruit, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Fruit>> createFruit(@RequestBody Fruit fruit) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(fruitService.createFruit(fruit), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Fruit>> updateFruit(@PathVariable Long id, @RequestBody Fruit fruit) {
        Fruit updated = fruitService.updateFruit(id, fruit);
        if (updated != null) {
            return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteFruit(@PathVariable Long id) {
        fruitService.deleteFruit(id);
        return ResponseEntity.ok(new DataResponse<>("Deleted successfully", HttpStatus.OK));
    }
}
