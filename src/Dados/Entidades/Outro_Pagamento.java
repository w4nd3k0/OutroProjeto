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
 * @author IFNMG
 */
@Entity
public class Outro_Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_OutroPagamento;
    
    private String Descricao_OutroPagamento;
    private LocalDate Pagamento_OutroPagamento;
    private BigDecimal Valor_OutroPagamento;
    
    @ManyToOne(optional=false)
    private Contato Contato_OutroPagamento;
    
    @ManyToOne(optional=false)
    private Forma_Pagamento Forma_OutroPagamento;
    
    public String getContato_OutroPagamentoNome(){
        return Contato_OutroPagamento.getNome_Contato();
    }
    
    public String getForma_OutroPagamentoNome(){
        return Forma_OutroPagamento.getDescricao_FormaPagamento();
    }
    
    public String getPagamento_OutroPagamentoFormatado(){
        DateTimeFormatter formatador = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM);
        
        String formatado = Pagamento_OutroPagamento.format(formatador);
        return formatado;
    }
    
    //Construtor vazio da JPA (Obrigatório)
    public Outro_Pagamento() {
    }
    
    /*Construtor*/

    public Outro_Pagamento(String Descricao_OutroPagamento, LocalDate Pagamento_OutroPagamento, BigDecimal Valor_OutroPagamento, Contato Contato_OutroPagamento, Forma_Pagamento Forma_OutroPagamento) {
        this.Descricao_OutroPagamento = Descricao_OutroPagamento;
        this.Pagamento_OutroPagamento = Pagamento_OutroPagamento;
        this.Valor_OutroPagamento = Valor_OutroPagamento;
        this.Contato_OutroPagamento = Contato_OutroPagamento;
        this.Forma_OutroPagamento = Forma_OutroPagamento;
    }
            
    /*Equals e hashCode*/
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.Id_OutroPagamento);
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
        final Outro_Pagamento other = (Outro_Pagamento) obj;
        if (!Objects.equals(this.Id_OutroPagamento, other.Id_OutroPagamento)) {
            return false;
        }
        return true;
    }
            
    /*Get's*/
    
    public Integer getId_OutroPagamento() {
        return Id_OutroPagamento;
    }

    public LocalDate getPagamento_OutroPagamento() {
        return Pagamento_OutroPagamento;
    }

    public BigDecimal getValor_OutroPagamento() {
        return Valor_OutroPagamento;
    }

    public String getDescricao_OutroPagamento() {
        return Descricao_OutroPagamento;
    }

    public Contato getContato_OutroPagamento() {
        return Contato_OutroPagamento;
    }

    public Forma_Pagamento getForma_OutroPagamento() {
        return Forma_OutroPagamento;
    }
            
    /*Set's*/
    
    public void setId_OutroPagamento(Integer Id_OutroPagamento) {
        this.Id_OutroPagamento = Id_OutroPagamento;
    }

    public void setPagamento_OutroPagamento(LocalDate Pagamento_OutroPagamento) {
        this.Pagamento_OutroPagamento = Pagamento_OutroPagamento;
    }

    public void setValor_OutroPagamento(BigDecimal Valor_OutroPagamento) {
        this.Valor_OutroPagamento = Valor_OutroPagamento;
    }

    public void setDescricao_OutroPagamento(String Descricao_OutroPagamento) {
        this.Descricao_OutroPagamento = Descricao_OutroPagamento;
    }

    public void setContato_OutroPagamento(Contato Contato_OutroPagamento) {
        this.Contato_OutroPagamento = Contato_OutroPagamento;
    }

    public void setForma_OutroPagamento(Forma_Pagamento Forma_OutroPagamento) {
        this.Forma_OutroPagamento = Forma_OutroPagamento;
    }
            
}
