
package com.mycompany.practicadesayunosjfx;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory;
    
    static{
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch( HibernateException ex){
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    
}
