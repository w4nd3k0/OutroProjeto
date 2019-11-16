/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Movimento_Conta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author W4ND3K0
 */
public class Movimento_ContaDAO {
    
    /**
    * Retorna uma lista com todos os Movimento_Conta que estejam cadastrados no banco de
    * dados
    *
    * @return
    */
    public List<Movimento_Conta> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select f from Movimento_Conta f", Movimento_Conta.class);

        //Retornar a lista de Movimento_Conta
        return consulta.getResultList();
    }

    /**
     * Manda salvar um Movimento_Conta no BD
     *  
     */
    public void salvar(Movimento_Conta f) {
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o Movimento_Conta
        gerenciador.persist(f);

        //Commit
        gerenciador.getTransaction().commit();
    }
    
    /**
     * Salva as alterações no BD
     */
    public void editar(Movimento_Conta f) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
    
    /**
     * Exclui o Movimento_Conta do BD
     */
    public void excluir(Movimento_Conta f){
        
         //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o Movimento_Conta que foi
        //selecionado na tela
        f = gerenciador.merge(f);

        //Mandar sincronizar as alterações 
        gerenciador.remove(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
}
