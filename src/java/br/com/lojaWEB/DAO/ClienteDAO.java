package br.com.lojaWEB.DAO;

import br.com.lojaWEB.model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ClienteDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LojaWEBPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = null;

    //Salvar cliente
    public void create(Cliente cliente) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            em.persist(cliente);
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

    //Alterar Cliente
    public void edit(Cliente cliente) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            cliente = em.merge(cliente);
            et.commit();
        } catch (Exception ex) {
            try {
                et.rollback();
            } catch (Exception re) {

            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getId();
                if (findCliente(id) == null) {

                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Apagar cliente
    public void destroy(Long id) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            Cliente cliente = null;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId();
            } catch (EntityNotFoundException enfe) {

            }
            em.remove(cliente);
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
    public Cliente findCliente(Long id) {
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    //Buscar todos por nomes
    public List<Cliente> findClientes(String dados) {
        try {
            Query query = em.createQuery("select c from Cliente as c where c.nome like :dados");
            query.setParameter("dados", dados + "%");
            List<Cliente> clientes = query.getResultList();
            return clientes;
        } finally {
            em.close();
        }
    }

    //Busca cliente pela email e senha
    public Cliente findCliente(String email, String pws) {
        try {
            Query query = em.createQuery(""
                    + "select c from Cliente as c "
                    + "where c.email = :email and c.pws = :pws");
            query.setParameter("email", email);
            query.setParameter("pws", pws);
            return (Cliente) query.getSingleResult();
        } finally {
            em.close();
        }
    }

}
