/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Recebimento;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import Dados.Entidades.Contato;
import Dados.Entidades.Recebimento;
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
import Servicos.ContatoServico;
import Servicos.RecebimentoServico;
import Util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author W4ND3K0
 */
public class JanelaRecebimentoController implements Initializable {

    @FXML
    private JFXTextField TFID;
    @FXML
    private JFXDatePicker DPData;
    @FXML
    private JFXTextField TFValor;
    @FXML
    private JFXTextField TFDescricao;
    @FXML
    private TableView<Recebimento> TabelaRecebimento;
    @FXML
    private JFXComboBox<Contato> CBContato;
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn COData;
    @FXML
    private TableColumn COValor;
    @FXML
    private TableColumn CODescricao;
    @FXML
    private TableColumn COCOntato;
    
        //Atributo que representa os dados para tabela
    private ObservableList<Recebimento> Dados
            = FXCollections.observableArrayList();

    //Atributos para representar os servicos
    private RecebimentoServico ServicoRecebimento = new RecebimentoServico();
    private ContatoServico ServicoContato = new ContatoServico();

    //Atributo para representar o Recebimento selecionado
    //na tabela para editar e excluir
    private Recebimento Selecionado;    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        ConfigurarTabelaRecebimento();

        //Carregue a lista de Recebimento na tabela
        ListarRecebimentoTabela();

        //Carregar combo de Contato
        ListarContato();
        
    }
    
    /**
     * Fazendo configuração das colunas da tabela
     */
    private void ConfigurarTabelaRecebimento() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_Recebimento"));
        COData.setCellValueFactory(new PropertyValueFactory("Data_Recebimento"));
        COValor.setCellValueFactory(new PropertyValueFactory("Valor_Recebimento"));
        CODescricao.setCellValueFactory(new PropertyValueFactory("Descricao_Recebimento"));
        COCOntato.setCellValueFactory(new PropertyValueFactory("Contato_RecebimentoNome"));
    }
    
    /**
     * Responsável por carregar a lista de Recebimentos na tabela
     */
    private void ListarRecebimentoTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Recebimentos
        List<Recebimento> Recebimento = ServicoRecebimento.listar();

        //Transformar a lista de Recebimento no formato que a tabela
        //do JavaFX aceita
        Dados = FXCollections.observableArrayList(Recebimento);

        //Jogando os dados na tabela
        TabelaRecebimento.setItems(Dados);
    }
    
    /**
    * Carrega o comboBox de generos no formulario
    */
    private void ListarContato() {

        List<Contato> Contatos = ServicoContato.listar();

        CBContato.setItems(FXCollections.observableArrayList(Contatos));
    }
    
    /**
    * Limpa os campos do formulário
    */
    private void LimparCampos() {
        TFID.setText("");
        DPData.setValue(null);
        TFDescricao.setText("");
        TFValor.setText("");
        CBContato.setValue(null);
    }

    @FXML
    private void Salvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        if (TFID.getText().isEmpty()) { //inserindo

            //Criando o objeto Contato
            Recebimento r = new Recebimento(DPData.getValue(), new BigDecimal(TFValor.getText()), TFDescricao.getText(), 
                    CBContato.getValue());

            //Mandando para a camada de serviço salvar
            ServicoRecebimento.salvar(r);

            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Recebimento salvo com sucesso!");

            //Carregando lista de Recebimento
            ListarRecebimentoTabela();

        } else { //atualizando

            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                //Pegar os novos dados do formulário e
                //atualizar o Recebimento
                Selecionado.setData_Recebimento(DPData.getValue());
                Selecionado.setValor_Recebimento(new BigDecimal(TFValor.getText()));
                Selecionado.setDescricao_Recebimento(TFDescricao.getText());
                Selecionado.setContatos_Recebimento(CBContato.getValue());
                
                //Mandando para a camada de serviço salvar as alterações
                ServicoRecebimento.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Recebimento atualizado com sucesso!");
                
                //Carregando lista de filmes
                ListarRecebimentoTabela();
            }
        }

        //Limpando o form
        LimparCampos();
    }

    @FXML
    private void Editar(ActionEvent event) {
        
        //Pegar o Recebimento que foi selecionado na tabela 
        Selecionado = TabelaRecebimento.getSelectionModel().getSelectedItem();

        //Se tem algum Recebimento selecionado
        if (Selecionado != null) {
            
            //Pega os dados do Recebimento e joga no formulário
            TFID.setText(String.valueOf(Selecionado.getId_Recebimento()));
            DPData.setValue(Selecionado.getData_Recebimento());
            TFValor.setText(Selecionado.getValor_Recebimento().toString());
            TFDescricao.setText(Selecionado.getDescricao_Recebimento());
            CBContato.setValue(Selecionado.getContatos_Recebimento());
            
        }else{//não selecionou filme na tabela
            AlertaUtil.mensagemErro("Selecione um recebimento.");
        } 
    }

    @FXML
    private void Excuir(ActionEvent event) {
        
        //Pegar a Conta que foi selecionado na tabela
        Selecionado = TabelaRecebimento.getSelectionModel()
                .getSelectedItem();
        
        //Verifica se tem Recebimento selecionada
        
        //existe Conta selecionado
        if(Selecionado != null){
            
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir?",
                      "EXCLUIR");
            
            //Verificando se apertou o OK
            if(btn.get() == ButtonType.OK){
                
                //Manda para a camada de serviço excluir
                ServicoRecebimento.excluir(Selecionado);
                
                //mostrar mensagem de sucesso
                AlertaUtil.mensagemSucesso("Lançamento excluído com sucesso");
                
                //Atualizar a tabela
                ListarRecebimentoTabela();     
            }
        
        //Não existe Conta selecionado
        }else{
            AlertaUtil.mensagemErro("Selecione um lançamento.");
        }
        
    }   
}
