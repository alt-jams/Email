
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jamilly
 */
public class Funcao {
    
    public void cadastrar(Usuario user){        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
    
    public void cadastrar(Mensagem msg){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(msg);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
    
    public Usuario getUserByEmailSenha(String email, String senha){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Usuario> query = 
                em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class);
        
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        
        Usuario usuario = null;
         
        try{
           usuario = query.getSingleResult(); 
        }catch(NoResultException e){  
        }
    return usuario;
    
    }

    public Usuario getUserByEmail(String email){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<Usuario> query = 
                em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
        
        query.setParameter("email", email);
        
        Usuario usuario = null;
   
        try{
           usuario = query.getSingleResult(); 
        }catch(NoResultException e){  
        }
    return usuario;
    }
    
    public Mensagem encontraMensagem(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager();
        
        Mensagem m = em.find(Mensagem.class, id);      
                
        em.close();
        emf.close();
        
        return m;
    }
    
    public void apaga(Mensagem m){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
                
        em.close();
        emf.close();
    }
    
    
    
}
