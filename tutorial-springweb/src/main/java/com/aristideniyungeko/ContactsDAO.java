package com.aristideniyungeko;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Data accessor using hibernate.
 */
@Repository
@Transactional
public class ContactsDAO {

   @Autowired private SessionFactory sessionFactory;
   Logger logger = Logger.getRootLogger();

   public Contact getById(int id) {
      return (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
   }

   @SuppressWarnings("unchecked")
   public List<Contact> searchContacts(String name) {
      Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
      criteria.add(Restrictions.ilike("name", name+"%"));
      return criteria.list();
   }

   @SuppressWarnings("unchecked")
   public List<Contact> getAllContacts() {
      Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
      criteria.addOrder(Order.asc("name"));
      return criteria.list();
   }

   public int save(Contact contact) {
      return (Integer) sessionFactory.getCurrentSession().save(contact);
   }

   public void update(Contact contact) {
      sessionFactory.getCurrentSession().merge(contact);
   }

   public void delete(int id) {
      Contact c = getById(id);
      sessionFactory.getCurrentSession().delete(c);
   }
}
