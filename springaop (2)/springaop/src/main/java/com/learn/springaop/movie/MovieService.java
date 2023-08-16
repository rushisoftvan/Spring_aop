package com.learn.springaop.movie;

import com.learn.springaop.annotation.Log;
import com.learn.springaop.annotation.Transaction;
import org.springframework.stereotype.Service;

@Service
public class MovieService {


    @Log
    @Transaction
    public MovieDto fetchMovieWithAop(Integer movieId, String movieName) {
        checkMovieId(movieId);

        return new MovieDto(movieId,movieName);
    }

     /*
        - Note : Spring aop is not working on private Method  @Log
        because when create proxy class of this class. this proxy class extends main class of proxy
        private method should not be extends that's why Spring aop not working on private method
      */

    @Log
    private static void checkMovieId(Integer movieId) {
        if(movieId <1){
            throw new IllegalArgumentException("Movie id should not be less then 1");
        }
    }

}
