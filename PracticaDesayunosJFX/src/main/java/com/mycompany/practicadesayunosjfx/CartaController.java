package com.mycompany.practicadesayunosjfx;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import models.Carta;
import models.Pedido;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CartaController implements Initializable{

    private CartaDAOHib gestorCarta = new CartaDAOHib();
    private PedidoDAOHib gestorPedidos = new PedidoDAOHib();
    private Informe informe = new Informe();
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    private TextField txtDisponibilidad;
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnVolver;
    @FXML
    private TableView<Carta> tabla;
    @FXML
    private TableColumn<Carta, String> cNombre;
    @FXML
    private TableColumn<Carta, Integer> cPrecio;
    @FXML
    private TableColumn<Carta, String> cDisponibilidad;

    private Carta cartaActual = null;
    @FXML
    private MenuItem mEstadisticas1;
    @FXML
    private MenuItem mCerrar1;
    @FXML
    private Label info;
    @FXML
    private ComboBox<String> comboDisponibilidad;
    @FXML
    private Button Informe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        cPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        cDisponibilidad.setCellValueFactory(new PropertyValueFactory("disponibilidad"));
        
        llenarCombo();
        actualizarTabla();
    }
    
    private void actualizarTabla() {
        List<Carta> carta = new ArrayList();
        carta = gestorCarta.getAll();   
        ObservableList<Carta> carta1 = FXCollections.observableArrayList();
        carta1.addAll(carta);

        tabla.getItems().clear();
        tabla.getItems().addAll(carta1);
    }
    
    public void llenarCombo() {
        ObservableList<String> disponibilidad = FXCollections.observableArrayList();
        String[] disponible = {"Disponible", "Agotado"};
        disponibilidad.addAll(disponible);
        comboDisponibilidad.setItems(disponibilidad);
    }
    
    
    @FXML
    private void añadirProducto(ActionEvent event) {
        
        String nombre = txtNombre.getText();
        Double precio = Double.parseDouble(txtPrecio.getText());
        String disponibilidad = comboDisponibilidad.getValue();
        
        cartaActual.setNombre(nombre);
        cartaActual.setPrecio(precio);
        cartaActual.setDisponibilidad(disponibilidad);
        
        gestorCarta.save(cartaActual);
        actualizarTabla();
        
        info.setText("Producto "+cartaActual.getNombre()+ " añadido con éxito!");
        info.setStyle("-fx-background-color:#00FF00; -fx-text-fill:white; -fx-font-weight: bold");
    }

    @FXML
    private void actualizarProducto(ActionEvent event) {
        String nombre = txtNombre.getText();
        Double precio = Double.parseDouble(txtPrecio.getText());
        String disponibilidad = txtDisponibilidad.getText();
        
        cartaActual.setNombre(nombre);
        cartaActual.setPrecio(precio);
        cartaActual.setDisponibilidad(disponibilidad);
        
        gestorCarta.update(cartaActual);
        
        actualizarTabla();
        
        info.setText("Pedido "+cartaActual.getNombre()+ " actualizado con éxito!");
        info.setStyle("-fx-background-color:#00FF00; -fx-text-fill:white; -fx-font-weight: bold");
    }

    @FXML
    private void borrarProducto(ActionEvent event) {
        Carta carta = tabla.getSelectionModel().getSelectedItem();
                
        gestorCarta.delete(carta);
        actualizarTabla();
        
        info.setText("Pedido "+cartaActual.getNombre()+ " borrado con éxito!");
        info.setStyle("-fx-background-color:#00FF00; -fx-text-fill:white; -fx-font-weight: bold");
    }

    @FXML
    private void mostrarTarea(MouseEvent event) {
        Carta carta = tabla.getSelectionModel().getSelectedItem();

        System.out.println(tabla.getSelectionModel().getSelectedIndex());

        if (carta != null) {
            txtNombre.setText("" + carta.getNombre().toString());
            txtPrecio.setText(carta.getPrecio().toString());
            comboDisponibilidad.setValue(carta.getDisponibilidad().toString());

            cartaActual = carta;

        }
    }


    @FXML
    private void abrirPedidos(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void abrirCarta(ActionEvent event) throws IOException {
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

    @FXML
    private void verInforme(ActionEvent event) {
        try {
            informe.showReport("carta");
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            informe.pdfReport("carta");
        } catch (JRException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}