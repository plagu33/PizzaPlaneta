
package cl.pizzaplaneta.ws;

import cl.pizzaplaneta.dao.*;
import cl.pizzaplaneta.modelos.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "Pizzeria")
public class Pizzeria {

    /**
     * This is a sample web service operation
     * @param resultado
     * @return 
     */
    @WebMethod(operationName = "venta")
    public String crearVenta(            
            @WebParam(name = "id_venta") String id_venta,
            @WebParam(name = "id_interno_clte") String id_interno_clte            
    ) {
       
        VentaDao vDao = new VentaDao();        
        Venta venta   = new Venta();
                
        venta.setId(id_venta);
        venta.setId_interno_clte(id_interno_clte);
        venta.setFecha_venta(new Date());
                
        vDao.iniciarTransaccion(); //inicia la transaccion en la base de datos
        try{
            
            vDao.insert(venta); //intento insertar el campo en la BD
            vDao.commit(); //si no da error, hago commit
            
        }catch(Exception e){
            e.printStackTrace();
            vDao.rollback(); //si hay algun error, hago rollback y no se aplica ningun cambio a la BD
        }
        
        return "ok";
    }
    
    @WebMethod(operationName = "crearProducto")
    public String crearProducto(
            @WebParam(name = "producto") String producto_nombre,
            @WebParam(name = "codigo") int producto_codigo,
            @WebParam(name = "descripcion") String producto_descripcion,
            @WebParam(name = "precio") int producto_precio
    ) {
       
        ProductoDao pDao=new ProductoDao();
        
        Producto producto=new Producto();
        
        producto.setId(UUID.randomUUID().toString());
        producto.setProducto(producto_nombre);
        producto.setCodigo(producto_codigo);
        producto.setDescripcion(producto_descripcion);
        producto.setPrecio(producto_precio);
        producto.setFecha(new Date());
        
        pDao.iniciarTransaccion(); //inicia la transaccion en la base de datos
        try{
            
            pDao.insert(producto); //intento insertar el campo en la BD
            pDao.commit(); //si no da error, hago commit
            
        }catch(Exception e){
            e.printStackTrace();
            pDao.rollback(); //si hay algun error, hago rollback y no se aplica ningun cambio a la BD
        }
        
        return "Producto creado exitosamente!!";
    }    
    
    
    
    
    
     @WebMethod(operationName = "buscarProductoPorID")
    public Producto buscarProductoId(
            
            @WebParam(name = "codigo") int producto_codigo
           
    ) {
        
        HashMap<String, Object> f = new HashMap();
        
        
        f.put("codigo_producto",producto_codigo);
        ProductoDao pDao = new ProductoDao();
        pDao.iniciarTransaccion();
        Producto prod = pDao.getUniqueByFields(f);
        pDao.commit();
        return prod;
    }
        
      @WebMethod(operationName = "buscarProductoNombre")
    public Producto buscarProductoNombre(
            
            @WebParam(name = "producto") String producto
            
    ) {
        
        HashMap<String, Object> f = new HashMap();
        
        
        f.put("producto",producto);
        ProductoDao pDao = new ProductoDao();
        pDao.iniciarTransaccion();
        Producto prod = pDao.getUniqueByFields(f);
        pDao.commit();
        return prod;
    }
        

            
        
        
    
    @WebMethod(operationName = "crearCliente")
    public String crearCLiente (
            //@WebParam(name = "id_cliente") String id_cliente,
            @WebParam(name = "nombre_cliente") String nombre_cliente,
            @WebParam(name = "rut") String rut,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "email") String email
            //,@WebParam(name = "id_interno_clte") String id_interno_clte            
    ) {
       
        ClienteDao cDao=new ClienteDao();        
        Cliente cliente=new Cliente();
        
        String id = UUID.randomUUID().toString();
        
        cliente.setId_cliente(id);
        cliente.setNombre_cliente(nombre_cliente);
        cliente.setRut(rut);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setFecha(new Date());
        //cliente.setId_interno_clte(id_interno_clte);
        
        cDao.iniciarTransaccion(); //inicia la transaccion en la base de datos
        try{
            
            cDao.insert(cliente); //intento insertar el campo en la BD
            cDao.commit(); //si no da error, hago commit
            
        }catch(Exception e){
            e.printStackTrace();
            cDao.rollback(); //si hay algun error, hago rollback y no se aplica ningun cambio a la BD
        }
        
        return id;
    }


  @WebMethod(operationName = "buscarCLientePorNombre")
    public Cliente buscarClienteNombre(
            
            @WebParam(name = "nombre_cliente") String nombre_cliente
            
    ) {
        
        HashMap<String, Object> f = new HashMap();
        
        
       
        f.put("nombre_cliente", nombre_cliente);
        ClienteDao cDao = new ClienteDao();
        cDao.iniciarTransaccion();
        Cliente cliente = cDao.getUniqueByFields(f);
        cDao.commit();
        return cliente;
    }
    
    
    
    @WebMethod(operationName = "buscarCLientePorID")
    public Cliente buscarClienteID(
           
           @WebParam(name = "id_interno_clte") String id_interno_clte
            
    ) {
        
        HashMap<String, Object> f = new HashMap();
        //Producto producto = new Producto();
        //producto.setCodigo(1);
        
        f.put("id_interno_clte",id_interno_clte);
        //f.put("nombre_cliente", nombre_cliente);
        ClienteDao cDao = new ClienteDao();
        cDao.iniciarTransaccion();
        Cliente cliente = cDao.getUniqueByFields(f);
        cDao.commit();
        return cliente;
    }
    
    @WebMethod(operationName = "buscarCLienteTelefono")
    public Cliente buscarClienteTelefono(
        @WebParam(name = "telefono") String telefono
    ) {
        
        HashMap<String, Object> f = new HashMap();
        
        f.put("telefono",telefono);
        ClienteDao cDao = new ClienteDao();
        cDao.iniciarTransaccion();
        Cliente cliente = cDao.getUniqueByFields(f);
        cDao.commit();
        return cliente;
    }
    
    
     @WebMethod(operationName = "detalleVenta")
    public String detalleVenta(
            //@WebParam(name = "producto") String producto_nombre,
           @WebParam(name = "id_venta") String id_venta,
            @WebParam(name = "codigo_producto") String codigo_producto,
            @WebParam(name = "cantidad") int cantidad
            //@WebParam(name = "precio") int producto_precio
    ) {

        VentaDetalle ventadet = new VentaDetalle();
        ventadet.setId_detalle_venta(UUID.randomUUID().toString());
        ventadet.setId_venta(id_venta);
        ventadet.setCodigo_producto(codigo_producto);
        ventadet.setCantidad(cantidad);
        
        VentaDetalleDao vtDao = new VentaDetalleDao();
        vtDao.iniciarTransaccion();

        try{            
            vtDao.insert(ventadet); //intento insertar el campo en la BD
            vtDao.commit(); //si no da error, hago commit                                     
        }catch(Exception e){
            e.printStackTrace();
            vtDao.rollback(); //si hay algun error, hago rollback y no se aplica ningun cambio a la BD            
        }                
        
        return codigo_producto;  
        
    }

    @WebMethod(operationName = "listarProductos")
    public List<Producto> listarProductos() throws ClassNotFoundException {
        
        ProductoDao pDao = new ProductoDao();
        pDao.iniciarTransaccion();
        List<Producto> productos = pDao.getAll();
        pDao.commit();
        return productos;
        
    }

}