package com.mycompany.practicadesayunosjfx;

import java.util.ArrayList;
import java.util.List;
import models.Pedido;

/**
 *
 * @author LorenLynchMcrae
 */
public interface PedidoDAO {
   
    public void save( Pedido m);
    public void update( Pedido m);
    public Pedido get( Integer id);
    public void delete( Pedido m);
    public List<Pedido> getAll();
    public Long getTotalPedidos();
    public List<Pedido> getTotalPedidosOrdenados();
    public String getBestCostumer();
    public List<Pedido> getPedidosHoy();
    public String getProductoPopular();
    public String getGananciaTotal();
}
