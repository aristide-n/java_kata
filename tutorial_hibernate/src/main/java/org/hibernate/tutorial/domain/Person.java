package org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 9/1/13
 */
public class Person {
    private Long id;
    private int age;
    private String firstName;
    private String lastName;

    private Set events = new HashSet();
    private Set emailAddresses = new HashSet();

    public Person(){}

    public Long getId(){
        return id;
    }

    private void setId(Long theId){
        this.id = theId;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int theAage){
        this.age = theAage;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String theFirsName){
        this.firstName = theFirsName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String theLastName){
        this.lastName = theLastName;
    }

    protected Set getEvents(){
        return events;
    }

    protected void setEvents(Set theEvents){
        this.events = theEvents;
    }

    public Set getEmailAddresses(){
        return emailAddresses;
    }

    public void setEmailAddresses(Set theEmailAddresses){
        this.emailAddresses = theEmailAddresses;
    }

    public void addToEvent(Event theEvent){
        this.getEvents().add(theEvent);
        theEvent.getParticipants().add(this);
    }

    public void removeFromEvent(Event theEvent){
        this.getEvents().remove(theEvent);
        theEvent.getParticipants().remove(this);
    }

}
