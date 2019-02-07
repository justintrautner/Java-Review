package com.justin.review.models;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="events")
public class Event {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=3)
    private String name;
    private Date date;
    @Size(min=3)
    private String location;
    @Size(min=2, max=2)
    private String state;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
//    RELATIONSHIPS
    
//    HOST RELATIONSHIP
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User hostUser;
//  GUESTS RELATIONSHIP
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "events_guests", 
      joinColumns = @JoinColumn(name = "event_id"), 
      inverseJoinColumns = @JoinColumn(name = "user_id")
  ) 
  private List<User>attendingUsers;
  
//  MESSAGES RELATIONSHIP
  @OneToMany(mappedBy="event", fetch =FetchType.LAZY)
  private List<Message> messages;

      
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    public Event() {
    	
    }
    
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String date) {
		
	    this.date = SDF.parse(date, new ParsePosition(0));
	    
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getHostUser() {
		return hostUser;
	}
	public void setHostUser(User hostUser) {
		this.hostUser = hostUser;
	}
	public List<User> getAttendingUsers() {
		return attendingUsers;
	}
	public void setAttendingUsers(List<User> attendingUsers) {
		this.attendingUsers = attendingUsers;
	}

	

    
    
    
    
    
    
    
    
}