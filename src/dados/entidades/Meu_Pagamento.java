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
 * @author IFNMG
 */
@Entity
public class Meu_Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_MeuPagamento;
    
    private String Desc_MeuPagamento;
    private LocalDate Vencimento_MeuPagamento;
    private LocalDate Pagamento_MeuPagamento;
    private BigDecimal Valor_MeuPagamento;
    
    @ManyToOne(optional=false)
    private Contato Contato_MeuPagamento;
    
    @ManyToOne(optional=false)
    private Lancamento_Pagamento Lancamento_MeuPagamento;
    
    @ManyToOne(optional=false)
    private Forma_Pagamento Forma_MeuPagamento;
        
    public String getContato_MeuPagamentoNome(){
        return Contato_MeuPagamento.getNome_Contato();
    }
    
    public String getLancamento_MeuPagamentoNome(){
        return Lancamento_MeuPagamento.getDescricao_LancamentoPagamento();
    }
    
    public String getForma_MeuPagamentoNome(){
        return Forma_MeuPagamento.getDescricao_FormaPagamento();
    }
    
    public String getVencimento_MeuPagamentoFormatado(){
        DateTimeFormatter formatador = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM);
        
        String formatado = Vencimento_MeuPagamento.format(formatador);
        return formatado;
    }
    
    public String getPagamento_MeuPagamentoFormatado(){
        DateTimeFormatter formatador = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM);
        
        String formatado = Pagamento_MeuPagamento.format(formatador);
        return formatado;
    }
    
    //Construtor vazio da JPA (Obrigatório)
    public Meu_Pagamento() {
    }
    
    /*Construtor*/

    public Meu_Pagamento(String Desc_MeuPagamento, LocalDate Vencimento_MeuPagamento, LocalDate Pagamento_MeuPagamento, BigDecimal Valor_MeuPagamento, Contato Contato_MeuPagamento, Lancamento_Pagamento Lancamento_MeuPagamento, Forma_Pagamento Forma_MeuPagamento) {
        this.Desc_MeuPagamento = Desc_MeuPagamento;
        this.Vencimento_MeuPagamento = Vencimento_MeuPagamento;
        this.Pagamento_MeuPagamento = Pagamento_MeuPagamento;
        this.Valor_MeuPagamento = Valor_MeuPagamento;
        this.Contato_MeuPagamento = Contato_MeuPagamento;
        this.Lancamento_MeuPagamento = Lancamento_MeuPagamento;
        this.Forma_MeuPagamento = Forma_MeuPagamento;
    }
     
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.Id_MeuPagamento);
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
        final Meu_Pagamento other = (Meu_Pagamento) obj;
        if (!Objects.equals(this.Id_MeuPagamento, other.Id_MeuPagamento)) {
            return false;
        }
        return true;
    }

    /*Get's*/

    public Integer getId_MeuPagamento() {
        return Id_MeuPagamento;
    }

    public String getDesc_MeuPagamento() {
        return Desc_MeuPagamento;
    }

    public LocalDate getVencimento_MeuPagamento() {
        return Vencimento_MeuPagamento;
    }

    public LocalDate getPagamento_MeuPagamento() {
        return Pagamento_MeuPagamento;
    }

    public BigDecimal getValor_MeuPagamento() {
        return Valor_MeuPagamento;
    }

    public Contato getContato_MeuPagamento() {
        return Contato_MeuPagamento;
    }

    public Lancamento_Pagamento getLancamento_MeuPagamento() {
        return Lancamento_MeuPagamento;
    }

    public Forma_Pagamento getForma_MeuPagamento() {
        return Forma_MeuPagamento;
    }
        
    /*Set's*/

    public void setId_MeuPagamento(Integer Id_MeuPagamento) {
        this.Id_MeuPagamento = Id_MeuPagamento;
    }

    public void setDesc_MeuPagamento(String Desc_MeuPagamento) {
        this.Desc_MeuPagamento = Desc_MeuPagamento;
    }

    public void setVencimento_MeuPagamento(LocalDate Vencimento_MeuPagamento) {
        this.Vencimento_MeuPagamento = Vencimento_MeuPagamento;
    }

    public void setPagamento_MeuPagamento(LocalDate Pagamento_MeuPagamento) {
        this.Pagamento_MeuPagamento = Pagamento_MeuPagamento;
    }

    public void setValor_MeuPagamento(BigDecimal Valor_MeuPagamento) {
        this.Valor_MeuPagamento = Valor_MeuPagamento;
    }

    public void setContato_MeuPagamento(Contato Contato_MeuPagamento) {
        this.Contato_MeuPagamento = Contato_MeuPagamento;
    }

    public void setLancamento_MeuPagamento(Lancamento_Pagamento Lancamento_MeuPagamento) {
        this.Lancamento_MeuPagamento = Lancamento_MeuPagamento;
    }

    public void setForma_MeuPagamento(Forma_Pagamento Forma_MeuPagamento) {
        this.Forma_MeuPagamento = Forma_MeuPagamento;
    }
}
