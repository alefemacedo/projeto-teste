package br.unialfa.teste.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertsUtil {

	public static void AlertSucess(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public static void AlertError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setContentText(message);
		alert.showAndWait();
	}
}
