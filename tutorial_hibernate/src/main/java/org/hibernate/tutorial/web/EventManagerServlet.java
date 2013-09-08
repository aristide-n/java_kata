package org.hibernate.tutorial.web;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aristide
 * Date: 9/5/13
 */
public class EventManagerServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            // begin unit of work
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            // process request and render response

            // Write HTML header
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Event Manager</title></head><body>");

            // Handle form data
            if ("store".equals(request.getParameter("action"))) {
                String eventTitle = request.getParameter("eventTitle");
                String eventDate = request.getParameter("eventDate");

                if(eventTitle.isEmpty() || eventDate.isEmpty()) {
                    out.println("<b><i>Please enter event Title and Date!</i></b>");
                }
                else {
                    createAndStoreEvent(eventTitle, dateFormatter.parse(eventDate), session);
                }
            }

            // print input form
            printEventForm(out);

            // print list of events in the DB
            listEvents(out, dateFormatter, session);

            // end unit of work
            session.getTransaction().commit();

            // print HTML footer
            out.println("</body></html>");
            out.flush();
            out.close();
        }
        catch (Exception exc) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if (ServletException.class.isInstance(exc)) {
                throw (ServletException) exc;
            }
            else {
                throw new ServletException(exc);
            }
        }
    }

    //protected
    private void createAndStoreEvent(String eventTitle, Date eventDate, Session session){
        Event theEvent = new Event();
        theEvent.setTitle(eventTitle);
        theEvent.setDate(eventDate);

        session.save(theEvent);
    }

    private void printEventForm(PrintWriter out) {
        out.println("<h2>Add new event:<h2>");
        out.println("<form>");
        out.println("Title: <input name='eventTitle' length=50/><br/>");
        out.println("Date (e.g 12.12.2012): <input name='eventDate' length=10/><br/>");
        out.println("<input type='submit' name='action' value='store'>");
        out.println("</form>");
    }

    private void listEvents(PrintWriter out, SimpleDateFormat dateFormatter, Session session) {
        List allEvents = session.createCriteria(Event.class).list();

        if(allEvents.size() > 0) {
            out.println("<h2>Events in the database:</h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Event title</th>");
            out.println("<th>Event date</th>");
            out.println("</tr>");

            Iterator it = allEvents.iterator();
            while (it.hasNext()){
                Event event = (Event) it.next();
                out.println("<tr>");
                out.println("<td>" + event.getTitle() + "</td>");
                out.println("<td>" + dateFormatter.format(event.getDate()) + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }
    }
}
