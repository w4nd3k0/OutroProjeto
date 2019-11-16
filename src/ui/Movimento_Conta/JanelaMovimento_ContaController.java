/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Movimento_Conta;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Conta;
import dados.entidades.Lancamento_Conta;
import dados.entidades.Movimento_Conta;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JComboBox;
import servicos.ContaServico;
import servicos.Lancamento_ContaServico;
import servicos.Movimento_ContaServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author W4ND3K0
 */
public class JanelaMovimento_ContaController implements Initializable {

    @FXML
    private JFXTextField TFID;
    @FXML
    private JFXDatePicker DPData;
    @FXML
    private JFXTextField TFDescricao;
    @FXML
    private JFXTextField TFValor;
    @FXML
    private JFXComboBox CBTipo;
    @FXML
    private JFXComboBox<Conta> CBConta;
    @FXML
    private JFXComboBox<Lancamento_Conta> CBLancamento;
    @FXML
    private TableView<Movimento_Conta> TabelaMovimento_Conta;
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn COData;
    @FXML
    private TableColumn CODescricao;
    @FXML
    private TableColumn COValor;
    @FXML
    private TableColumn COTipo;
    @FXML
    private TableColumn COConta;
    @FXML
    private TableColumn COLancamento;
    
    
    //Atributo que representa os tipo de lançamento Credito ou Débito para tabela
    private ObservableList<String> Tipo
            = FXCollections.observableArrayList("C","D");
    
    //Atributo que representa os dados para tabela
    private ObservableList<Movimento_Conta> Dados
            = FXCollections.observableArrayList();
    
    //Atributos para representar os servicos
    private ContaServico ServicoConta = new ContaServico();
    private Lancamento_ContaServico ServicoLancamento_Conta = new Lancamento_ContaServico();
    private Movimento_ContaServico ServicoMovimento_Conta = new Movimento_ContaServico();
    
    //Atributo para representar o Movimento_Conta selecionado
    //na tabela para editar e excluir
    private Movimento_Conta Selecionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CBTipo.setItems(Tipo);
        
        //Configure a tabela
        ConfigurarTabelaMovimento_Conta();
        
        //Carregue a lista de Movimento_Conta na tabela
        ListarMovimento_ContaTabela();

        //Carregue a lista de Contas na tabela
        ListarConta();
        
        //Carregue a lista de Lancamento_Conta na tabela
        ListarLancamento_Conta();   
    }
    
    /**
     * Fazendo configuração das colunas da tabela
     */
    private void ConfigurarTabelaMovimento_Conta() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_MovimentoConta"));
        COData.setCellValueFactory(new PropertyValueFactory("Data_MovimentoContaFormatado"));
        CODescricao.setCellValueFactory(new PropertyValueFactory("Descricao_MovimentoConta"));
        COValor.setCellValueFactory(new PropertyValueFactory("Valor_MovimentoConta"));
        COTipo.setCellValueFactory(new PropertyValueFactory("Tipo_MovimentoConta"));
        COConta.setCellValueFactory(new PropertyValueFactory("Numero_ContaNome"));
        COLancamento.setCellValueFactory(new PropertyValueFactory("LancamentoContaNome"));
    }
    
    private void ListarMovimento_ContaTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Movimento_Conta
        List<Movimento_Conta> Movimento_Conta = ServicoMovimento_Conta.listar();

        //Transformar a lista de no formato que a tabela
        //do JavaFX aceita
        Dados = FXCollections.observableArrayList(Movimento_Conta);

        //Jogando os dados na tabela
        TabelaMovimento_Conta.setItems(Dados);
    }
        
    /**
    * Carrega o comboBox de Conta no formulario
    */
    private void ListarConta() {

        List<Conta> Contas = ServicoConta.listar();

        CBConta.setItems(FXCollections.observableArrayList(Contas));

    }   
        
    /**
    * Carrega o comboBox de Lancamento_Conta no formulario
    */
    private void ListarLancamento_Conta() {

        List<Lancamento_Conta> Lancamento_Contas = ServicoLancamento_Conta.listar();

        CBLancamento.setItems(FXCollections.observableArrayList(Lancamento_Contas));
    }
    
    /**
    * Limpa os campos do formulário
    */
    private void LimparCampos() {
        TFID.setText("");
        DPData.setValue(null);
        TFDescricao.setText("");
        TFValor.setText("");
        CBTipo.setValue(null);
        CBConta.setValue(null);
        CBLancamento.setValue(null);
    }
    
    @FXML
    private void Salvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        if (TFID.getText().isEmpty()) { //inserindo

            //Criando o objeto filme
            Movimento_Conta f = new Movimento_Conta(DPData.getValue(), TFDescricao.getText(), 
                    new BigDecimal(TFValor.getText()), CBTipo.getValue().toString(), CBConta.getValue(), CBLancamento.getValue());
        
            //Mandando para a camada de serviço salvar
            ServicoMovimento_Conta.salvar(f);

            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Lançamento salvo com sucesso!");

            //Carregando lista de Movimento_Conta
            ListarMovimento_ContaTabela();

        } else { //atualizando

            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                //Pegar os novos dados do formulário e atualizar o Movimento_Conta
                Selecionado.setData_MovimentoConta(DPData.getValue());
                Selecionado.setDescricao_MovimentoConta(TFDescricao.getText());
                Selecionado.setValor_MovimentoConta(new BigDecimal(TFValor.getText()));
                Selecionado.setTipo_MovimentoConta(CBTipo.getValue().toString());
                Selecionado.setNumero_Conta(CBConta.getValue());
                Selecionado.setLancamentoConta(CBLancamento.getValue());
                
                //Mandando para a camada de serviço salvar as alterações
                ServicoMovimento_Conta.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Filme atualizado com sucesso!");
                
                //Carregando lista de filmes
                ListarMovimento_ContaTabela();
                
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

    

