package com.data.session14.controller;

import com.data.session14.modal.dto.req.MovieRequest;
import com.data.session14.modal.dto.res.APIResponse;
import com.data.session14.modal.entity.Movie;
import com.data.session14.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
public class MovieAdminController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<?>  postMovie(@RequestBody MovieRequest mr){
        return ResponseEntity.ok(new APIResponse<>("Thêm phim thành công",movieService.createMovie(mr),true,HttpStatus.CREATED));
    }

    // Cập nhật phim theo ID
    @PutMapping("{id}")
    public ResponseEntity<APIResponse<Movie>> updateMovie(@PathVariable Long id, @RequestBody MovieRequest mr) {
        Movie updated = movieService.updateMovie(id, mr);
        return ResponseEntity.ok(
                new APIResponse<>("Cập nhật phim thành công", updated, true, HttpStatus.OK)
        );
    }

    // Xóa phim theo ID
    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<String>> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok(
                new APIResponse<>("Xóa phim thành công", null, true, HttpStatus.OK)
        );
    }

}
