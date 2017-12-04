package br.unialfa.teste.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

import br.unialfa.teste.dao.PessoaDAO;
import br.unialfa.teste.model.vo.Pessoa;
import br.unialfa.teste.util.AlertsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FormularyController implements Initializable {
	@FXML
	private AnchorPane formularyPane;
	@FXML
	private Label titleLabel;
	@FXML
	private TextField nomeTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telefoneTextField;
	@FXML
	private TextField enderecoTextField;
	@FXML
	private Button salvarButtom;
	@FXML
	private Button listarButton;

	private PessoaDAO dao;
	private Pessoa pessoa;

	public void initialize(URL location, ResourceBundle resources) {
		this.dao = new PessoaDAO();
		this.titleLabel.setText("Cadastro de Pessoa");
		this.verificaPessoa();
	}

	@FXML
	public void salvar(ActionEvent event) {
		String nome = this.nomeTextField.getText();
		String email = this.emailTextField.getText();
		String telefone = this.telefoneTextField.getText();
		String endereco = this.enderecoTextField.getText();
		if (!nome.equals("") && !email.equals("")) {
			this.pessoa.setNome(nome);
			this.pessoa.setEmail(email);
			this.pessoa.setTelefone(telefone);
			this.pessoa.setEndereco(endereco);

			this.dao.cadastrarOuEditar(this.pessoa);
			AlertsUtil.AlertSucess("Cadastro/Edição realizado(a) com sucesso!");

			this.nomeTextField.setText("");
			this.emailTextField.setText("");
			this.telefoneTextField.setText("");
			this.enderecoTextField.setText("");
			ControllerApplication.getControllerApplicationInstance().setPessoa(null);
		} else if (nome.equals("")) {
			AlertsUtil.AlertError("O campo nome não pode ser nulo.");
		} else if (email.equals("")) {
			AlertsUtil.AlertError("O campo email não pode ser nulo.");
		}
	}

	@FXML
	public void listar(ActionEvent event) {
		MenuController.getMenuControllerInstance().showListView();
	}

	public void verificaPessoa() {
		this.pessoa = ControllerApplication.getControllerApplicationInstance().getPessoa();
		if (this.pessoa != null) {
			this.titleLabel.setText("Edição de Pessoa");
			this.nomeTextField.setText(this.pessoa.getNome());
			this.emailTextField.setText(this.pessoa.getEmail());
			this.telefoneTextField.setText(this.pessoa.getTelefone());
			this.enderecoTextField.setText(this.pessoa.getEndereco());
		} else {
			this.pessoa = new Pessoa();
		}
	}

	public AnchorPane getFormularyPane() {
		return formularyPane;
	}

	public void setFormularyPane(AnchorPane formularyPane) {
		this.formularyPane = formularyPane;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}

	public TextField getNomeTextField() {
		return nomeTextField;
	}

	public void setNomeTextField(TextField nomeTextField) {
		this.nomeTextField = nomeTextField;
	}

	public TextField getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(TextField emailTextField) {
		this.emailTextField = emailTextField;
	}

	public TextField getTelefoneTextField() {
		return telefoneTextField;
	}

	public void setTelefoneTextField(TextField telefoneTextField) {
		this.telefoneTextField = telefoneTextField;
	}

	public TextField getEnderecoTextField() {
		return enderecoTextField;
	}

	public void setEnderecoTextField(TextField enderecoTextField) {
		this.enderecoTextField = enderecoTextField;
	}

	public Button getSalvarButtom() {
		return salvarButtom;
	}

	public void setSalvarButtom(Button salvarButtom) {
		this.salvarButtom = salvarButtom;
	}

	public Button getListarButton() {
		return listarButton;
	}

	public void setListarButton(Button listarButton) {
		this.listarButton = listarButton;
	}

}
