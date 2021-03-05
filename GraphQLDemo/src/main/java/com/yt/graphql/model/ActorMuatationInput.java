package com.yt.graphql.model;

import graphql.schema.GraphQLInputType;
import lombok.Data;
@Data
public class ActorMuatationInput implements GraphQLInputType {
	private Integer actorId;
	private String address;
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "actorMutationInput";
	}
	

}
