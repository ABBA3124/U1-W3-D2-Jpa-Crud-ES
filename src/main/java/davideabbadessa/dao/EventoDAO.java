package davideabbadessa.dao;

import davideabbadessa.entities.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class EventoDAO {
    private EntityManagerFactory emf;

    public EventoDAO() {
        this.emf = Persistence.createEntityManagerFactory("U1-W3-D1-jpa-crud");
    }

    public void save(Evento evento) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Evento getById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Evento.class, id);
        } finally {
            em.close();
        }
    }

    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Evento evento = em.find(Evento.class, id);
            if (evento != null) {
                em.remove(evento);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Evento> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Evento", Evento.class).getResultList();
        } finally {
            em.close();
        }
    }

}
