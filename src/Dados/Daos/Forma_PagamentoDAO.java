/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados.Daos;

import Dados.Entidades.Forma_Pagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import Util.JPAUtil;

/**
 *
 * @author W4ND3K0
 */
public class Forma_PagamentoDAO {
    
    /**
     * Salvar a Forma_Pagamento no BD
     */
    public void salvar(Forma_Pagamento f) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o Forma_Pagamento
        gerenciador.persist(f);

        //Commit
        gerenciador.getTransaction().commit();

    }

    /**
     * Retorna uma lista com todos as Forma_Pagamento que estejam cadastrados no banco de
     * dados
     *
     * @return
     */
    public List<Forma_Pagamento> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select f from Forma_Pagamento f", Forma_Pagamento.class);

        //Retornar a lista de Forma_Pagamento
        return consulta.getResultList();
    }

    /**
     * Salva as alterações no BD
     */
    public void editar(Forma_Pagamento f) {

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
     * Exclui a Forma_Pagamento do BD
     */
    public void excluir(Forma_Pagamento f){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar a Forma_Pagamento do BD com a Forma_Pagamento que foi
        //selecionado na tela
        f = gerenciador.merge(f);

        //Mandar sincronizar as alterações 
        gerenciador.remove(f);
        
        //Commit na transação
        gerenciador.getTransaction().commit();   
    }
}
