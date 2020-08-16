
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jamilly
 */
public class CBUsuarioModel extends DefaultComboBoxModel<Usuario>{
    private List<Usuario> usuarios;
    public CBUsuarioModel() {
        super();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager();
        
        usuarios = em.createQuery("FROM Usuario").getResultList();
        
        for(Usuario usuario : usuarios){
            addElement(usuario);
        }
    }
    
    @Override
    public Usuario getSelectedItem(){
        return (Usuario) super.getSelectedItem();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
