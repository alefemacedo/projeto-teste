package br.unialfa.teste.controller;

import br.unialfa.teste.model.vo.Pessoa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerApplication extends Application {
	private Stage primaryStage;
	private Parent rootLayout;
	private static ControllerApplication controllerApplicationInstance;
	private static Pessoa pessoa = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.controllerApplicationInstance = this;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/menu.fxml"));
		this.rootLayout = loader.load();
		Scene scene = new Scene(rootLayout);
		this.primaryStage = primaryStage;
		this.primaryStage.setResizable(true);
		this.primaryStage.setTitle("Projeto Teste");
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Parent getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(Parent rootLayout) {
		this.rootLayout = rootLayout;
	}

	public static ControllerApplication getControllerApplicationInstance() {
		return controllerApplicationInstance;
	}

	public static Pessoa getPessoa() {
		return pessoa;
	}

	public static void setPessoa(Pessoa pessoa) {
		ControllerApplication.pessoa = pessoa;
	}

}
