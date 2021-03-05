package com.yt.graphql.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.yt.graphql.model.Actor;
import com.yt.graphql.model.ActorMuatationInput;
import com.yt.graphql.repository.ActorRepository;
import com.yt.graphql.repository.FilmRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
public class ActorService implements GraphQLQueryResolver,GraphQLMutationResolver,GraphQLSubscriptionResolver {

	@Autowired
	private ActorRepository actorRepository;
	 private ConcurrentHashMap<Integer, FluxSink<Actor>> subscribers = new ConcurrentHashMap<>();


	@Autowired
	private FilmRepository filmRepository;

	public List<Actor> getAllActors() {
		return actorRepository.findAll();

	}

	public Actor getActorById(Integer actorId) {
		return actorRepository.findById(actorId).get();
	}

	public Actor getActorByNameLike(String name) {
		return actorRepository.findActorByFirstNameLike(name);
	}
	
	public Actor updateActorAddress(ActorMuatationInput input) {
		Optional<Actor> actors = actorRepository.findById(input.getActorId());
		Actor actor = actors.get();
		actor.setAddress(input.getAddress());
		actorRepository.save(actor);
		 if(subscribers.get(input.getActorId())!=null){
	            subscribers.get(input.getActorId()).next(actor);
	        }
		return actor;
	}

	public Publisher<Actor> onActorUpdate(Integer actorId) {
		return Flux.create(subscriber-> subscribers.put(actorId,subscriber.onDispose(()->subscribers.remove(actorId,subscriber))), FluxSink.OverflowStrategy.LATEST);
	}

}
