package servicos;

import dados.daos.AtorDAO;
import dados.entidades.Ator;
import java.util.List;

public class AtorServico {
    
    //Atributo para representar a camada de dados
    private AtorDAO dao = new AtorDAO();
    
    public void salvar(Ator a){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(a);
    }
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<Ator> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
}
