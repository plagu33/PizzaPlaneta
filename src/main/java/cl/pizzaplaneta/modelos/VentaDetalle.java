package cl.pizzaplaneta.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "detalle_venta")
public class VentaDetalle extends Modelo implements Serializable{

    @Id
    @Column(name = "id_detalle_venta")
    private String id_detalle_venta;

    @Column(name = "id_venta")
    private String id_venta;
    
    @Column(name="codigo_producto") 
    private String codigo_producto;
    
    @Column(name="cantidad") 
    private int cantidad;

    public String getId_detalle_venta() {
        return id_detalle_venta;
    }

    public void setId_detalle_venta(String id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }

    public String getId_venta() {
        return id_venta;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}