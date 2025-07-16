package com.data.session07.controller;

import com.data.session07.model.dto.res.DataResponse;
import com.data.session07.model.entity.Worker;
import com.data.session07.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Worker>>> getAllWorkers() {
        return ResponseEntity.ok(new DataResponse<>(workerService.getAllWorkers(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Worker>> addWorker(@RequestBody Worker worker) {
        return ResponseEntity.ok(new DataResponse<>(workerService.addWorker(worker), HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Worker>> updateWorker(@RequestBody Worker worker, @PathVariable long id) {
        return ResponseEntity.ok(new DataResponse<>(workerService.updateWorker(id, worker), HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Worker>> deleteWorker(@PathVariable long id) {
        workerService.deleteWorker(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
    }
}
