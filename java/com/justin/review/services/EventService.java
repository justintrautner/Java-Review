package com.justin.review.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.justin.review.models.Event;
import com.justin.review.models.Message;
import com.justin.review.models.User;
import com.justin.review.repositories.EventRepository;
import com.justin.review.repositories.MessageRepository;

@Service
public class EventService {
	
	private final EventRepository eventRepo;
	private final MessageRepository msgRepo;
	
	public EventService(EventRepository eventRepo, MessageRepository msgRepo) {
		this.eventRepo=eventRepo;
		this.msgRepo=msgRepo;
	}
//	EVENTS IN MY STATE
	public List<Event> findEventsInState(User u) {
		return eventRepo.findByState(u.getState());
	}
//	EVENTS NOT IN MY STATE
	public List<Event> findEventsNotInState(User u) {
		return eventRepo.findByStateNot(u.getState());
	}
//	FIND EVENT BY ID
	public Event findEventById(Long id) {
		Optional<Event> event=eventRepo.findById(id);
		if(event.isPresent()) {
			return event.get();
		}else {
			return null;
		}
	}
//	CREATE EVENT
	public Event createEvent(@Valid Event newevent) {
		return eventRepo.save(newevent);
		
	}
//	DELETE EVENT
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);	
	}
//	UPDATE EVENT
	public Event updateEvent(Event updatedevent) {
		return eventRepo.save(updatedevent);
		
	}
	public Message createMessage(Message newMessage) {
		return msgRepo.save(newMessage);
	}





}
