package com.example.imersaojava;

import com.example.imersaojava.service.MoviesService;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        MoviesService.findMostPopularMovies();
    }
}