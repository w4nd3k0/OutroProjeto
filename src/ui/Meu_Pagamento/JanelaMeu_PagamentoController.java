/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Meu_Pagamento;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Contato;
import dados.entidades.Forma_Pagamento;
import dados.entidades.Lancamento_Pagamento;
import dados.entidades.Meu_Pagamento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import servicos.ContatoServico;
import servicos.Forma_PagamentoServico;
import servicos.Lancamento_PagamentoServico;
import servicos.Meu_PagamentoServico;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class JanelaMeu_PagamentoController implements Initializable {

    @FXML
    private JFXTextField TFID;
    @FXML
    private JFXDatePicker DPVencimento;
    @FXML
    private JFXDatePicker DPPagamento;
    @FXML
    private JFXTextField TFValor;
    @FXML
    private JFXComboBox<Contato> CBContato;
    @FXML
    private JFXComboBox<Lancamento_Pagamento> CBLancamento;
    @FXML
    private JFXComboBox<Forma_Pagamento> CBForma;
    @FXML
    private TableView<Meu_Pagamento> TabelaMeuPagamento;
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn COVencimento;
    @FXML
    private TableColumn COPagamento;
    @FXML
    private TableColumn COValor;
    @FXML
    private TableColumn COContato;
    @FXML
    private TableColumn COLancamento;
    @FXML
    private TableColumn COForma;
        
    //Atributo que representa os dados para tabela
    private ObservableList<Meu_Pagamento> Dados
            = FXCollections.observableArrayList();
    
    //Atributos para representar os servicos
    private Forma_PagamentoServico ServicoForma_Pagamento = new Forma_PagamentoServico();
    private Lancamento_PagamentoServico ServicoLancamento_Pagamento = new Lancamento_PagamentoServico();
    private ContatoServico ServicoContato = new ContatoServico();
    private Meu_PagamentoServico ServicoMeu_Pagamento = new Meu_PagamentoServico();
    
    //Atributo para representar o Movimento_Conta selecionado
    //na tabela para editar e excluir
    private Meu_Pagamento Selecionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Salvar(ActionEvent event) {
    }

    @FXML
    private void Editar(ActionEvent event) {
    }

    @FXML
    private void Excluir(ActionEvent event) {
    }
    
}
