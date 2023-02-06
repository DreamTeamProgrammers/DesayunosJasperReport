package com.mycompany.practicadesayunosjfx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 *
 * @author anton
 */
public class EstadisticasController implements Initializable {

    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnCarta;

    private PedidoDAOHib gestorPedidos = new PedidoDAOHib();
    private CartaDAOHib gestorCarta = new CartaDAOHib();

    @FXML
    private TextField txtProductoPopular;
    @FXML
    private TextField txtMejorCliente;
    @FXML
    private TextField txtGanancia;
    @FXML
    private TextField txtTotalPedidos;
    @FXML
    private MenuItem mEstadisticas1;
    @FXML
    private MenuItem mCerrar1;

    @FXML
    private void volverPedidos(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void volverCarta(ActionEvent event) throws IOException {
        App.setRoot("carta");
    }

    @FXML
    private void abrirEstadisticas(ActionEvent event) throws IOException {
        App.setRoot("estadisticas");
    }

    @FXML
    private void cerrarAplicacion(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PedidoDAOHib gestorPedidos = new PedidoDAOHib();
        Long totalPedidos = gestorPedidos.getTotalPedidos();
        String mejorCliente = gestorPedidos.getBestCostumer().toString();
        String productoPopular = gestorPedidos.getProductoPopular().toString();
        String gananciasTotales = gestorPedidos.getGananciaTotal();
        
        txtTotalPedidos.setText(totalPedidos.toString());
        txtMejorCliente.setText(mejorCliente);
        txtProductoPopular.setText(productoPopular);
        txtGanancia.setText(gananciasTotales);
    }

    @FXML
    private void abrirCarta(ActionEvent event) throws IOException {
        App.setRoot("carta");
    }

    @FXML
    private void abrirPedidos(ActionEvent event) throws IOException {
        App.setRoot("primary");

    }

}
