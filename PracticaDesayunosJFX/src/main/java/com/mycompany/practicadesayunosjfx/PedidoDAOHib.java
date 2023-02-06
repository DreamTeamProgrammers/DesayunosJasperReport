package com.mycompany.practicadesayunosjfx;

import java.util.ArrayList;
import java.util.List;
import models.Pedido;
import org.hibernate.DuplicateMappingException.Type;
import org.hibernate.Transaction;

/**
 *
 * @author anton
 */
public class PedidoDAOHib implements PedidoDAO {

    @Override
    public void save(Pedido p) {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.save(p);
            t.commit();
        }
    }

    @Override
    public void update(Pedido p) {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(p);
            t.commit();
        }
    }

    @Override
    public Pedido get(Integer id) {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            return s.get(Pedido.class, id);
        }
    }

    @Override
    public void delete(Pedido p) {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.delete(p);
            t.commit();
        }
    }

    @Override
    public List<Pedido> getAll() {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("from Pedido");
            return q.list();
        }

    }

    @Override
    public Long getTotalPedidos() {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("SELECT COUNT(idpedido)FROM Pedido");

            return (Long) q.uniqueResult();
        }
    }
    
    @Override
    public List<Pedido> getTotalPedidosOrdenados() {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("FROM Pedido ORDER BY fecha");

            return q.list();
        }
    }

    @Override
    public String getBestCostumer() {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("SELECT p.cliente\n"
                    + "FROM Pedido p\n"
                    + "group by p.cliente\n"
                    + "order by count(p.cliente) DESC");

            return q.list().get(0).toString();
        }
    }

    @Override
    public List<Pedido> getPedidosHoy() {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("FROM Pedido WHERE fecha LIKE '2023-02-06%'");

            return q.list();
        }
    }

    @Override
    public String getProductoPopular() {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("SELECT c.nombre\n"
                    + "FROM Carta c, Pedido p\n"
                    + "WHERE c.idproducto = p.producto\n"
                    + "group by producto\n"
                    + "order by count(producto) DESC");

            return q.list().get(0).toString();
        }
    }

    @Override
    public String getGananciaTotal() {
        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
            var q = s.createQuery("SELECT sum(c.precio) \n" +
                                    "FROM Carta c, Pedido p\n" +
                                    "WHERE p.producto = c.idproducto");

            return q.list().get(0).toString();
        }
    }

}
