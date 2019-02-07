package com.justin.review.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.justin.review.models.Event;
import com.justin.review.models.User;
import com.justin.review.services.EventService;
import com.justin.review.services.UserService;
import com.justin.review.validator.UserValidator;

@Controller
public class UserController {
	
	private final UserService userService;
	private final EventService eventService;
	private final UserValidator userValidator;
	
    public UserController(UserService userService, UserValidator userValidator, EventService eventService) {
        this.userService = userService;
        this.userValidator=userValidator;
        this.eventService=eventService;
    }
	
    @GetMapping("/")
    public String signIn(@ModelAttribute("user") User user) {
    	return "signIn.jsp";
    }
//    REGISTER USER
    @PostMapping(value="/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
        if(result.hasErrors()) {
        	return "signIn.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("u_id", u.getId());
        return "redirect:/events";
    }
//    LOGIN USER
    @PostMapping(value="/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, @ModelAttribute("user") User user) {
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if(isAuthenticated) {
        	User u = userService.findByEmail(email);
        	session.setAttribute("u_id", u.getId());
        	return "redirect:/events";
        }else {
        	model.addAttribute("error", "Invalid Credentials");
        	return "signIn.jsp";
        }
    }
//    HOME PAGE
    @GetMapping("/events")
    public String home(HttpSession session, Model model, @Valid @ModelAttribute("newevent") Event newevent) {
//    	FIND USER FROM SESSION
    	if(session.getAttribute("u_id")==null) {
    		return "redirect:/";
    	}
    	Long u_id=(Long) session.getAttribute("u_id");
    	User u=userService.findUserById(u_id);
    	model.addAttribute("user", u);
//    	FIND EVENTS IN MY STATE
    	List<Event> eventsInState= eventService.findEventsInState(u);
    	model.addAttribute("eventsInState", eventsInState);
//    	FIND EVENTS NOT IN MY STATE
    	List<Event> eventsNotInState=eventService.findEventsNotInState(u);
    	model.addAttribute("eventsNotInState", eventsNotInState);
    	return "homePage.jsp";
    }
    
    
//    LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    
    
    
    
    
    
    
    
    
}
