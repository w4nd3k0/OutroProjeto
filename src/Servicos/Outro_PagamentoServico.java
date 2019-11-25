/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import Dados.Daos.Outro_PagamentoDAO;
import Dados.Entidades.Outro_Pagamento;
import java.util.List;

/**
 *
 * @author IFNMG
 */
public class Outro_PagamentoServico {
    
    //Atributo para representar a camada de dados
    private Outro_PagamentoDAO dao = new Outro_PagamentoDAO();
    
    public void salvar(Outro_Pagamento a){
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
    public List<Outro_Pagamento> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();   
    }
    
    /**
     * Recebe uma Conta e manda para a camada DAO atualizar 
     */
    public void editar(Outro_Pagamento a){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(a);
    }
    
    /**
     *  Recebe uma Conta para passar para a DAO excluir no BD
     */
    public void excluir(Outro_Pagamento a){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(a);
    }
    
}
