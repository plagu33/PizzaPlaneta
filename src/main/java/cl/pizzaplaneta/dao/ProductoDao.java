package cl.pizzaplaneta.dao;

import cl.pizzaplaneta.modelos.Producto;
import org.hibernate.Session;

/**
 *
 * @author Michel M. <michell@linuxero.cl>
 */
public class ProductoDao extends Dao<Producto> {
    
    public ProductoDao() {
        super(Producto.class);
    }
    
    @Override
    public Producto getById(String id) {

        Session session = getCurrentSession();
        if (session == null) {

        }
        
        return session.get(Producto.class, id);
        

    }  
    
}
