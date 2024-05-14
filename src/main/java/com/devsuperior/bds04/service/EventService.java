package com.devsuperior.bds04.service;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import com.devsuperior.bds04.service.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = eventRepository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = eventRepository.save(entity);
			return new EventDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}	
	}
	
	@Transactional
	public @Valid EventDTO insert(@Valid EventDTO dto) {
		
		Event entity = new Event();
		copyDTOToEntity(dto, entity);
		entity = eventRepository.save(entity);
		return new EventDTO(entity);
		
	}

	private void copyDTOToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(new City(dto.getCityId(),null));
	}

	

}
