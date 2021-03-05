package com.yt.graphql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.yt.graphql.model.Actor;
import com.yt.graphql.model.Film;
import com.yt.graphql.repository.FilmRepository;

@Service
public class FilmService implements GraphQLResolver<Actor> {

    @Autowired
    private FilmRepository repository;


    public Film getFilm(Actor actor){
        return repository.findById(actor.getFilmId()).get();
    }
    
}

