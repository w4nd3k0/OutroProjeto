/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author lusst
 */
public class AlertaUtil {
    
    /**
     * Exibe uma mensagem de erro
     * @param m 
     */
    public static void mensagemErro(String m) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }
    
    /**
     * Exibe uma mensagem de sucesso
     * @param m 
     */
    public static void mensagemSucesso(String m) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }
    
    /**
     * Mostra uma caixa com uma mensagem de confirmação
     * onde a pessoa vai poder responder se deseja realizar
     * uma ação
     */
    public static Optional<ButtonType> mensagemDeConfirmacao(
            String mensagem, String titulo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }
    
}
