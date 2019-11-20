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
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servicos.ContatoServico;
import servicos.Forma_PagamentoServico;
import servicos.Lancamento_PagamentoServico;
import servicos.Meu_PagamentoServico;
import util.AlertaUtil;

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
        
        //Configure a tabela
        ConfigurarTabelaMeu_Pagamento();
        
        //Carregue a lista de Meu_Pagamento na tabela
        ListarMeu_PagamentoTabela();

        //Carregue a lista de Contato na tabela
        ListarContato();
        
        //Carregue a lista de Lancamento_Pagamento na tabela
        ListarLancamento_Pagamento();
        
        //Carregue a lista de Forma_Pagamento na tabela
        ListarForma_Pagamento(); 
        
    }
    
        /**
     * Fazendo configuração das colunas da tabela
     */
    private void ConfigurarTabelaMeu_Pagamento() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_MeuPagamento"));
        COVencimento.setCellValueFactory(new PropertyValueFactory("Vencimento_MeuPagamento"));
        COPagamento.setCellValueFactory(new PropertyValueFactory("Pagamento_MeuPagamento"));
        COValor.setCellValueFactory(new PropertyValueFactory("Valor_MeuPagamento"));
        COContato.setCellValueFactory(new PropertyValueFactory("Contato_MeuPagamento"));
        COLancamento.setCellValueFactory(new PropertyValueFactory("Lancamento_MeuPagamento"));
        COForma.setCellValueFactory(new PropertyValueFactory("Forma_MeuPagamento"));
    }
    
        private void ListarMeu_PagamentoTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Meu_Pagamento
        List<Meu_Pagamento> Meu_Pagamento = ServicoMeu_Pagamento.listar();

        //Transformar a lista de no formato que a tabela
        //do JavaFX aceita
        Dados = FXCollections.observableArrayList(Meu_Pagamento);

        //Jogando os dados na tabela
        TabelaMeuPagamento.setItems(Dados);
    }
        
    /**
    * Carrega o comboBox de Contato no formulario
    */
    private void ListarContato() {

        List<Contato> Contato = ServicoContato.listar();

        CBContato.setItems(FXCollections.observableArrayList(Contato));
    }
    
    /**
    * Carrega o comboBox de Forma_Pagamento no formulario
    */
    private void ListarForma_Pagamento() {

        List<Forma_Pagamento> Forma_Pagamento = ServicoForma_Pagamento.listar();

        CBForma.setItems(FXCollections.observableArrayList(Forma_Pagamento));
    }
    
    /**
    * Carrega o comboBox de Lancamento_Pagamento no formulario
    */
    private void ListarLancamento_Pagamento() {

        List<Lancamento_Pagamento> Lancamento_Pagamento = ServicoLancamento_Pagamento.listar();

        CBLancamento.setItems(FXCollections.observableArrayList(Lancamento_Pagamento));
    }
    
    /**
    * Limpa os campos do formulário
    */
    private void LimparCampos() {
        TFID.setText("");
        DPVencimento.setValue(null);
        DPPagamento.setValue(null);
        TFValor.setText("");
        CBContato.setValue(null);
        CBLancamento.setValue(null);
        CBForma.setValue(null);
    }
     
    @FXML 
    private void Salvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        if (TFID.getText().isEmpty()) { //inserindo

            //Criando o objeto Meu_Pagamento
            Meu_Pagamento m = new Meu_Pagamento(DPVencimento.getValue(), DPPagamento.getValue(), new BigDecimal(TFValor.getText()), CBContato.getValue(), CBLancamento.getValue(), CBForma.getValue());
            //Mandando para a camada de serviço salvar
            ServicoMeu_Pagamento.salvar(m);

            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Pagamento salvo com sucesso!");

            //Carregando lista de Meu_Pagamento
            ListarMeu_PagamentoTabela();

        } else { //atualizando

            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                //Pegar os novos dados do formulário e atualizar o Meu_Pagamento
                Selecionado.setVencimento_MeuPagamento(DPVencimento.getValue());
                Selecionado.setPagamento_MeuPagamento(DPPagamento.getValue());
                Selecionado.setValor_MeuPagamento(new BigDecimal(TFValor.getText()));
                Selecionado.setContato_MeuPagamento(CBContato.getValue());
                Selecionado.setLancamento_MeuPagamento(CBLancamento.getValue());
                Selecionado.setForma_MeuPagamento(CBForma.getValue());

                
                //Mandando para a camada de serviço salvar as alterações
                ServicoMeu_Pagamento.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Pagamento atualizado com sucesso!");
                
                //Carregando lista de filmes
                ListarMeu_PagamentoTabela();   
            }   
        }

        //Limpando o form
        LimparCampos();
    }

    @FXML
    private void Editar(ActionEvent event) {
    }

    @FXML
    private void Excluir(ActionEvent event) {
    }
    
}
