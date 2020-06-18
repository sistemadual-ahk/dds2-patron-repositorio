package repositories.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DAOHibernate<T> implements IDAO<T> {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    private EntityManager entityManager(){
        return emf.createEntityManager();
    }

    private void ejecutar(T object, String metodo){
        try{
            EntityManager em = entityManager();
            em.getTransaction().begin();
            em.getClass().getMethod(metodo).invoke(object);
            em.getTransaction().commit();
            em.close();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public boolean exist(int id) {
        return false;
    }

    public T find(int id, Class<T> clase) {
        EntityManager em = entityManager();
        return (T) em.find(clase, id);
    }

    public List<T> findAll(Class<T> clase) {
        EntityManager em = entityManager();
         Query query = em.createQuery("from " + clase.getName());
        return (List<T>) query.getResultList();
    }

    public void delete(T object) {
        ejecutar(object, "remove");
    }

    public void update(T object) {
        ejecutar(object, "merge");
    }

    public void insert(T object) {
        ejecutar(object, "persist");
    }
}