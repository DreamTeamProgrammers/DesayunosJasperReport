
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Antonio y Loren
 */
@Entity
@AllArgsConstructor
public class Carta implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Integer idproducto;
    private String nombre;
    private String tipo;
    private Double precio;
    private String disponibilidad;
    
    /*@OneToMany
    @JoinColumn(name="producto")
    private List<Pedido> pedidos;*/


    public Carta(String nombre, String tipo, Double precio, String disponibilidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public Carta() {
    }
    
    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return nombre + ", tipo=" + tipo + ", precio=" + precio + ", disponibilidad=" + disponibilidad + '}';
    }

}

    