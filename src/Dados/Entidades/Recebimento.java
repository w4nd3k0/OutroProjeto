/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados.Entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author W4ND3K0
 */

@Entity
public class Recebimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Recebimento;
    
    private LocalDate Data_Recebimento;
    private BigDecimal Valor_Recebimento;
    private String Descricao_Recebimento;
    
    @ManyToOne(optional=false)
    private Contato Contato_Recebimento;
    
    //Construtor vazio da JPA (Obrigatório)
    public Recebimento() {
    }
    
    /*Construtor*/

    public Recebimento(LocalDate Data_Recebimento, BigDecimal Valor_Recebimento, String Descricao_Recebimento, Contato Contatos_Recebimento) {
        this.Data_Recebimento = Data_Recebimento;
        this.Valor_Recebimento = Valor_Recebimento;
        this.Descricao_Recebimento = Descricao_Recebimento;
        this.Contato_Recebimento = Contatos_Recebimento;
    }
    
    public String getData_RecebimentoFormatado(){
        DateTimeFormatter formatador = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM);
        
        String formatado = Data_Recebimento.format(formatador);
        return formatado;
    }
    
    public String getContato_RecebimentoNome(){
        return Contato_Recebimento.getNome_Contato();
    }
      
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.Id_Recebimento);
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
        final Recebimento other = (Recebimento) obj;
        if (!Objects.equals(this.Id_Recebimento, other.Id_Recebimento)) {
            return false;
        }
        return true;
    }
       
    /*Get's*/    
    
    public Integer getId_Recebimento() {
        return Id_Recebimento;
    }

    public LocalDate getData_Recebimento() {
        return Data_Recebimento;
    }

    public BigDecimal getValor_Recebimento() {
        return Valor_Recebimento;
    }

    public String getDescricao_Recebimento() {
        return Descricao_Recebimento;
    }

    public Contato getContatos_Recebimento() {
        return Contato_Recebimento;
    }

    /*Set's*/
    public void setId_Recebimento(Integer Id_Recebimento) {
        this.Id_Recebimento = Id_Recebimento;
    }

    public void setData_Recebimento(LocalDate Data_Recebimento) {
        this.Data_Recebimento = Data_Recebimento;
    }

    public void setValor_Recebimento(BigDecimal Valor_Recebimento) {
        this.Valor_Recebimento = Valor_Recebimento;
    }

    public void setDescricao_Recebimento(String Descricao_Recebimento) {
        this.Descricao_Recebimento = Descricao_Recebimento;
    }

    public void setContatos_Recebimento(Contato Contatos_Recebimento) {
        this.Contato_Recebimento = Contatos_Recebimento;
    }    
}
