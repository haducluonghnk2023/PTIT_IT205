package com.data.session07.controller;

import com.data.session07.model.dto.res.DataResponse;
import com.data.session07.model.entity.Seed;
import com.data.session07.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seeds")
public class SeedController {
    @Autowired
    private SeedService seedService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Seed>>> getAllSeeds() {
        return ResponseEntity.ok(new DataResponse<>(seedService.getAllSeeds(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Seed>> addSeed(@RequestBody Seed seed) {
        return ResponseEntity.ok(new DataResponse<>(seedService.addSeed(seed), HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Seed>> updateSeed(@RequestBody Seed seed, @PathVariable long id) {
        return ResponseEntity.ok(new DataResponse<>(seedService.updateSeed(id, seed), HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Seed>> deleteSeed(@PathVariable long id) {
        seedService.deleteSeed(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
    }
}
