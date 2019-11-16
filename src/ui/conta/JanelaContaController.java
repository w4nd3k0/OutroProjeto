/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.conta;

import com.jfoenix.controls.JFXTextField;
import dados.entidades.Conta;
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
import servicos.ContaServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author W4ND3K0
 */
public class JanelaContaController implements Initializable {
    
    @FXML
    private JFXTextField TFIDConta;
    @FXML
    private JFXTextField TFNomeConta;
    @FXML
    private JFXTextField TFBancoConta;
    @FXML
    private JFXTextField TFAgenciaConta;
    @FXML
    private JFXTextField TFNumeroConta;

    @FXML
    private TableView<Conta> TabelaConta;
    
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn CONome;
    @FXML
    private TableColumn COBanco;
    @FXML
    private TableColumn COAgencia;
    @FXML
    private TableColumn COConta;
    
    //Atributo para representar o servico
    private ContaServico Servico = new ContaServico();
    
   //Atributo que representa os dados para tabela
    private ObservableList<Conta> Dados
            = FXCollections.observableArrayList();

    //Atributo que vai armazenar qual a conta foi selecionado na tabela
    private Conta Selecionado;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        ConfigurarTabelaConta();

        //Carregue a lista de Contas na tabela
        ListarContaTabela();
    }
    
    /**
    * Limpa os campos do formulário
    */
    private void LimparCampos(){
        //Limpando o form
        TFIDConta.setText("");
        TFNomeConta.setText("");
        TFBancoConta.setText("");
        TFAgenciaConta.setText("");
        TFNumeroConta.setText("");
    }
    
    /**
    * Fazendo configuração das colunas da tabela
    */
    private void ConfigurarTabelaConta() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_Conta"));
        CONome.setCellValueFactory(new PropertyValueFactory("Nome_Conta"));
        COBanco.setCellValueFactory(new PropertyValueFactory("Banco_Conta"));
        COAgencia.setCellValueFactory(new PropertyValueFactory("Agencia_Conta"));
        COConta.setCellValueFactory(new PropertyValueFactory("Numero_Conta"));
    }
    
    /**
    * Responsável por carregar a lista de atores na tabela
    */
    private void ListarContaTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Conta
        List<Conta> Conta = Servico.listar();

        //Transformar a lista de Contas no formato que a tabela do JavaFX aceita
        Dados = FXCollections.observableArrayList(Conta);

        //Jogando os dados na tabela
        TabelaConta.setItems(Dados);

    }

    @FXML
    private void Salvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        if(TFIDConta.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário e cria um objeto Conta
            Conta a = new Conta(TFNomeConta.getText(), TFBancoConta.getText(), TFAgenciaConta.getText(), TFNumeroConta.getText());

            //Mandar a Conta para a camada de servico
            Servico.salvar(a);
            
            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Conta salva com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            ListarContaTabela();
            
        }else{ //atualizando a Conta
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                //Pegar os novos dados do formulário e atualizar a Conta
                Selecionado.setNome_Conta(TFNomeConta.getText());
                Selecionado.setBanco_Conta(TFBancoConta.getText());
                Selecionado.setAgencia_Conta(TFAgenciaConta.getText());
                Selecionado.setNumero_Conta(TFNumeroConta.getText());
                
                //Mandando pra camada de serviço salvar as alterações
                Servico.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Conta atualizada com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 ListarContaTabela();
            }
        }

        //Limpando os campos do form
        LimparCampos();
    }

    @FXML
    private void Editar(ActionEvent event) {
        
        //Pegar a Conta que foi selecionado na tabela
        Selecionado = TabelaConta.getSelectionModel()
                .getSelectedItem();

        //Se tem alguma Conta selecionado
        
        //tem Conta selecionado
        if (Selecionado != null) {
            
            //Pegar os dados da Conta e jogar nos campos do formulario
            TFIDConta.setText(String.valueOf(Selecionado.getId_Conta()));
            TFNomeConta.setText(Selecionado.getNome_Conta());
            TFBancoConta.setText(Selecionado.getBanco_Conta());
            TFAgenciaConta.setText(Selecionado.getAgencia_Conta());
            TFNumeroConta.setText(Selecionado.getNumero_Conta());
        
        //não tem conta selecionado na tabela
        
        }else{
            AlertaUtil.mensagemErro("Selecione uma conta.");
        }
    }

    @FXML
    private void Excuir(ActionEvent event) {
        
        //Pegar a Conta que foi selecionado na tabela
        Selecionado = TabelaConta.getSelectionModel()
                .getSelectedItem();
        
        //Verifica se tem Conta selecionada
        
        //existe Conta selecionado
        if(Selecionado != null){
            
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir?",
                      "EXCLUIR");
            
            //Verificando se apertou o OK
            if(btn.get() == ButtonType.OK){
                
                //Manda para a camada de serviço excluir
                Servico.excluir(Selecionado);
                
                //mostrar mensagem de sucesso
                AlertaUtil.mensagemSucesso("Conta excluída com sucesso");
                
                //Atualizar a tabela
                ListarContaTabela();     
            }
        
        //Não existe Conta selecionado
        }else{
            AlertaUtil.mensagemErro("Selecione uma conta.");
        }
    }
}
