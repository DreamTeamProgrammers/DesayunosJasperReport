package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Antonio y Loren
 */
@Entity
@AllArgsConstructor
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Integer idpedido;
    private LocalDate fecha;
    private String cliente;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name="producto")
    private Carta producto = new Carta();
    
    public Pedido() {
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Pedido(String cliente, Carta producto) {
        this.fecha = LocalDate.now();
        this.cliente = cliente;
        this.producto = producto;
    } 

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Carta getProducto() {
        return producto;
    }

    public void setProducto(Carta producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idpedido=" + idpedido + ", fecha=" + fecha + ", cliente=" + cliente + ", producto=" + producto + '}';
    }

    
}

