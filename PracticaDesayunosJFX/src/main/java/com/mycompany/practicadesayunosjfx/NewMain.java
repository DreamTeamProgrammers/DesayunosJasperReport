package com.mycompany.practicadesayunosjfx;

import java.util.List;
import models.Carta;
import models.Pedido;

/**
 *
 * @author anton
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        JdbcUtil conexion = new JdbcUtil();

    }
}
//        PedidoDAOHib gestorPedidos = new PedidoDAOHib();
//        CartaDAOHib gestorCarta = new CartaDAOHib();
//
//        System.out.println(gestorPedidos.getAll());
//
//        System.out.println(gestorPedidos.getTotalPedidos());
//
//        System.out.println(gestorPedidos.getPedidosHoy());
//
////        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
////            var q = s.createQuery("SELECT p.cliente\n"
////                    + "FROM Pedido p\n"
////                    + "group by p.cliente\n"
////                    + "order by count(p.cliente) DESC");
////
////            List results = q.list();
////            System.out.println(results.subList(0, 1));
//
////        }
//        System.out.println("----------------------------------------------");
//        System.out.println(gestorPedidos.getBestCostumer());
//        System.out.println("----------------------------------------------");
//        
////        try ( var s = HibernateUtil.getSessionFactory().openSession()) {
////            var q = s.createQuery("SELECT SUM(c.precio) \n" +
////                                    "FROM Carta c, Pedido p\n" +
////                                    "WHERE p.producto = c.idproducto");
////
////            String resultado = q.list().get(0).toString();
////            System.out.println(resultado);
////        }
//        System.out.println("----------------------------------");
//        System.out.println(gestorPedidos.getGananciaTotal());
//    }

