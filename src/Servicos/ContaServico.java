/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import Dados.Daos.ContaDAO;
import Dados.Entidades.Conta;
import java.util.List;

/**
 *
 * @author W4ND3K0
 */
public class ContaServico {
    
    //Atributo para representar a camada de dados
    private ContaDAO dao = new ContaDAO();
    
    public void salvar(Conta a){
        //Fazer qualquer regra de negócio
        
        //Mandar a Conta para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(a);
    }
    
    /**
     * Solicita a camada DAO para buscar as Contas
     * cadastrados
     * @return 
     */
    public List<Conta> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();   
    }
    
    /**
     * Recebe uma Conta e manda para a camada DAO atualizar 
     */
    public void editar(Conta a){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(a);
    }
    
    /**
     *  Recebe uma Conta para passar para a DAO excluir no BD
     */
    public void excluir(Conta a){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(a);
    }
}
