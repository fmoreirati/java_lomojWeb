package br.com.lojaWEB.DAO;

import br.com.lojaWEB.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProdutoDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LojaWEBPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = null;

    //Salvar produto
    public void create(Produto produto) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            em.persist(produto);
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

    //Alterar Produto
    public void edit(Produto produto) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            produto = em.merge(produto);
            et.commit();
        } catch (Exception ex) {
            try {
                et.rollback();
            } catch (Exception re) {

            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = produto.getId();
                if (findProduto(id) == null) {

                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Apagar produto
    public void destroy(Long id) throws Exception {
        et = em.getTransaction();
        try {
            et.begin();
            Produto produto = null;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getId();
            } catch (EntityNotFoundException enfe) {

            }
            em.remove(produto);
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
    public Produto findProduto(Long id) {
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    //Buscar todos por nomes
    public List<Produto> findProdutos(String dados) {
        try {
            Query query = em.createQuery("select p from Produto as p where p.descricao like :dados and p.ativo = 1");
            query.setParameter("dados", dados + "%");
            List<Produto> produtos = query.getResultList();
            return produtos;
        } finally {
            em.close();
        }
    }
    public List<Produto> findProdutosALL(String dados) {
        try {
            Query query = em.createQuery("select p from Produto as p where p.descricao like :dados");
            query.setParameter("dados", dados + "%");
            List<Produto> produtos = query.getResultList();
            return produtos;
        } finally {
            em.close();
        }
    }

    

}
