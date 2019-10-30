package dados.daos;

import dados.entidades.Ator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

public class AtorDAO {
    
    /**
     * Salvar o ator no BD
     */
    public void salvar(Ator a){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(a);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    /**
     * Retorna uma lista com todos os atores 
     * que estejam cadastrados no banco de dados
     * @return 
     */
    public List<Ator> listar(){
        
      //Pegando o gerenciador de acesso ao BD
      EntityManager gerenciador = JPAUtil.getGerenciador(); 
      
      //Criando a consulta ao BD
      TypedQuery consulta = gerenciador.createQuery(
              "Select a from Ator a", Ator.class);
      
      //Retornar a lista de atores
      return consulta.getResultList();
        
    }
    
}
