/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.ContatoDAO;
import dados.entidades.Contato;
import java.util.List;

/**
 *
 * @author W4ND3K0
 */
public class ContatoServico {
    
    //Atributo para representar a camada de dados
    private ContatoDAO dao = new ContatoDAO();
    
    public void salvar(Contato c){
        //Fazer qualquer regra de negócio
        
        //Mandar o Contato para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(c);
    }
    
    /**
     * Solicita a camada DAO para buscar os Contatos
     * cadastrados
     * @return 
     */
    public List<Contato> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    /**
     * Recebe um Contato e manda para a camada DAO atualizar 
     */
    public void editar(Contato c){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(c);
        
    }
    
    /**
     *  Recebe um ator para passar para a DAO excluir no BD
     */
    public void excluir(Contato c){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(c);
    } 
}
