package org.hibernate.tutorial;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 8/31/13
 */
public class EventManager {
    public static void main(String[] args){
        EventManager manager = new EventManager();

        if (args[0].equals("store")){
            manager.createAndStoreEvent("My Event", new Date());
        }
        else if (args[0].equals("list")){
            List events = manager.listEvents();

            System.out.println(events.size() + " events found");

            for (int i = 0; i < events.size(); i++){
                Event theEvent = (Event) events.get(i);
                System.out.println("Event: " + theEvent.getTitle() + " Time: " + theEvent.getDate());
            }
        }
        else if (args[0].equals("addpersontoevent")){
            Long eventId = manager.createAndStoreEvent("My Event", new Date());
            Long personId = manager.createAndStorePerson(1000, "Foo", "Bar");
            manager.addPersonToEvent(personId, eventId);
            System.out.println("Added person " + personId + " to event " + eventId);
        }
        else if (args[0].equals("addemailtoperson")){
            Long personId = manager.createAndStorePerson(10000, "John", "Doe");
            manager.addEmailToPerson(personId, "email@example.com");
            System.out.println("Added email email@example.com to person " + personId);
        }

        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreEvent(String title, Date theTime){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theTime);
        Long eventId = (Long) session.save(theEvent);

        session.getTransaction().commit();

        return eventId;
    }

    private Long createAndStorePerson(int age, String firstName, String lastName){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person thePerson = new Person();
        thePerson.setAge(age);
        thePerson.setFirstName(firstName);
        thePerson.setLastName(lastName);
        Long personId =(Long) session.save(thePerson);

        session.getTransaction().commit();

        return personId;
    }

    private List listEvents(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }

    private void addPersonToEvent(Long personId, Long eventId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.addToEvent(anEvent);

        session.getTransaction().commit();
    }



    private void addEmailToPerson(Long personId, String emailAddress){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        aPerson.getEmailAddresses().add(emailAddress);
//        aPerson.getEmailAddresses();

        session.getTransaction().commit();
    }
}
