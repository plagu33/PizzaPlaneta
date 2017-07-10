/*
 * Esta clase ha sido desarrollada con fines educativos
 *
 */
package cl.pizzaplaneta.dao;

import cl.pizzaplaneta.modelos.Modelo;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Michel M. <michell@linuxero.cl>
 * @param <T> Clase de Modelo
 */
public abstract class Dao<T extends Modelo> {

    final Class<T> model;
    protected Session currentSession;
    protected Transaction currentTransaction;

    public Dao(Class<T> model) {
        this.model = model;

    }

    protected Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session iniciarTransaccion() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    protected void closeCurrentSession() {
        currentSession.close();
    }

    public void commit() {
        currentTransaction.commit();
        currentSession.close();
    }

    public void rollback() {
        currentTransaction.rollback();
        currentSession.close();
    }

    protected static SessionFactory getSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            return sessionFactory;
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
        /*
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
         */
        return null;
    }

    protected Session getCurrentSession() {
        return currentSession;
    }

    protected void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    protected Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    protected void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    /**
     * Inserta un nuevo registro en la BD
     *
     * @param obj objeto del tipo entity a insertar
     */
    public void insert(T obj) {
        getCurrentSession().save(obj);
    }

    /**
     * Elimina un registro en base a su id
     *
     * @param id id del registro a eliminar
     */
    public void delete(String id) {
        Session session = getCurrentSession();
        Object load = session.load(model, id);
        session.delete(load);

    }

    /**
     * Actualiza un registro con los datos del objeto que se pasa como argumento
     *
     * @param obj Objeto a actualizar con los nuevos datos
     */
    public void update(T obj) {
        Session session = getCurrentSession();
        session.update(obj);
    }

    /**
     * Retorna todos los registros existentes
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() throws ClassNotFoundException {
        Session session = getCurrentSession();
        List<?> lstObjs;

        String clazz = model.getName();
        String query = "from " + clazz;
        Class.forName(clazz);
        lstObjs = session.createQuery(query).list();
        return (List<T>) lstObjs;
    }

    /**
     * Retorna un registro en base a su ID
     *
     * @param id id del registro
     * @return
     */
    public T getById(String id) {

        Session session = getCurrentSession();
        if (session == null) {

        }
        Object obj;
        obj = session.get(model, id);
        return (T) obj;

    }

    /**
     * Obtiene un objeto UNICO indicando los campos necesarios. Si existe mas de
     * un objeto retornará error.
     *
     * @param fields HashMap String,Object indicado que campo debe ser igual a
     * "que cosa"
     * @return
     */
    public T getUniqueByFields(HashMap<String, Object> fields) {
        String sql;

        Session session = getCurrentSession();
        String clazz = model.getSimpleName();
        sql = "from " + clazz + " where ";
        HashMap<String, Integer> orden = new HashMap<>();
        if (fields != null && !fields.isEmpty()) {
            int pos = 1;
            for (Map.Entry pair : fields.entrySet()) {
                Integer p = orden.put((String) pair.getKey(), pos);
                sql += " " + (String) pair.getKey() + " = :" + (String) pair.getKey() + " and";
                pos++;
            }
        }
        //System.out.println(sql);
        sql = sql.substring(0, sql.length() - 4);
        //System.out.println(sql);
        Query query = session.createQuery(sql);
        if (fields != null && !fields.isEmpty()) {
            for (Map.Entry pair : fields.entrySet()) {
                query.setParameter((String) pair.getKey(), pair.getValue());
            }
        }
        return (T) query.uniqueResult();

    }

    /**
     * Obtiene un listado de objetos indicando los campos necesarios.
     *
     * @param fields HashMap String,Object indicado que campo debe ser igual a
     * "que cosa"
     * @return
     */
    public List<T> getListByFields(HashMap<String, Object> fields) {
        String sql;

        Session session = getCurrentSession();
        String clazz = model.getName();
        sql = "from " + clazz + " where ";
        if (fields != null && !fields.isEmpty()) {
            for (Map.Entry pair : fields.entrySet()) {
                sql += " " + (String) pair.getKey() + " = :" + (String) pair.getKey() + " and";
            }
        }

        sql = sql.substring(0, sql.length() - 4);

        Query query = session.createQuery(sql);
        if (fields != null && !fields.isEmpty()) {
            for (Map.Entry pair : fields.entrySet()) {
                query.setParameter((String) pair.getKey(), pair.getValue());
            }
        }
        return query.list();
    }

    /**
     *
     * @param query
     * @param parameters
     * @param TRACE
     * @param resultClass
     * @return
     */
    public List<T> execute(String query, HashMap<String, String> parameters, String TRACE, Class<?> resultClass) {
        Session session = getCurrentSession();
        SQLQuery sql = session.createSQLQuery(query);
        if (resultClass != null) {
            sql.setResultTransformer(Transformers.aliasToBean(resultClass));
        }

        if (parameters != null && !parameters.isEmpty()) {
            Iterator it = parameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                sql.setParameter((String) pair.getKey(), pair.getValue());
                it.remove();
            }
        }
        return sql.list();
    }

    public int executeSaveOrUpdate(String query, List<Class<?>> classes, HashMap<String, String> parameters, String TRACE) {

        Session session = getCurrentSession();
        SQLQuery sql = session.createSQLQuery(query);
        if (classes.size() > 0) {
            for (Class<?> clazz : classes) {
                sql.addEntity(clazz.getClass());
            }
        }
        if (parameters != null && !parameters.isEmpty()) {
            Iterator it = parameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                sql.setParameter((String) pair.getKey(), pair.getValue());
                it.remove();
            }
        }
        return sql.executeUpdate();

    }

    /**
     * Genera la sentencia SQL para el order by. Recibe una clase Model y un
     * string de entrada de API retornando "Tabla.Campo asc|desc". Si por
     * ejemplo se entrega el model Usuarios, y el argumento order es
     * "+nombreCompleto" se obtiene como resultado: "usuarios.nombre_completo
     * asc" (utilizando nombres de las tablas)
     *
     * @param modelObject
     * @param order
     * @return
     * @throws NoSuchFieldException
     */
    public String buildSqlOrderBy(Object modelObject, String... order) throws NoSuchFieldException {
        Modelo modelLocal = (Modelo) modelObject;
        StringBuilder sb = new StringBuilder();
        for (String o : order) {
            sb.append(modelLocal.getTableName())
                    .append(".")
                    .append(modelLocal.getColumnName(o.substring(1)))
                    .append(" ")
                    .append(o.charAt(0) == '+' ? "asc" : "desc");
            sb.append(", ");
        }
        if (sb.toString().length() > 2) {
            return sb.toString().substring(0, sb.toString().length() - 3);
        } else {
            return "";
        }
    }

    /**
     * Transforma un String SQL de una query normal a la misma query efectuando
     * un count(1). Elimina toda la seleccion de campos del select, cambiandola
     * por un count(1) y elimina la clausula order by, solo de la query externa.
     * Mantiene intactas las sentencias having, group by y limit ademas de no
     * interferir de ninguna forma en las subquerys.
     *
     * @param query
     * @return
     */
    public String getSqlCounterFromQuery(String query) {
        query = new StringBuilder(query).replace(0, query.indexOf(" from "), "select count(1)").toString();
        String limit = "";
        String groupby = "";
        String having = "";
        if (query.contains(" limit ")) {
            int start = query.lastIndexOf(" limit ");
            int end = query.length();
            limit = query.substring(query.lastIndexOf(" limit "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        if (query.contains(" having ")) {
            int start = query.lastIndexOf(" having ");
            int end = query.length();
            having = query.substring(query.lastIndexOf(" having "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        if (query.contains(" group by ")) {
            int start = query.lastIndexOf(" group by ");
            int end = query.length();
            groupby = query.substring(query.lastIndexOf(" group by "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        if (query.contains(" order by ")) {
            int start = query.lastIndexOf(" order by ");
            int end = query.length();
            String orderby = query.substring(query.lastIndexOf(" order by "), query.length());
            query = new StringBuilder(query).replace(start, end, "").toString();
        }
        return query + groupby + having + limit;
    }

    public long getCounterFromQuery(String query) {
        Session session = getCurrentSession();
        String counterQuery = getSqlCounterFromQuery(query);
        SQLQuery sql = session.createSQLQuery(counterQuery);
        return ((BigInteger) sql.uniqueResult()).longValue();

    }

}
