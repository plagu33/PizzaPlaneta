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
 * @author manu
 */
@Entity
@Table(name = "productos")
public class Producto extends Modelo implements Serializable {
    
    @Id
    @Column(name = "id_producto")
    private String id;

    @Column(name="producto")
    private String producto;
            
    @Column(name="codigo_producto")
    private int codigo;            
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="precio")
    private int precio;
    
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;    

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setProducto(String producto) {
        this.producto = producto;
    }    
    
    public String getProducto() {
        return producto;
    }        
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }    
    
    public int getCodigo() {
        return codigo;
    }           
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
    
    public String getDescripcion() {
        return descripcion;
    }        
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }    
    
    public int getPrecio() {
        return precio;
    }        
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }    
    
    public Date getFecha() {
        return fecha;
    }

}