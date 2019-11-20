/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author IFNMG
 */
public class Meu_Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_MeuPagamento;
    
    private LocalDate Vencimento_MeuPagamento;
    private LocalDate Pagamento_MeuPagamento;
    private BigDecimal Valor_MeuPagamento;
    
    @ManyToOne(optional=false)
    private Contato Contato_MeuPagamento;
    
    @ManyToOne(optional=false)
    private Lancamento_Pagamento Lancamento_MeuPagamento;
    
    @ManyToOne(optional=false)
    private Forma_Pagamento Forma_MeuPagamento;
    
    //Construtor vazio da JPA (Obrigatório)
    public Meu_Pagamento() {
    }
    
    /*Construtor*/

    public Meu_Pagamento(LocalDate Vencimento_MeuPagamento, LocalDate Pagamento_MeuPagamento, BigDecimal Valor_MeuPagamento, Contato Contato_MeuPagamento, Lancamento_Pagamento Lancamento_MeuPagamento, Forma_Pagamento Tipo_MeuPagamento) {
        this.Vencimento_MeuPagamento = Vencimento_MeuPagamento;
        this.Pagamento_MeuPagamento = Pagamento_MeuPagamento;
        this.Valor_MeuPagamento = Valor_MeuPagamento;
        this.Contato_MeuPagamento = Contato_MeuPagamento;
        this.Lancamento_MeuPagamento = Lancamento_MeuPagamento;
        this.Forma_MeuPagamento = Tipo_MeuPagamento;
    }
        
    /*Equals e hashCode*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.Id_MeuPagamento);
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
    
    public Forma_Pagamento getTipo_MeuPagamento() {
        return Forma_MeuPagamento;
    }

    /*Set's*/
    public void setId_MeuPagamento(Integer Id_MeuPagamento) {
        this.Id_MeuPagamento = Id_MeuPagamento;
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

    public void setTipo_MeuPagamento(Forma_Pagamento Tipo_MeuPagamento) {
        this.Forma_MeuPagamento = Tipo_MeuPagamento;
    }   
    
}
