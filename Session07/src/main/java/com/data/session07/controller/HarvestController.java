package com.data.session07.controller;

import com.data.session07.model.dto.res.DataResponse;
import com.data.session07.model.entity.Harvest;
import com.data.session07.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("harvests")
public class HarvestController {
    @Autowired
    private HarvestService harvestService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Harvest>>> getAllHarvests() {
        return ResponseEntity.ok(new DataResponse<>(harvestService.getAllHarvests(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Harvest>> addHarvest(@RequestBody Harvest harvest) {
        return  ResponseEntity.ok(new DataResponse<>(harvestService.addHarvest(harvest), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Harvest>> getHarvestById(@PathVariable Long id) {
        return ResponseEntity.ok(new DataResponse<>(harvestService.getHarvestById(id), HttpStatus.OK));
    }

}
