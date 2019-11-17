/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author W4ND3K0
 */

@Entity
public class Lancamento_Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_LancamentoPagamento;
    
    private String Descricao_LancamentoPagamento;
    
    //Construtor vazio da JPA (Obrigatório)
    public Lancamento_Pagamento() {
    }
    
    /*Construtor*/

    public Lancamento_Pagamento(String Descricao_LancamentoPagamento) {
        this.Descricao_LancamentoPagamento = Descricao_LancamentoPagamento;
    }
    
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.Id_LancamentoPagamento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lancamento_Pagamento other = (Lancamento_Pagamento) obj;
        if (!Objects.equals(this.Id_LancamentoPagamento, other.Id_LancamentoPagamento)) {
            return false;
        }
        return true;
    }
        
    /*Get's*/    
    
    public Integer getId_LancamentoPagamento() {
        return Id_LancamentoPagamento;
    }
    
    public String getDescricao_LancamentoPagamento() {
        return Descricao_LancamentoPagamento;
    }

    /*Set's*/
    public void setId_LancamentoPagamento(Integer Id_LancamentoPagamento) {
        this.Id_LancamentoPagamento = Id_LancamentoPagamento;
    }

    public void setDescricao_LancamentoPagamento(String Descricao_LancamentoPagamento) {
        this.Descricao_LancamentoPagamento = Descricao_LancamentoPagamento;
    }
}
