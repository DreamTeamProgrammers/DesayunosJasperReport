
package com.mycompany.practicadesayunosjfx;

import java.util.ArrayList;
import java.util.List;
import models.Carta;
import org.hibernate.Transaction;

/**
 *
 * @author anton
 */
public class CartaDAOHib implements CartaDAO {

    @Override
    public void save(Carta c) {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.save(c);
            t.commit();
        }
    }

    @Override
    public void update(Carta c) {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.update(c);
            t.commit();
        }
    }

    @Override
    public Carta get(Integer id) {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            return s.get(Carta.class,id);     
        }
    }

    @Override
    public void delete(Carta c) {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.delete(c);
            t.commit();
        }
    }

    @Override
    public List<Carta> getAll() {
        try(var s = HibernateUtil.getSessionFactory().openSession()){
            var q = s.createQuery("from Carta");
            return q.list();
        }
    }
    
}
