/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.RecebimentoDAO;
import dados.entidades.Recebimento;
import java.util.List;

/**
 *
 * @author W4ND3K0
 */
public class RecebimentoServico {
    
    //Atributo para representar a camada de dados
    private RecebimentoDAO dao = new RecebimentoDAO();
    
    public void salvar(Recebimento r){
        //Fazer qualquer regra de negócio
        
        //Mandar o Recebimento para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(r);
    }
    
    /**
     * Solicita a camada DAO para buscar os Recebimentos
     * cadastrados
     * @return 
     */
    public List<Recebimento> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    /**
     * Recebe um Contato e manda para a camada DAO atualizar 
     */
    public void editar(Recebimento r){
        
        //Qualquer regra de negócio (se aplicável)
        
        
        //Mandar a DAO atualizar os dados no BD
        dao.editar(r);
        
    }
    
    /**
     *  Recebe um Recebimento para passar para a DAO excluir no BD
     */
    public void excluir(Recebimento r){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Mandar para a DAO excluir
        dao.excluir(r);
    }
    
}
