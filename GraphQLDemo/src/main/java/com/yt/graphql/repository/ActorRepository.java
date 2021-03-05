package com.yt.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.yt.graphql.model.Actor;

@Service
public interface ActorRepository extends JpaRepository<Actor,Integer> {

    public Actor findActorByFirstNameLike(String name);
}