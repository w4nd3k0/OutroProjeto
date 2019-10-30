package ui.ator;

import com.jfoenix.controls.JFXTextField;
import dados.entidades.Ator;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servicos.AtorServico;

/**
 * FXML Controller class
 *
 * @author lusst
 */
public class JanelaAtorController implements Initializable {

    @FXML
    private JFXTextField textFieldId;
    
    @FXML
    private JFXTextField textFieldNome;
    
    //Atributo para representar o servico
    private AtorServico servico = new AtorServico();
    
    @FXML
    private TableView<Ator> tabela;
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn colNome;
    
    //Atributo que representa os dados para tabela
    
    //import javafx.collections.FXCollections;
    //import javafx.collections.ObservableList;
    private ObservableList<Ator> dados = 
            FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     * Tudo que é feito ao inicializar a Janela
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        configurarTabela();
        
        //Carregue a lista de atores na tabela
        listarAtoresTabela();
        
    }

    @FXML
    private void salvar(ActionEvent event) {
        
        //Pega os dados do fomulário
        //e cria um objeto ator
        Ator a = new Ator(textFieldNome.getText());
        
        //Mandar o ator para a camada de servico
        servico.salvar(a);
        //Exibindo mensagem
        mensagemSucesso("Ator salvo com sucesso!");
        //Limpando o form
        textFieldNome.setText("");
    }
    
    public void mensagemSucesso(String m){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!"); 
        alerta.setHeaderText(null); 
        alerta.setContentText(m);
        alerta.showAndWait(); 
    }
    
    /**
     * Fazendo configuração das colunas da
     * tabeça
     */
    private void configurarTabela(){
        
        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        colId.setCellValueFactory(
                new PropertyValueFactory("id"));
        colNome.setCellValueFactory(
                new PropertyValueFactory("nome"));
        
    }//configurarTabela
    
    /**
     * Responsável por carregar a lista de atores 
     * na tabela
     */
    private void listarAtoresTabela(){
        //Limpando quaisquer dados anteriores
        dados.clear();
        
        //Solicitando a camada de servico a lista de atores
        List<Ator> atores = servico.listar();
        
        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(atores);
        
        //Jogando os dados na tabela
        tabela.setItems(dados);
        
    }
    
}
