/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Outro_Pagamento;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Contato;
import dados.entidades.Forma_Pagamento;
import dados.entidades.Outro_Pagamento;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
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
import servicos.Outro_PagamentoServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class JanelaOutro_PagamentoController implements Initializable {

    @FXML
    private JFXTextField TFID;
    @FXML
    private JFXDatePicker DPPagamento;
    @FXML
    private JFXTextField TFValor;
    @FXML
    private JFXTextField TFDescricao;
    @FXML
    private JFXComboBox<Contato> CBContato;
    @FXML
    private JFXComboBox<Forma_Pagamento> CBForma;
    @FXML
    private TableView<Outro_Pagamento> TabelaOutroPagamento;
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn COPagamento;
    @FXML
    private TableColumn COValor;
    @FXML
    private TableColumn CODescricao;
    @FXML
    private TableColumn COContato;
    @FXML
    private TableColumn COForma;
    
    //Atributo que representa os dados para tabela
    private ObservableList<Outro_Pagamento> Dados
            = FXCollections.observableArrayList();
    
    //Atributos para representar os servicos
    private Outro_PagamentoServico ServicoOutro_Pagamento = new Outro_PagamentoServico();
    
    private Forma_PagamentoServico ServicoForma_Pagamento = new Forma_PagamentoServico();
    private ContatoServico ServicoContato = new ContatoServico();
    
    //Atributo para representar o Outro_Pagamento selecionado
    //na tabela para editar e excluir
    private Outro_Pagamento Selecionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        ConfigurarTabelaOutro_Pagamento();
        
        //Carregue a lista de Outro_Pagamento na tabela
        ListarOutro_PagamentoTabela();

        //Carregue a lista de Contato na tabela
        ListarContato();
                
        //Carregue a lista de Forma_Pagamento na tabela
        ListarForma_Pagamento(); 
    }
    
    /**
    * Fazendo configuração das colunas da tabela
    */
    private void ConfigurarTabelaOutro_Pagamento() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_OutroPagamento"));
        CODescricao.setCellValueFactory(new PropertyValueFactory("Descricao_OutroPagamento"));
        COPagamento.setCellValueFactory(new PropertyValueFactory("Pagamento_OutroPagamentoFormatado"));
        COValor.setCellValueFactory(new PropertyValueFactory("Valor_OutroPagamento"));
        COContato.setCellValueFactory(new PropertyValueFactory("tContato_OutroPagamentoNome"));
        COForma.setCellValueFactory(new PropertyValueFactory("Forma_OutroPagamentoNome"));
    }
    
    private void ListarOutro_PagamentoTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Outro_Pagamento
        List<Outro_Pagamento> Outro_Pagamento = ServicoOutro_Pagamento.listar();

        //Transformar a lista de no formato que a tabela
        //do JavaFX aceita
        Dados = FXCollections.observableArrayList(Outro_Pagamento);

        //Jogando os dados na tabela
        TabelaOutroPagamento.setItems(Dados);
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
    * Limpa os campos do formulário
    */
    private void LimparCampos() {
        TFID.setText("");
        TFDescricao.setText("");
        DPPagamento.setValue(null);
        TFValor.setText("");
        CBContato.setValue(null);
        CBForma.setValue(null);
    }
    
    @FXML
    private void Salvar(ActionEvent event) {
        
         //Verificar se está atualizando ou inserindo
        if (TFID.getText().isEmpty()) { //inserindo

            //Criando o objeto Outro_Pagamento
            Outro_Pagamento m = new Outro_Pagamento(TFDescricao.getText(), DPPagamento.getValue(), new BigDecimal(TFValor.getText()), CBContato.getValue(), CBForma.getValue());
            
            //Mandando para a camada de serviço salvar
            ServicoOutro_Pagamento.salvar(m);

            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Pagamento salvo com sucesso!");

            //Carregando lista de Meu_Pagamento
            ListarOutro_PagamentoTabela();

        } else { //atualizando

            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                //Pegar os novos dados do formulário e atualizar o Outro_Pagamento
                Selecionado.setDescricao_OutroPagamento(TFDescricao.getText());
                Selecionado.setPagamento_OutroPagamento(DPPagamento.getValue());
                Selecionado.setValor_OutroPagamento(new BigDecimal(TFValor.getText()));
                Selecionado.setContato_OutroPagamento(CBContato.getValue());
                Selecionado.setForma_OutroPagamento(CBForma.getValue());

                
                //Mandando para a camada de serviço salvar as alterações
                ServicoOutro_Pagamento.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Pagamento atualizado com sucesso!");
                
                //Carregando lista de filmes
                ListarOutro_PagamentoTabela();   
            }   
        }

        //Limpando o form
        LimparCampos();
    }

    @FXML
    private void Editar(ActionEvent event) {
        
        //Pegar o Outro_Pagamento que foi selecionado na tabela 
        Selecionado = TabelaOutroPagamento.getSelectionModel().getSelectedItem();

        //Se tem algum Outro_Pagamento selecionado
        if (Selecionado != null) {
            
            //Pega os dados do Outro_Pagamento e joga no formulário
            TFID.setText(String.valueOf(Selecionado.getId_OutroPagamento()));
            TFDescricao.setText(Selecionado.getDescricao_OutroPagamento());
            DPPagamento.setValue(Selecionado.getPagamento_OutroPagamento());
            TFValor.setText(Selecionado.getValor_OutroPagamento().toString());
            CBContato.setValue(Selecionado.getContato_OutroPagamento());
            CBForma.setValue(Selecionado.getForma_OutroPagamento());
                        
        }else{//não selecionou Outro_Pagamento na tabela
            AlertaUtil.mensagemErro("Selecione um lançamento.");
        }        
    }

    @FXML
    private void Excluir(ActionEvent event) {
        
        //Pegar a Conta que foi selecionado na tabela
        Selecionado = TabelaOutroPagamento.getSelectionModel()
                .getSelectedItem();
        
        //Verifica se tem Meu_Pagamento selecionada
        
        //existe Conta selecionado
        if(Selecionado != null){
            
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir?",
                      "EXCLUIR");
            
            //Verificando se apertou o OK
            if(btn.get() == ButtonType.OK){
                
                //Manda para a camada de serviço excluir
                ServicoOutro_Pagamento.excluir(Selecionado);
                
                //mostrar mensagem de sucesso
                AlertaUtil.mensagemSucesso("Lançamento excluído com sucesso");
                
                //Atualizar a tabela
                ListarOutro_PagamentoTabela();     
            }
        
        //Não existe Conta selecionado
        }else{
            AlertaUtil.mensagemErro("Selecione um lançamento.");
        }
    }   
}
