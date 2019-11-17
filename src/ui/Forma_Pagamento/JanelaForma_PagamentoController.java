/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Forma_Pagamento;

import com.jfoenix.controls.JFXTextField;
import dados.entidades.Conta;
import dados.entidades.Forma_Pagamento;
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
import servicos.Forma_PagamentoServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author W4ND3K0
 */
public class JanelaForma_PagamentoController implements Initializable {

    @FXML
    private JFXTextField TFID;
    @FXML
    private JFXTextField TFDescricao;
    @FXML
    private TableView<Forma_Pagamento> TabelaForma_Pagamento;
    @FXML
    private TableColumn COID;
    @FXML
    private TableColumn CODescricao;
    
    //Atributo para representar o servico
    private Forma_PagamentoServico Servico = new Forma_PagamentoServico();
    
   //Atributo que representa os dados para tabela
    private ObservableList<Forma_Pagamento> Dados
            = FXCollections.observableArrayList();

    //Atributo que vai armazenar qual a Forma_Pagamento foi selecionado na tabela
    private Forma_Pagamento Selecionado;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Configure a tabela
        ConfigurarTabelaForma_Pagamento();

        //Carregue a lista de Forma_Pagamento na tabela
        ListarForma_PagamentoTabela();        
    }
    
        /**
    * Limpa os campos do formulário
    */
    private void LimparCampos(){
        
        //Limpando o form
        TFID.setText("");
        TFDescricao.setText("");
    }
    
        /**
    * Fazendo configuração das colunas da tabela
    */
    private void ConfigurarTabelaForma_Pagamento() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
        COID.setCellValueFactory(new PropertyValueFactory("Id_FormaPagamento"));
        CODescricao.setCellValueFactory(new PropertyValueFactory("Descricao_FormaPagamento"));
    }
    
    /**
    * Responsável por carregar a lista de Forma_Pagamento na tabela
    */
    private void ListarForma_PagamentoTabela() {
        //Limpando quaisquer dados anteriores
        Dados.clear();

        //Solicitando a camada de servico a lista de Conta
        List<Forma_Pagamento> Forma_Pagamento = Servico.listar();

        //Transformar a lista de Forma_Pagamento no formato que a tabela do JavaFX aceita
        Dados = FXCollections.observableArrayList(Forma_Pagamento);

        //Jogando os dados na tabela
        TabelaForma_Pagamento.setItems(Dados);
    }

    @FXML
    private void Salvar(ActionEvent event) {
        
        //Verificar se está atualizando ou inserindo
        if(TFID.getText().isEmpty()){ //inserindo
            //Pega os dados do fomulário e cria um objeto Conta
            Forma_Pagamento f = new Forma_Pagamento(TFDescricao.getText());
            
            //Mandar a Forma_Pagamento para a camada de servico
            Servico.salvar(f);
            
            //Exibindo mensagem
            AlertaUtil.mensagemSucesso("Forma de pagamento salva com sucesso!");
            
            //Chama o metodo para atualizar a tabela
            ListarForma_PagamentoTabela();
            
        }else{ //atualizando a Forma_Pagamento
           
            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn = 
                AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                      "EDITAR");
            
            //Se o botão OK foi pressionado
            if(btn.get() == ButtonType.OK){
                
                //Pegar os novos dados do formulário e atualizar a Conta
                Selecionado.setDescricao_FormaPagamento(TFDescricao.getText());
                
                //Mandando pra camada de serviço salvar as alterações
                Servico.editar(Selecionado);
                
                //Exibindo mensagem
                AlertaUtil.mensagemSucesso("Forma de pagamento atualizada com sucesso!"); 
                
                //Chama o metodo para atualizar a tabela
                 ListarForma_PagamentoTabela();
            }
        }

        //Limpando os campos do form
        LimparCampos();
    }

    @FXML
    private void Editar(ActionEvent event) {
        
        //Pegar a Forma_Pagamento que foi selecionado na tabela
        Selecionado = TabelaForma_Pagamento.getSelectionModel()
                .getSelectedItem();

        //Se tem alguma Forma_Pagamento selecionado
        
        //tem Conta selecionado
        if (Selecionado != null) {
            
            //Pegar os dados da Conta e jogar nos campos do formulario
            TFID.setText(String.valueOf(Selecionado.getId_FormaPagamento()));
            TFDescricao.setText(Selecionado.getDescricao_FormaPagamento());
        
        //não tem Forma_Pagamento selecionado na tabela
        
        }else{
            AlertaUtil.mensagemErro("Selecione uma forma de pagamento.");
        }
    }

    @FXML
    private void Excluir(ActionEvent event) {
        
        //Pegar a Forma_Pagamento que foi selecionado na tabela
        Selecionado = TabelaForma_Pagamento.getSelectionModel()
                .getSelectedItem();
        
        //Verifica se tem Forma_Pagamento selecionada
        
        //existe Forma_Pagamento selecionado
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
                AlertaUtil.mensagemSucesso("Forma de pagamento excluída com sucesso");
                
                //Atualizar a tabela
                ListarForma_PagamentoTabela();     
            }
        
        //Não existe Conta selecionado
        }else{
            AlertaUtil.mensagemErro("Selecione uma forma de pagamento.");
        }
    }
}
