package com.yt.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.yt.graphql.model.Film;

@Service
public interface FilmRepository extends JpaRepository<Film, Integer> {

}