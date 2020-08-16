
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jamilly
 */
public class MensagemTableModel extends AbstractTableModel{
    protected List<Mensagem> mensagens;
    private int op;
    private String[] colunas = {"Remetente", "título"}; 

    
    public MensagemTableModel() {
        this.mensagens =  new ArrayList<>();
        
    }
    
    @Override
    public int getRowCount() {
       return mensagens.size();
    }

    @Override
    public int getColumnCount() {
       return colunas.length; 
    }
    
    public String getColumnName(int i){
        return colunas[i];
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (coluna == 0){ 
            return mensagens.get(linha).getRemetente().getEmail();
        }
        else if (coluna == 1){
            return mensagens.get(linha).getTitulo();
        }
       return null;  
    }
    
    public void carregarDadosEntrada(Usuario user){
        EntityManagerFactory emf =  
                Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager(); 
        
        TypedQuery<Mensagem> query = 
                em.createQuery("SELECT m FROM Mensagem m WHERE m.destinatario = :destinatario AND m.viewDestinatario = :viewDestinatario", Mensagem.class);
        query.setParameter("destinatario", user);
        query.setParameter("viewDestinatario", 1);
        
        try{
           mensagens = query.getResultList();
        }catch(NoResultException e){  
        }    
        
        em.close();
        emf.close();
        
        fireTableDataChanged();
    }
    
    public void carregarLixeira(Usuario user){
        EntityManagerFactory emf =  
                Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager(); 
        
        TypedQuery<Mensagem> query = 
                em.createQuery("SELECT m FROM Mensagem m WHERE m.destinatario = :destinatario AND m.viewDestinatario = :viewDestinatario", Mensagem.class);
        query.setParameter("destinatario", user);
        query.setParameter("viewDestinatario", 0);
        
        try{
           mensagens = query.getResultList();
        }catch(NoResultException e){  
        }    
        
        em.close();
        emf.close();
        
        fireTableDataChanged();
    }
    
    public void carregarDadosSaida(Usuario user){
       EntityManagerFactory emf =  
                Persistence.createEntityManagerFactory("MensagemPU");
        EntityManager em = emf.createEntityManager(); 
        
        TypedQuery<Mensagem> query = 
                em.createQuery("SELECT m FROM Mensagem m WHERE m.remetente = :remetente AND m.viewRemetente = :viewRemetente", Mensagem.class);
        query.setParameter("remetente", user);
        query.setParameter("viewRemetente", 1);
        
        try{
           mensagens = query.getResultList();
        }catch(NoResultException e){  
        }    
        
        em.close();
        emf.close();
        
        fireTableDataChanged(); 
    } 
    
    public Mensagem getMensagem(int i){
       return mensagens.get(i); 
    }  
}
