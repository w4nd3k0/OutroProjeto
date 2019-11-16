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
public class Lancamento_Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_LancamentoConta;
    
    private String Descricao_LancamentoConta;
    
    //Construtor vazio da JPA (Obrigatório)
    public Lancamento_Conta() {
    }
    
    /*Construtor*/

    public Lancamento_Conta(String Descricao_LancamentoConta) {
        this.Descricao_LancamentoConta = Descricao_LancamentoConta;
    }
    
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.Id_LancamentoConta);
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
        final Lancamento_Conta other = (Lancamento_Conta) obj;
        if (!Objects.equals(this.Id_LancamentoConta, other.Id_LancamentoConta)) {
            return false;
        }
        return true;
    }
    
    
    
    /*Get's*/

    public Integer getId_LancamentoConta() {
        return Id_LancamentoConta;
    }

    public String getDescricao_LancamentoConta() {
        return Descricao_LancamentoConta;
    }
    
    /*Set's*/

    public void setId_LancamentoConta(Integer Id_LancamentoConta) {
        this.Id_LancamentoConta = Id_LancamentoConta;
    }

    public void setDescricao_LancamentoConta(String Descricao_LancamentoConta) {
        this.Descricao_LancamentoConta = Descricao_LancamentoConta;
    }
    
    
    
}
