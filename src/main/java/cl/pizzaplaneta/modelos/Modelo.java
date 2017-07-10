/*
* Esta clase ha sido desarrollada con fines educativos
 */
package cl.pizzaplaneta.modelos;

import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 *
 * @author Michel M. <michell@linuxero.cl>
 */
public abstract class Modelo {

    public String getTableName() {
        Table table = this.getClass().getAnnotation(Table.class);
        return (table == null) ? "" : table.name();
    }

    public String getColumnName(String property) {
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (f.getName().equals(property)) {
                    f.setAccessible(true);
                    Column a = f.getAnnotation(Column.class);
                    return a.name();
                }

            }
            throw new NoSuchFieldException("No existe el campo " + property);
        } catch (NoSuchFieldException | SecurityException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
