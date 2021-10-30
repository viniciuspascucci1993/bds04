package com.devsuperior.bds04.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositores.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged( Pageable pageble) {
		Page<Event> listEmployees = eventRepository.findAll(pageble);
		return listEmployees.map(x -> new EventDTO(x));
	}
	
	@Transactional
	public EventDTO insert(EventDTO eventDto) {
		Event entity = new Event();
		entity.setName(eventDto.getName());
		entity.setDate(eventDto.getDate());
		entity.setUrl(eventDto.getUrl());
		entity.setCity(new City(1L, eventDto.getName()));
		
		entity = eventRepository.save(entity);
		
		return new EventDTO(entity);
	}
}
