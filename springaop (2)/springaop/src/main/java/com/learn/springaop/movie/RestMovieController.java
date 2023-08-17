package com.learn.springaop.movie;

import com.learn.springaop.annotation.AroundExample;
import com.learn.springaop.annotation.Log;
import com.learn.springaop.annotation.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestMovieController {
   
    private final MovieService movieService;

    @Log
    @GetMapping("movies/{id}/{movieName}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable("id") Integer movieId, @PathVariable("movieName") String movieName){
        MovieDto omg = this.movieService.fetchMovieWithAop(movieId, movieName);
        return ResponseEntity.ok(omg);
    }


    @AroundExample
    @GetMapping("/Around/{id}")
    public void checkAroundExample(@PathVariable("id") Integer id){
        if(id>2){
            System.out.println("rushikesh");
            System.out.println(id);
        }
        else{
            throw new IllegalArgumentException("id should not be less then 2");
        }

    }

}
