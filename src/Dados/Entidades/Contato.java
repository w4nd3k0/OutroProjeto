/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados.Entidades;

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
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Contato;
    
    private String Nome_Contato;
    private String Telefone_Contato;
    private String Banco_Contato;
    private String Agencia_Contato;
    private String Conta_Contato;
    
    //Construtor vazio da JPA (Obrigatório)
    public Contato() {
    }
    
    /*Construtor*/

    public Contato(String Nome_Contato, String Telefone_Contato, String Banco_Contato, String Agencia_Contato, String Conta_Contato) {
        this.Nome_Contato = Nome_Contato;
        this.Telefone_Contato = Telefone_Contato;
        this.Banco_Contato = Banco_Contato;
        this.Agencia_Contato = Agencia_Contato;
        this.Conta_Contato = Conta_Contato;
    }
    
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.Id_Contato);
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
        final Contato other = (Contato) obj;
        if (!Objects.equals(this.Id_Contato, other.Id_Contato)) {
            return false;
        }
        return true;
    }
        
    /*Get's*/
    
    public Integer getId_Contato() {
        return Id_Contato;
    }

    public String getNome_Contato() {
        return Nome_Contato;
    }

    public String getTelefone_Contato() {
        return Telefone_Contato;
    }

    public String getBanco_Contato() {
        return Banco_Contato;
    }

    public String getAgencia_Contato() {
        return Agencia_Contato;
    }

    public String getConta_Contato() {
        return Conta_Contato;
    }

    /*Set's*/
    public void setId_Contato(Integer Id_Contato) {
        this.Id_Contato = Id_Contato;
    }

    public void setNome_Contato(String Nome_Contato) {
        this.Nome_Contato = Nome_Contato;
    }

    public void setTelefone_Contato(String Telefone_Contato) {
        this.Telefone_Contato = Telefone_Contato;
    }

    public void setBanco_Contato(String Banco_Contato) {
        this.Banco_Contato = Banco_Contato;
    }

    public void setAgencia_Contato(String Agencia_Contato) {
        this.Agencia_Contato = Agencia_Contato;
    }

    public void setConta_Contato(String Conta_Contato) {
        this.Conta_Contato = Conta_Contato;
    }
    
    /*Criar Lista que sera usado em Recebimentos e Pagamentos*/
    
    public String toString(){
        return this.getNome_Contato();
    }
}
