package com.entity.create.services;

import com.entity.create.requests.EntityCreateRequest;

public interface CreateService {

	public String entityCreator(EntityCreateRequest entityCreateRequest);

	public String repositoryCreator(EntityCreateRequest repositoryCreateRequest);

}
