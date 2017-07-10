package cl.pizzaplaneta.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Michel M. <michell@linuxero.cl>
 */
@Entity
@Table(name = "ventas")
public class Venta extends Modelo implements Serializable{

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "id_interno_clte")
    private String id_interno_clte;
    
    //@Column(name="id_producto") 
    //private String id_producto;
    
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_venta;
    
    
    
    
    @Column(name="id_venta")
    private String id_venta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_interno_clte() {
        return id_interno_clte;
    }

    public void setId_interno_clte(String id_interno_clte) {
        this.id_interno_clte = id_interno_clte;
    }

  

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    

    public String getId_venta() {
        return id_venta;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }
    
    
   
}