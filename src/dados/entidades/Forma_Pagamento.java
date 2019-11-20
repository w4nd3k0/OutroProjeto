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
public class Forma_Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_FormaPagamento;
    
    private String Descricao_FormaPagamento;
    
    //Construtor vazio da JPA (Obrigatório)
    public Forma_Pagamento() {
    }
    
    
    /*Construtor*/

    public Forma_Pagamento(String Descricao_FormaPagamento) {
        this.Descricao_FormaPagamento = Descricao_FormaPagamento;
    }
      
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.Id_FormaPagamento);
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
        final Forma_Pagamento other = (Forma_Pagamento) obj;
        if (!Objects.equals(this.Id_FormaPagamento, other.Id_FormaPagamento)) {
            return false;
        }
        return true;
    }
        
    /*Get's*/

    public Integer getId_FormaPagamento() {
        return Id_FormaPagamento;
    }

    public String getDescricao_FormaPagamento() {
        return Descricao_FormaPagamento;
    }
        
    /*Set's*/

    public void setId_FormaPagamento(Integer Id_FormaPagamento) {
        this.Id_FormaPagamento = Id_FormaPagamento;
    }

    public void setDescricao_FormaPagamento(String Descricao_FormaPagamento) {
        this.Descricao_FormaPagamento = Descricao_FormaPagamento;
    }
    
    /*Criar Lista que sera usado em Recebimentos e Pagamentos*/
    
    public String toString(){
        return this.getDescricao_FormaPagamento();
    }
}
