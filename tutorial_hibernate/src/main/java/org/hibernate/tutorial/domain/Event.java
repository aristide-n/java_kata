package org.hibernate.tutorial.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 8/23/13
 */
public class Event {
    private Long id;
    private String title;
    private Date date;

    private Set participants = new HashSet();

    public Event(){}

    public Long getId(){
        return id;
    }

    private void setId(Long id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    protected Set getParticipants(){
        return participants;
    }

    protected void setParticipants(Set theParticipants){
        this.participants = theParticipants;
    }

    public void addToPerson(Person thePerson){
        this.getParticipants().add(thePerson);
        thePerson.getEvents().add(this);
    }

    public void removeFromPerson(Person thePerson){
        this.getParticipants().remove(thePerson);
        thePerson.getEvents().remove(this);
    }
}
