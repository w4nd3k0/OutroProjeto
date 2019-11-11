/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.FilmeDAO;
import dados.daos.GeneroDAO;
import dados.entidades.Filme;
import dados.entidades.Genero;
import java.util.List;

/**
 *
 * @author lusst
 */
public class GeneroServico {
    
    //Atributo para representar a camada de dados
    private GeneroDAO dao = new GeneroDAO();
    
    /**
     * Solicita a camada DAO para buscar os atores
     * cadastrados
     * @return 
     */
    public List<Genero> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
}
