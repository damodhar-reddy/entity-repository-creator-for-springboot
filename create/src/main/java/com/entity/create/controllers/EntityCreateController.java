package com.entity.create.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.create.requests.EntityCreateRequest;
import com.entity.create.services.EntityCreateService;

@RestController
@RequestMapping("/entity")
public class EntityCreateController {
	@Autowired
	private EntityCreateService entityCreateService;
	
	@PostMapping("/creator")
	public String entityCreator(@RequestBody EntityCreateRequest entityCreateRequest){
		return entityCreateService.entityCreator(entityCreateRequest);
	}
}
