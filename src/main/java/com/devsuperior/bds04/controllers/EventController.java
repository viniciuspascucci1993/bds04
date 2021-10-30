package com.devsuperior.bds04.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService service;
	
	@GetMapping
	public ResponseEntity<Page<EventDTO>> findAllPaged( Pageable pageble ) {
		
		PageRequest pageRequest = PageRequest.of(pageble.getPageNumber(), pageble.getPageSize(), Sort.by("name"));
		Page<EventDTO> pageDto = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(pageDto);
	}
	
	@PostMapping
	public ResponseEntity<EventDTO> insertEvent( @Valid @RequestBody EventDTO eventoDto ) {
		
		eventoDto = service.insert(eventoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eventoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(eventoDto);
	}
}
