/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author jose
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Modelo implements Serializable{

    @Id
    @Column(name = "id_cliente")
    private String id_cliente;

    @Column(name="nombre_cliente") 
    private String nombre_cliente;
    
    @Column(name="rut")
    private String rut;
    
    @Column(name="telefono")
    private String telefono;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "id_interno_clte")
    private String id_interno_clte;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getId_interno_clte(){
        return id_interno_clte;
    }
    
    public void setId_interno_clte(String id_interno_clte){
            this.id_interno_clte=id_interno_clte;
    }


}

   
    
    
    
   
    
    
    

