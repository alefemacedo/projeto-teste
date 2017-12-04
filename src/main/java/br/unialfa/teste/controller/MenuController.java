package br.unialfa.teste.controller;

import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuController implements Initializable {
	@FXML
	private BorderPane paneMenu;
	@FXML
	private MenuItem listMenuItem;
	@FXML
	private MenuItem formularyMenuItem;
	@FXML
	private MenuItem exitMenuItem;

	private static MenuController menuControllerInstance;

	public void initialize(URL location, ResourceBundle resources) {
		this.menuControllerInstance = this;
		this.showListView();
	}

	public void showListView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/lista.fxml"));
		try {
			SplitPane view = loader.load();
			this.paneMenu.setCenter(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showFormularyView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/formulary.fxml"));
		try {
			AnchorPane view = loader.load();
			this.paneMenu.setCenter(view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sair() {
		ControllerApplication.getControllerApplicationInstance().getPrimaryStage().close();
		System.exit(0);
	}

	public BorderPane getPaneMenu() {
		return paneMenu;
	}

	public void setPaneMenu(BorderPane paneMenu) {
		this.paneMenu = paneMenu;
	}

	public MenuItem getListMenuItem() {
		return listMenuItem;
	}

	public void setListMenuItem(MenuItem listMenuItem) {
		this.listMenuItem = listMenuItem;
	}

	public MenuItem getFormularyMenuItem() {
		return formularyMenuItem;
	}

	public void setFormularyMenuItem(MenuItem formularyMenuItem) {
		this.formularyMenuItem = formularyMenuItem;
	}

	public static MenuController getMenuControllerInstance() {
		return menuControllerInstance;
	}

	public static void setMenuControllerInstance(MenuController menuControllerInstance) {
		MenuController.menuControllerInstance = menuControllerInstance;
	}

}
