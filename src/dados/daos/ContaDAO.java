/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Conta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author W4ND3K0
 */
public class ContaDAO {
    
    /**
     * Salvar a Conta no BD
     */
    public void salvar(Conta a) {

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
     * Retorna uma lista com todos as Conta que estejam cadastrados no banco de
     * dados
     *
     * @return
     */
    public List<Conta> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select a from Conta a", Conta.class);

        //Retornar a lista de Contas
        return consulta.getResultList();

    }

    /**
     * Salva as alterações no BD
     */
    public void editar(Conta a) {

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
     * Exclui a Conta do BD
     */
    public void excluir(Conta a){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar a conta do BD com a conta que foi
        //selecionado na tela
        a = gerenciador.merge(a);

        //Mandar sincronizar as alterações 
        gerenciador.remove(a);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }    
}
