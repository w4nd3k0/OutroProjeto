/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.Forma_PagamentoDAO;
import dados.entidades.Conta;
import dados.entidades.Forma_Pagamento;
import java.util.List;

/**
 *
 * @author W4ND3K0
 */
public class Forma_PagamentoServico {
    
    //Atributo para representar a camada de dados
    private Forma_PagamentoDAO dao = new Forma_PagamentoDAO();
    
    public void salvar(Forma_Pagamento f){
        //Fazer qualquer regra de negócio
        
        //Mandar a Forma_Pagamento para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(f);
    }
    
    /**
     * Solicita a camada DAO para buscar as Contas
     * cadastrados
     * @return 
     */
    public List<Forma_Pagamento> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();   
    }
    
    /**
     * Recebe uma Forma_Pagamento e manda para a camada DAO atualizar 
     */
    public void editar(Forma_Pagamento f){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(f);
    }
    
    /**
     *  Recebe uma Conta para passar para a DAO excluir no BD
     */
    public void excluir(Forma_Pagamento f){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(f);
    }
    
}
