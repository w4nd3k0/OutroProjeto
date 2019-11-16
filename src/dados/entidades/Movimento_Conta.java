/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;

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
public class Movimento_Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_MovimentoConta;
    
    private LocalDate Data_MovimentoConta;
    private String Descricao_MovimentoConta;
    private BigDecimal Valor_MovimentoConta;
    private String Tipo_MovimentoConta; //Crédito ou débito
    
    @ManyToOne(optional=false)
    private Conta Numero_Conta;
    
    @ManyToOne(optional=false)
    private Lancamento_Conta LancamentoConta;
    
    //Construtor vazio da JPA (Obrigatório)
    public Movimento_Conta() {
    }
    
    public String getNumero_ContaNome(){
        return Numero_Conta.getNome_Conta();
    }
    
    public String getLancamentoContaNome(){
        return LancamentoConta.getDescricao_LancamentoConta();
    }
    
    public String getData_MovimentoContaFormatado(){
        DateTimeFormatter formatador = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM);
        
        String formatado = Data_MovimentoConta.format(formatador);
        return formatado;
    }
    
    /*Construtor*/

    public Movimento_Conta(LocalDate Data_MovimentoConta, String Descricao_MovimentoConta, BigDecimal Valor_MovimentoConta, String Tipo_MovimentoConta, Conta Numero_Conta, Lancamento_Conta LancamentoConta) {
        this.Data_MovimentoConta = Data_MovimentoConta;
        this.Descricao_MovimentoConta = Descricao_MovimentoConta;
        this.Valor_MovimentoConta = Valor_MovimentoConta;
        this.Tipo_MovimentoConta = Tipo_MovimentoConta;
        this.Numero_Conta = Numero_Conta;
        this.LancamentoConta = LancamentoConta;
    }
        
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.Id_MovimentoConta);
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
        final Movimento_Conta other = (Movimento_Conta) obj;
        if (!Objects.equals(this.Id_MovimentoConta, other.Id_MovimentoConta)) {
            return false;
        }
        return true;
    }
        
    /*Get's*/

    public Integer getId_MovimentoConta() {
        return Id_MovimentoConta;
    }

    public LocalDate getData_MovimentoConta() {
        return Data_MovimentoConta;
    }

    public String getDescricao_MovimentoConta() {
        return Descricao_MovimentoConta;
    }

    public BigDecimal getValor_MovimentoConta() {
        return Valor_MovimentoConta;
    }

    public String getTipo_MovimentoConta() {
        return Tipo_MovimentoConta;
    }

    public Conta getNumero_Conta() {
        return Numero_Conta;
    }

    public Lancamento_Conta getLancamentoConta() {
        return LancamentoConta;
    }    
    
    /*Set's*/

    public void setId_MovimentoConta(Integer Id_MovimentoConta) {
        this.Id_MovimentoConta = Id_MovimentoConta;
    }

    public void setData_MovimentoConta(LocalDate Data_MovimentoConta) {
        this.Data_MovimentoConta = Data_MovimentoConta;
    }

    public void setDescricao_MovimentoConta(String Descricao_MovimentoConta) {
        this.Descricao_MovimentoConta = Descricao_MovimentoConta;
    }

    public void setValor_MovimentoConta(BigDecimal Valor_MovimentoConta) {
        this.Valor_MovimentoConta = Valor_MovimentoConta;
    }

    public void setTipo_MovimentoConta(String Tipo_MovimentoConta) {
        this.Tipo_MovimentoConta = Tipo_MovimentoConta;
    }

    public void setNumero_Conta(Conta Numero_Conta) {
        this.Numero_Conta = Numero_Conta;
    }

    public void setLancamentoConta(Lancamento_Conta LancamentoConta) {
        this.LancamentoConta = LancamentoConta;
    }   
}