package com.devsuperior.bds04.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.service.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventResource {
	
	@Autowired
	private EventService eventService;
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EventDTO> update (@PathVariable Long id, @RequestBody EventDTO dto){
		dto = eventService.update(id,dto);
		return ResponseEntity.ok().body(dto);
		
	}

}
