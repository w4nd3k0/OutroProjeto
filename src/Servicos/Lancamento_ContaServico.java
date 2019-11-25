/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import Dados.Daos.Lancamento_ContaDAO;
import Dados.Entidades.Lancamento_Conta;
import java.util.List;

/**
 *
 * @author W4ND3K0
 */
public class Lancamento_ContaServico {
    
    //Atributo para representar a camada de dados
    private Lancamento_ContaDAO dao = new Lancamento_ContaDAO();
    
    public void salvar(Lancamento_Conta a){
        //Fazer qualquer regra de negócio
        
        //Mandar o Lancamento_Conta para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(a);
    }
    
    /**
     * Solicita a camada DAO para buscar os Lancamento_Conta
     * cadastrados
     * @return 
     */
    public List<Lancamento_Conta> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    /**
     * Recebe uma Lancamento_Conta e manda para a camada DAO atualizar 
     */
    public void editar(Lancamento_Conta a){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(a);
        
    }
    
    /**
     *  Recebe um Lancamento_Conta para passar para a DAO excluir no BD
     */
    public void excluir(Lancamento_Conta a){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(a);
    }
    
}
