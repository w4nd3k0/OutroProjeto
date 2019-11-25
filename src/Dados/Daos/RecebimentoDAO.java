/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados.Daos;

import Dados.Entidades.Recebimento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Util.JPAUtil;

/**
 *
 * @author W4ND3K0
 */
public class RecebimentoDAO {
    
    /**
     * Salvar o Recebimento no BD
     */
    public void salvar(Recebimento r) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o Recebimento
        gerenciador.persist(r);

        //Commit
        gerenciador.getTransaction().commit();

    }

    /**
     * Retorna uma lista com todos os Recebimentos que estejam cadastrados no banco de
     * dados
     *
     * @return
     */
    public List<Recebimento> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select r from Recebimento r", Recebimento.class);

        //Retornar a lista de Contas
        return consulta.getResultList();

    }

    /**
     * Salva as alterações no BD
     */
    public void editar(Recebimento r) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(r);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    /**
     * Exclui o Recebimento do BD
     */
    public void excluir(Recebimento r){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o Recebimento do BD com a conta que foi
        //selecionado na tela
        r = gerenciador.merge(r);

        //Mandar sincronizar as alterações 
        gerenciador.remove(r);
        
        //Commit na transação
        gerenciador.getTransaction().commit();   
    }
}
