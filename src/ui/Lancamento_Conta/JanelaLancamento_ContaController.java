/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Lancamento_Conta;

import com.jfoenix.controls.JFXTextField;
import Dados.Entidades.Lancamento_Conta;
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
import Servicos.Lancamento_ContaServico;
import Util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author W4ND3K0
 */
public class JanelaLancamento_ContaController implements Initializable {

    @FXML
    private JFXTextField TFIDLancamento_Conta;
    @FXML
    private JFXTextField TFDescricaoLancamento_Conta;
    @FXML
    private TableView<Lancamento_Conta> TabelaLancamento_Conta;
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn CODescricao;
    
    //Atributo para representar o servico
    private Lancamento_ContaServico Servico = new Lancamento_ContaServico();
    
   //Atributo que representa os dados para tabela
    private ObservableList<Lancamento_Conta> Dados
            = FXCollections.observableArrayList();

    //Atributo que vai armazenar qual o Lancamento_Conta foi selecionado na tabela
    private Lancamento_Conta Selecionado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        ConfigurarTabelaLancamento_Conta();

        //Carregue a lista de Lancamento_Conta na tabela
        ListarLancamento_ContaTabela();
        
    }
    
        private void LimparCampos(){
        //Limpando o form
        TFIDLancamento_Conta.setText("");
        TFDescricaoLancamento_Conta.setText("");
    }
        
    private void ConfigurarTabelaLancamento_Conta() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_LancamentoConta"));
        CODescricao.setCellValueFactory(new PropertyValueFactory("Descricao_LancamentoConta"));
    }
    
    /**
    * Responsável por carregar a lista de Lancamento_Conta na tabela
    */
    private void ListarLancamento_ContaTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Lancamento_Conta
        List<Lancamento_Conta> Lancamento_Conta = Servico.listar();

        //Transformar a lista de Lancamento_Conta no formato que a tabela do JavaFX aceita
        Dados = FXCollections.observableArrayList(Lancamento_Conta);

        //Jogando os dados na tabela
        TabelaLancamento_Conta.setItems(Dados);

    }

    @FXML
    private void Salvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        if(TFIDLancamento_Conta.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário e cria um objeto Lancamento_Conta
            Lancamento_Conta a = new Lancamento_Conta(TFDescricaoLancamento_Conta.getText());
            //Mandar o Lancamento_Conta para a camada de servico
            Servico.salvar(a);
            
            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Lançamento salvo com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            ListarLancamento_ContaTabela();
            
        }else{ //atualizando o Lancamento_Conta
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                //Pegar os novos dados do formulário e atualizar a Lancamento_Conta
                Selecionado.setDescricao_LancamentoConta(TFDescricaoLancamento_Conta.getText());
                
                //Mandando pra camada de serviço salvar as alterações
                Servico.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Lançamento atualizada com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 ListarLancamento_ContaTabela();
            }
        }

        //Limpando os campos do form
        LimparCampos();
    }

    @FXML
    private void Editar(ActionEvent event) {
        
        //Pegar a Lancamento_Conta que foi selecionado na tabela
        Selecionado = TabelaLancamento_Conta.getSelectionModel()
                .getSelectedItem();

        //Se tem alguma Lancamento_Conta selecionado
        
        //tem Lancamento_Conta selecionado
        if (Selecionado != null) {
            
            //Pegar os dados do Lancamento_Conta e jogar nos campos do formulario
            TFIDLancamento_Conta.setText(String.valueOf(Selecionado.getId_LancamentoConta()));
            TFDescricaoLancamento_Conta.setText(Selecionado.getDescricao_LancamentoConta());
        
        //não tem Lancamento_Conta selecionado na tabela
        
        }else{
            AlertaUtil.mensagemErro("Selecione um lançamento.");
        }
    }

    @FXML
    private void Excluir(ActionEvent event) {
        
        //Pegar oLancamento_Conta que foi selecionado na tabela
        Selecionado = TabelaLancamento_Conta.getSelectionModel()
                .getSelectedItem();
        
        //Verifica se tem Lancamento_Conta selecionada
        
        //existe Lancamento_Conta selecionado
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
                AlertaUtil.mensagemSucesso("Lançamento excluído com sucesso");
                
                //Atualizar a tabela
                ListarLancamento_ContaTabela();     
            }
        
        //Não existe Conta selecionado
        }else{
            AlertaUtil.mensagemErro("Selecione um lançamento.");
        }
    }  
}
