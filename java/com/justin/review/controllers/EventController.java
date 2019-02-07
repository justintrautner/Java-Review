package com.justin.review.controllers;



import java.util.Calendar;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.justin.review.models.Event;
import com.justin.review.models.Message;
import com.justin.review.models.User;
import com.justin.review.services.EventService;
import com.justin.review.services.UserService;

@Controller
public class EventController {
	
	
	private final EventService eventService;
	private final UserService userService;
	
	public EventController(EventService eventService, UserService userService) {
		this.eventService=eventService;
		this.userService=userService;
	}
//	FIND EVENT BY ID
	@GetMapping("/events/{id}")
	public String showEvent(@PathVariable("id") Long id, Model model) {
		Event event = eventService.findEventById(id);
		model.addAttribute("event", event);

		return "eventInfo.jsp";
	}
//	POST MESSAGE
	@PostMapping("/events/{id}")
	public String postmessage(@PathVariable("id") Long id,HttpSession session, @RequestParam("content") String content) {
//		FIND EVENT BY ID
		
		Event event = eventService.findEventById(id);
		
//		FIND USER BY ID	
    	Long u_id=(Long) session.getAttribute("u_id");
    	User u=userService.findUserById(u_id);
   
//    	CREATE MESSAGE
    	Message newMessage = new Message();
    	newMessage.setContent(content);
    	newMessage.setEvent(event);
    	newMessage.setUser(u);
    	Message createdMsg=eventService.createMessage(newMessage);
//    	ADD MESSAGE TO EVENT
    	List<Message> eventMsgs=event.getMessages();
    	eventMsgs.add(createdMsg);
    	event.setMessages(eventMsgs);
    	eventService.updateEvent(event);
        return "redirect:/events/"+id;
	}
	
//	CREATE EVENT
	@PostMapping("/events")
	public String createEvent(@Valid @ModelAttribute("newevent") Event newevent, BindingResult result, HttpSession session, Model model) {
//		GET USER
    	Long u_id=(Long) session.getAttribute("u_id");
    	model.addAttribute("user_id", u_id);
    	User u=userService.findUserById(u_id);
//    	CHECK DATE
    	Calendar formDate = Calendar.getInstance();
    	Calendar today = Calendar.getInstance();
    	formDate.setTime(newevent.getDate());
    	if(today.compareTo(formDate)>0) {
    		model.addAttribute("dateError", "Date must be after today");
    		return "homePage.jsp";
    	}
    	
//		CREATE EVENT
		if(result.hasErrors()) {
			return "homePage.jsp";
		}else {
			System.out.println(newevent.getDate());
	    	newevent.setHostUser(u);
			eventService.createEvent(newevent);
			return "redirect:/events";
		}
	}
//	DELETE EVENT
	@DeleteMapping("/events/{id}")
	public String destroy(@PathVariable("id") Long id) {
		eventService.deleteEvent(id);
		return "redirect:/events";
	}
//	JOIN EVENT
	@PostMapping("/events/{id}/join")
	public String join(@PathVariable("id") Long id, HttpSession session, Model model) {
//		GET USER
    	Long u_id=(Long) session.getAttribute("u_id");
    	User u=userService.findUserById(u_id);
    	model.addAttribute("user", u);
//    	GET EVENT
    	Event event = eventService.findEventById(id);
    	System.out.println(event.getName());
    	System.out.println(u.getFirst_name());
//    	ADD EVENT TO USER'S EVENT LIST
    	List<Event> eventsList = u.getAttendedEvents();
    	eventsList.add(event);
    	u.setAttendedEvents(eventsList);
    	userService.updateUser(u);
		return "redirect:/events";
	}
//	UNJOIN EVENT
	@PostMapping("/events/{id}/cancel")
	public String cancel(@PathVariable("id") Long id, HttpSession session, Model model) {
//		GET USER
    	Long u_id=(Long) session.getAttribute("u_id");
    	User u=userService.findUserById(u_id);
    	model.addAttribute("user", u);
//    	GET EVENT
    	Event event = eventService.findEventById(id);
    	System.out.println(event.getName());
    	System.out.println(u.getFirst_name());
//    	DESTROY EVENT FROM USER'S EVENT LIST
    	List<Event> eventsList = u.getAttendedEvents();
    	eventsList.remove(event);
    	u.setAttendedEvents(eventsList);
    	userService.updateUser(u);
    	return "redirect:/events";
	}
//	EDIT PAGE FOR EVENT
	@GetMapping("/events/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model, @Valid @ModelAttribute("updatedevent") Event updatedevent, HttpSession session) {
//		GET EVENT
		Event event = eventService.findEventById(id);
//		GET USER
    	Long u_id=(Long) session.getAttribute("u_id");
//    	CHECK EVENT HOST VS USER
    	if(event.getHostUser().getId().equals(u_id)) {
    		model.addAttribute("event", event);
    		return "editPage.jsp";
    	}else {
    		return "redirect:/events";
    	}	
	}
//	EDIT EVENT REQUEST
	@PutMapping("/events/{id}/edit")
	public String edit(@Valid @ModelAttribute("updatedevent") Event updatedevent, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "editPage.jsp";
		}else {
//			GET USER
	    	Long u_id=(Long) session.getAttribute("u_id");
	    	User u=userService.findUserById(u_id);
	    	updatedevent.setHostUser(u);
			eventService.updateEvent(updatedevent);
			return "redirect:/events";
		}

	}
	
	
	
	
	
	
	

}
