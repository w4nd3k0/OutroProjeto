/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Lancamento_Conta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author W4ND3K0
 */
public class Lancamento_ContaDAO {
    
     /**
     * Salvar o Lancamento_Conta no BD
     */
    public void salvar(Lancamento_Conta a) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o ator
        gerenciador.persist(a);

        //Commit
        gerenciador.getTransaction().commit();

    }

    /**
     * Retorna uma lista com todos os Lancamento_Conta que estejam cadastrados no banco de
     * dados
     *
     * @return
     */
    public List<Lancamento_Conta> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select a from Lancamento_Conta a", Lancamento_Conta.class);

        //Retornar a lista de Lancamento_Conta
        return consulta.getResultList();

    }

    /**
     * Salva as alterações no BD
     */
    public void editar(Lancamento_Conta a) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(a);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
    
    /**
     * Exclui o Lancamento_Conta do BD
     */
    public void excluir(Lancamento_Conta a){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o Lancamento_Conta do BD com o Lancamento_Conta que foi
        //selecionado na tela
        a = gerenciador.merge(a);

        //Mandar sincronizar as alterações 
        gerenciador.remove(a);
        
        //Commit na transação
        gerenciador.getTransaction().commit();     
    } 
}
