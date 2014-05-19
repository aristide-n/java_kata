package org.hibernate.tutorial.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 8/31/13
 */
public class HibernateUtil {
   private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

   private static SessionFactory buildSessionFactory() {
      try {
         // Create the SessionFactory from hibernate.cfg.xml configuration
         return new Configuration().configure().buildSessionFactory();
      } catch(Throwable exc) {
         // Log the exception
         System.err.print("\nInitial SessionFactory creation failed.\n" + exc + "  \n");
         throw new ExceptionInInitializerError(exc);
      }
   }

   public static SessionFactory getSessionFactory() {
      return SESSION_FACTORY;
   }
}
