/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados.Daos;

import Dados.Entidades.Contato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Util.JPAUtil;

/**
 *
 * @author W4ND3K0
 */
public class ContatoDAO {
    
    /**
    * Retorna uma lista com todos os Contatos que estejam cadastrados no banco de
    * dados
    *
    * @return
    */
    public List<Contato> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select c from Contato c", Contato.class);

        //Retornar a lista de Movimento_Conta
        return consulta.getResultList();
    }

    /**
     * Manda salvar um Contato no BD
     *  
     */
    public void salvar(Contato c) {
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o Contato
        gerenciador.persist(c);

        //Commit
        gerenciador.getTransaction().commit();
    }
    
    /**
     * Salva as alterações no BD
     */
    public void editar(Contato c) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
    
    /**
     * Exclui o Contato do BD
     */
    public void excluir(Contato c){
        
         //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o ator do BD com o Contato que foi
        //selecionado na tela
        c = gerenciador.merge(c);

        //Mandar sincronizar as alterações 
        gerenciador.remove(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }   
}
