/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Contato;

import com.jfoenix.controls.JFXTextField;
import dados.entidades.Conta;
import dados.entidades.Contato;
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
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author W4ND3K0
 */
public class JanelaContatoController implements Initializable {

    @FXML
    private JFXTextField TFID;
    @FXML
    private JFXTextField TFNome;
    @FXML
    private JFXTextField TFTelefone;
    @FXML
    private JFXTextField TFBanco;
    @FXML
    private JFXTextField TFAgencia;
    @FXML
    private JFXTextField TFConta;
    @FXML
    private TableView<Contato> TabelaContato;
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn CONome;
    @FXML
    private TableColumn CoTelefone;
    @FXML
    private TableColumn COBanco;
    @FXML
    private TableColumn COAgencia;
    @FXML
    private TableColumn COConta;
    
    //Atributo para representar o servico
    private ContatoServico Servico = new ContatoServico();
    
   //Atributo que representa os dados para tabela
    private ObservableList<Contato> Dados
            = FXCollections.observableArrayList();

    //Atributo que vai armazenar qual o Contato foi selecionado na tabela
    private Contato Selecionado;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        ConfigurarTabelaContato();

        //Carregue a lista de Contatos na tabela
        ListarContatoTabela();
    }
    
    /**
    * Limpa os campos do formulário
    */
    private void LimparCampos(){
        //Limpando o form
        TFID.setText("");
        TFNome.setText("");
        TFTelefone.setText("");
        TFBanco.setText("");
        TFAgencia.setText("");
        TFConta.setText("");
    }
    
    /**
    * Fazendo configuração das colunas da tabela
    */
    private void ConfigurarTabelaContato() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_Contato"));        
        CONome.setCellValueFactory(new PropertyValueFactory("Nome_Contato"));
        CoTelefone.setCellValueFactory(new PropertyValueFactory("Telefone_Contato"));        
        COBanco.setCellValueFactory(new PropertyValueFactory("Banco_Contato"));
        COAgencia.setCellValueFactory(new PropertyValueFactory("Agencia_Contato"));
        COConta.setCellValueFactory(new PropertyValueFactory("Conta_Contato"));
    }
    
    /**
    * Responsável por carregar a lista de Contatos na tabela
    */
    private void ListarContatoTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Contato
        List<Contato> Conta = Servico.listar();

        //Transformar a lista de Contatos no formato que a tabela do JavaFX aceita
        Dados = FXCollections.observableArrayList(Conta);

        //Jogando os dados na tabela
        TabelaContato.setItems(Dados);

    }

    @FXML
    private void Salvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        
        if(TFID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário e cria um objeto Contato
            Contato a = new Contato(TFNome.getText(), TFTelefone.getText(), TFBanco.getText(), TFAgencia.getText(), TFConta.getText());
            //Mandar a Conta para a camada de servico
            Servico.salvar(a);
            
            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Contato salvo com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            ListarContatoTabela();
            
        }else{ //atualizando o Contato
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                //Pegar os novos dados do formulário e atualizar a Conta
                Selecionado.setNome_Contato(TFNome.getText());
                Selecionado.setTelefone_Contato(TFTelefone.getText());
                Selecionado.setBanco_Contato(TFBanco.getText());
                Selecionado.setAgencia_Contato(TFAgencia.getText());
                Selecionado.setConta_Contato(TFConta.getText());
                
                //Mandando pra camada de serviço salvar as alterações
                Servico.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Contato atualizada com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 ListarContatoTabela();
            }
        }
        //Limpando os campos do form
        LimparCampos();
    }

    @FXML
    private void Editar(ActionEvent event) {
        
        //Pegar o Contato que foi selecionado na tabela
        Selecionado = TabelaContato.getSelectionModel()
                .getSelectedItem();

        //Se tem alguma Contato selecionado
        
        //tem Contato selecionado
        if (Selecionado != null) {
            
            //Pegar os dados da Conta e jogar nos campos do formulario
            TFID.setText(String.valueOf(Selecionado.getId_Contato()));
            TFNome.setText(Selecionado.getNome_Contato());
            TFTelefone.setText(Selecionado.getTelefone_Contato());
            TFBanco.setText(Selecionado.getBanco_Contato());
            TFAgencia.setText(Selecionado.getAgencia_Contato());
            TFConta.setText(Selecionado.getConta_Contato());
        
        //não tem Contato selecionado na tabela
        
        }else{
            AlertaUtil.mensagemErro("Selecione um contato.");
        }
    }

    @FXML
    private void Excuir(ActionEvent event) {
        
        //Pegar o Contato que foi selecionado na tabela
        Selecionado = TabelaContato.getSelectionModel()
                .getSelectedItem();
        
        //Verifica se tem Contato selecionada
        
        //existe Contato selecionado
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
                AlertaUtil.mensagemSucesso("Contato excluída com sucesso");
                
                //Atualizar a tabela
                ListarContatoTabela();     
            }
        
        //Não existe Conta selecionado
        }else{
            AlertaUtil.mensagemErro("Selecione uma conta.");
        }
    }   
}
