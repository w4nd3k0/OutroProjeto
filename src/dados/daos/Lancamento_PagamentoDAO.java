/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Lancamento_Pagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author W4ND3K0
 */
public class Lancamento_PagamentoDAO {
    
    /**
     * Salvar a Lancamento_Pagamento no BD
     */
    public void salvar(Lancamento_Pagamento l) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o Lancamento_Pagamento
        gerenciador.persist(l);

        //Commit
        gerenciador.getTransaction().commit();

    }

    /**
     * Retorna uma lista com todos as Lancamento_Pagamento que estejam cadastrados no banco de
     * dados
     *
     * @return
     */
    public List<Lancamento_Pagamento> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select l from Lancamento_Pagamento l", Lancamento_Pagamento.class);

        //Retornar a lista de Lancamento_Pagamento
        return consulta.getResultList();
    }

    /**
     * Salva as alterações no BD
     */
    public void editar(Lancamento_Pagamento l) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(l);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
    }
    
    /**
     * Exclui a Lancamento_Pagamento do BD
     */
    public void excluir(Lancamento_Pagamento l){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar a Lancamento_Pagamento do BD com a Lancamento_Pagamento que foi
        //selecionado na tela
        l = gerenciador.merge(l);

        //Mandar sincronizar as alterações 
        gerenciador.remove(l);
        
        //Commit na transação
        gerenciador.getTransaction().commit();   
    }
}
