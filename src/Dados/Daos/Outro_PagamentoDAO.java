/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados.Daos;

import Dados.Entidades.Outro_Pagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Util.JPAUtil;

/**
 *
 * @author IFNMG
 */
public class Outro_PagamentoDAO {
    
    /**
     * Salvar a Conta no BD
     */
    public void salvar(Outro_Pagamento a) {

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
    public List<Outro_Pagamento> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select a from Outro_Pagamento a", Outro_Pagamento.class);

        //Retornar a lista de Outro_Pagamento
        return consulta.getResultList();

    }

    /**
     * Salva as alterações no BD
     */
    public void editar(Outro_Pagamento a) {

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
    public void excluir(Outro_Pagamento a){
        
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
