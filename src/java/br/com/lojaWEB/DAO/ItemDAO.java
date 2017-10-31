package br.com.lojaWEB.DAO;

import br.com.lojaWEB.model.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ItemDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LojaWEBPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = null;

    //Salvar item
    public void create(Item item) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            em.persist(item);
            et.commit();
        } catch (Exception ex) {
            try {
                et.rollback();
            } catch (Exception re) {
                re.toString();
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Alterar Item
    public void edit(Item item) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            item = em.merge(item);
            et.commit();
        } catch (Exception ex) {
            try {
                et.rollback();
            } catch (Exception re) {

            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = item.getId();
                if (findItem(id) == null) {

                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Apagar item
    public void destroy(Long id) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            Item item = null;
            try {
                item = em.getReference(Item.class, id);
                item.getId();
            } catch (EntityNotFoundException enfe) {

            }
            em.remove(item);
            et.commit();
        } catch (Exception ex) {
            try {
                et.rollback();
            } catch (Exception re) {

            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Busca cliete pelo id
    public Item findItem(Long id) {
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    //Buscar todos por nomes
    public List<Item> findItems() {
        try {
            Query query = em.createQuery("select i from Item as i");
            //query.setParameter("dados", dados + "%");
            List<Item> items = query.getResultList();
            return items;
        } finally {
            em.close();
        }
    } 

}
