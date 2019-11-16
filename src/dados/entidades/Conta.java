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
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Conta;
    
    private String Nome_Conta;
    private String Banco_Conta;
    private String Agencia_Conta;
    private String Numero_Conta;
    
    //Construtor vazio da JPA (Obrigatório)
    public Conta() {
    }
    
    /*Construtor*/

    public Conta(String Nome_Conta, String Banco_Conta, String Agencia_Conta, String Numero_Conta) {
        this.Nome_Conta = Nome_Conta;
        this.Banco_Conta = Banco_Conta;
        this.Agencia_Conta = Agencia_Conta;
        this.Numero_Conta = Numero_Conta;
    }  
    
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.Id_Conta);
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
        final Conta other = (Conta) obj;
        if (!Objects.equals(this.Id_Conta, other.Id_Conta)) {
            return false;
        }
        return true;
    }

    /*Get's*/
    
    public void setId_Conta(Integer Id_Conta) {
        this.Id_Conta = Id_Conta;
    }

    public void setNome_Conta(String Nome_Conta) {
        this.Nome_Conta = Nome_Conta;
    }

    public void setBanco_Conta(String Banco_Conta) {
        this.Banco_Conta = Banco_Conta;
    }

    public void setAgencia_Conta(String Agencia_Conta) {
        this.Agencia_Conta = Agencia_Conta;
    }
    
    public void setNumero_Conta(String Numero_Conta) {
        this.Numero_Conta = Numero_Conta;
    }

    /*Get's*/
    
    public Integer getId_Conta() {
        return Id_Conta;
    }

    public String getNome_Conta() {
        return Nome_Conta;
    }

    public String getBanco_Conta() {
        return Banco_Conta;
    }

    public String getAgencia_Conta() {
        return Agencia_Conta;
    }

    public String getNumero_Conta() {
        return Numero_Conta;
    }
    
    /*Criar Lista que sera usado em movimento Contas*/
    
    public String toString(){
        return this.getNome_Conta();
    }
    
}
