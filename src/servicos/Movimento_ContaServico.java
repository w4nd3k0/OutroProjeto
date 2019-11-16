/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.Movimento_ContaDAO;
import dados.entidades.Movimento_Conta;
import java.util.List;

/**
 *
 * @author W4ND3K0
 */
public class Movimento_ContaServico {
    
    //Atributo para representar a camada de dados
    private Movimento_ContaDAO dao = new Movimento_ContaDAO();
    
    /**
     * Solicita a camada DAO para buscar os Movimento_Conta
     * cadastrados
     * @return 
     */
    public List<Movimento_Conta> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    /**
     * Recebe um Movimento_Conta e manda para a camada DAO salvar
     * no BD
     */
    public void salvar(Movimento_Conta f){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Manda a camada DAO salvar no BD
        dao.salvar(f);
        
    }
    
    /**
     * Recebe um Movimento_Conta e manda para a camada DAO atualizar 
     */
    public void editar(Movimento_Conta f){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(f);
        
    }
    
    /**
     *  Recebe um Movimento_Conta para passar para a DAO excluir no BD
     */
    public void excluir(Movimento_Conta f){
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(f);
    }
    
}
