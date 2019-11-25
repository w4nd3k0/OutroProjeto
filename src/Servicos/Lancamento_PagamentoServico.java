/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import Dados.Daos.Lancamento_PagamentoDAO;
import Dados.Entidades.Lancamento_Pagamento;
import java.util.List;

/**
 *
 * @author W4ND3K0
 */
public class Lancamento_PagamentoServico {
    
    //Atributo para representar a camada de dados
    private Lancamento_PagamentoDAO dao = new Lancamento_PagamentoDAO();
    
    public void salvar(Lancamento_Pagamento l){
        //Fazer qualquer regra de negócio
        
        //Mandar a Lancamento_Pagamento para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(l);
    }
    
    /**
     * Solicita a camada DAO para buscar as Lancamento_Pagamento
     * cadastrados
     * @return 
     */
    public List<Lancamento_Pagamento> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();   
    }
    
    /**
     * Recebe uma Lancamento_Pagamento e manda para a camada DAO atualizar 
     */
    public void editar(Lancamento_Pagamento l){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(l);
    }
    
    /**
     *  Recebe uma Conta para passar para a DAO excluir no BD
     */
    public void excluir(Lancamento_Pagamento l){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(l);
    }
}
