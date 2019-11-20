/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Outro_Pagamento;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Outro_Pagamento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private JFXComboBox<Outro_Pagamento> CBContato;
    @FXML
    private JFXComboBox<Outro_Pagamento> CBForma;
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
