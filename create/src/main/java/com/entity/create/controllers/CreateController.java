package com.entity.create.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.create.requests.EntityCreateRequest;
import com.entity.create.services.CreateService;

@RestController
@RequestMapping()
public class CreateController {
	@Autowired
	private CreateService createService;
	
	@PostMapping("/entity/creator")
	public String entityCreator(@RequestBody EntityCreateRequest entityCreateRequest){
		return createService.entityCreator(entityCreateRequest);
	}
	
	@PostMapping("/repository/creator")
	public String repositoryCreator(@RequestBody EntityCreateRequest repositoryCreateRequest) {
		return createService.repositoryCreator(repositoryCreateRequest);
	}
}
