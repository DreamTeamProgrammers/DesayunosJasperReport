package com.mycompany.practicadesayunosjfx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

public class PrimaryController implements Initializable {

    private Informe informe = new Informe();
    private PedidoDAOHib gestorPedidos = new PedidoDAOHib();
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtCliente;
    private TextField txtProducto;
    private TextField txtEstado;
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnCarta;
    @FXML
    private TableView<Pedido> tabla;
    @FXML
    private TableColumn<Pedido, LocalDate> cFecha;
    @FXML
    private TableColumn<Pedido, String> cCliente;
    @FXML
    private TableColumn<Pedido, String> cProducto;
    @FXML
    private TableColumn<Pedido, String> cEstado;
    @FXML
    private MenuItem mEstadisticas;
    @FXML
    private MenuItem mCerrar;

    private Pedido pedidoActual = null;

    LocalDate date = LocalDate.now();
    @FXML
    private ComboBox<Carta> comboProducto;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Label info;
    @FXML
    private Button btnInforme;
    @FXML
    private DatePicker dataPicker1;
    @FXML
    private DatePicker dataPicker2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        cCliente.setCellValueFactory(new PropertyValueFactory("cliente"));

        cProducto.setCellValueFactory((var fila) -> {
            Pedido pedido = fila.getValue();
            return new ReadOnlyObjectWrapper(pedido.getProducto().getNombre());
        });

        //cEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        cEstado.setCellValueFactory((var fila) -> {
            Pedido a = fila.getValue();
            if (a.getEstado()) {
                return new SimpleObjectProperty("Recogido");
            } else {
                return new SimpleStringProperty("Pendiente");
            }

        });
        llenarCombo();

        actualizarTabla();

    }

    public ArrayList<Carta> traerProductos() {
        ArrayList<Carta> productos = new ArrayList<>();
        try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query q = s.createQuery("from Carta");
            productos = (ArrayList<Carta>) q.list();
        }
        return productos;
    }

    public void llenarCombo() {
        ObservableList<Carta> productos = FXCollections.observableArrayList();
        for (Carta producto : traerProductos()) {
            productos.add(producto);
        }
        comboProducto.setItems(productos);
        comboProducto.setConverter(new StringConverter<Carta>() {
            @Override
            public String toString(Carta object) {
                if (object != null) {
                    return object.getNombre();
                } else {
                    return "";
                }
            }

            @Override
            public Carta fromString(String param) {
                try ( Session s = HibernateUtil.getSessionFactory().openSession()) {
                    Query q = s.createSQLQuery("from Carta where nombre=:nombre");
                    q.setParameter("nombre", param);
                    return (Carta) q.list().get(0);
                }
            }
        });
    }

    private void actualizarTabla() {

        List<Pedido> pedidos1 = new ArrayList();
        pedidos1 = gestorPedidos.getTotalPedidosOrdenados();
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        pedidos.addAll(pedidos1);

        tabla.getItems().clear();
        tabla.getItems().addAll(pedidos);
    }

    private void actualizarTablaDiaHoy() {

        List<Pedido> pedidos1 = new ArrayList();
        pedidos1 = gestorPedidos.getPedidosHoy();
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        pedidos.addAll(pedidos1);

        tabla.getItems().clear();
        tabla.getItems().addAll(pedidos);
    }

    @FXML
    private void añadirPedido(ActionEvent event) {

        Carta producto = comboProducto.getValue();
        Boolean estado = false;
        if (checkbox.isSelected()) {
            estado = true;
        }

        pedidoActual.setFecha(date);
        pedidoActual.setCliente(txtCliente.getText());
        pedidoActual.setProducto(producto);
        pedidoActual.setEstado(estado);

        gestorPedidos.save(pedidoActual);

        actualizarTablaDiaHoy();

        info.setText("Pedido añadido a  " + pedidoActual.getCliente() + "  con éxito!");
        info.setStyle("-fx-background-color:#00FF00; -fx-text-fill:white; -fx-font-weight: bold");
    }

    @FXML
    private void actualizarPedido(ActionEvent event) {

        Carta producto = comboProducto.getValue();
        Boolean estado = false;
        if (checkbox.isSelected()) {
            estado = true;
        }

        pedidoActual.setFecha(date);
        pedidoActual.setCliente(txtCliente.getText());
        pedidoActual.setProducto(producto);
        pedidoActual.setEstado(estado);

        gestorPedidos.update(pedidoActual);

        actualizarTablaDiaHoy();

        info.setText("Pedido actualizado a " + pedidoActual.getCliente() + "  con éxito!");
        info.setStyle("-fx-background-color:#00FF00; -fx-text-fill:white; -fx-font-weight: bold");
    }

    @FXML
    private void borrarPedido(ActionEvent event) {

        Pedido pedido = tabla.getSelectionModel().getSelectedItem();

        gestorPedidos.delete(pedido);
        actualizarTablaDiaHoy();

        info.setText("Pedido borrado a " + pedidoActual.getCliente() + "  con éxito!");
        info.setStyle("-fx-background-color:#00FF00; -fx-text-fill:white; -fx-font-weight: bold");
    }

    @FXML
    private void mostrarPedido(MouseEvent event) {

        Pedido pedido = tabla.getSelectionModel().getSelectedItem();

        System.out.println(tabla.getSelectionModel().getSelectedIndex());

        if (pedido != null) {
            Carta prod = pedido.getProducto();
            if (pedido.getEstado()) {
                checkbox.setSelected(true);
            } else {
                checkbox.setSelected(false);
            }

            txtFecha.setText("" + pedido.getFecha().toString());
            txtCliente.setText(pedido.getCliente().toString());
            comboProducto.setValue(prod);

            pedidoActual = pedido;

        }
    }

    @FXML
    private void abrirPedidosHoy(ActionEvent event) {
        actualizarTabla();
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
    private void abrirPedidosDeHoy(ActionEvent event) {
        actualizarTablaDiaHoy();
    }

    @FXML
    private void abrirInforme(ActionEvent event) {
        if (dataPicker1.getValue() == null || dataPicker2.getValue() == null) {
            try {
                informe.showReport("pedidosHoy");
            } catch (JRException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
            informe.pdfReport("pedidosHoy");
            } catch (JRException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                informe.showReportBetweenDates(dataPicker1.getValue(), dataPicker2.getValue());
            } catch (JRException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
            informe.pdfReport("pedidos");
            } catch (JRException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

}
