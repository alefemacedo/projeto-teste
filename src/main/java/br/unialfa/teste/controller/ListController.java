package br.unialfa.teste.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.unialfa.teste.dao.PessoaDAO;
import br.unialfa.teste.model.vo.Pessoa;
import br.unialfa.teste.util.AlertsUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListController implements Initializable {
	@FXML
	private TableView<Pessoa> pessoaTableView;
	@FXML
	private TableColumn<Pessoa, String> nomeTableColumn;
	@FXML
	private TableColumn<Pessoa, String> emailTableColumn;
	@FXML
	private TableColumn<Pessoa, String> telefoneTableColumn;
	@FXML
	private TableColumn<Pessoa, String> enderecoTableColumn;
	@FXML
	private TextArea pesquisaTextArea;
	@FXML
	private Button pesquisarButton;
	@FXML
	private Button editarButton;
	@FXML
	private Button excluirButton;
	@FXML
	private Button cadastrarButton;

	private ObservableList<Pessoa> pessoasO = FXCollections.observableArrayList();
	private PessoaDAO dao;
	private Pessoa pessoaSelecionada;

	public void initialize(URL location, ResourceBundle resources) {
		this.dao = new PessoaDAO();

		this.nomeTableColumn.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		this.emailTableColumn.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("email"));
		this.telefoneTableColumn.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("telefone"));
		this.enderecoTableColumn.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("telefone"));

		this.pessoaTableView.setItems(listPessoas());
	}

	public ObservableList<Pessoa> listPessoas() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas = dao.listar("from Pessoa");
		for (Pessoa pessoa : pessoas) {
			this.pessoasO.add(pessoa);
		}

		return this.pessoasO;
	}

	@FXML
	public void cadastrar(ActionEvent event) {
		MenuController.getMenuControllerInstance().showFormularyView();
	}

	@FXML
	public void editar(ActionEvent event) {
		this.pessoaSelecionada = this.pessoaTableView.getSelectionModel().getSelectedItem();
		if (this.pessoaSelecionada != null) {
			Pessoa p = this.dao.recuperar(this.pessoaSelecionada.getId());
			ControllerApplication.setPessoa(p);
			MenuController.getMenuControllerInstance().showFormularyView();
		} else {
			AlertsUtil.AlertError("Por favor selecione uma pessoa da listagem.");
		}
	}

	@FXML
	public void remover(ActionEvent event) {
		this.pessoaSelecionada = this.pessoaTableView.getSelectionModel().getSelectedItem();
		if (this.pessoaSelecionada != null) {
			this.dao.remover(this.pessoaSelecionada.getId());
			AlertsUtil.AlertSucess("Pessoa removida com sucesso.");
			this.pessoasO.remove(this.pessoaSelecionada);
			this.pessoaSelecionada = null;
			this.pessoaTableView.refresh();
		} else {
			AlertsUtil.AlertError("Por favor selecione uma pessoa da listagem.");
		}
	}

	@FXML
	public void filterPessoas(ActionEvent event) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		if(!this.pesquisaTextArea.getText().equals("")) {
			String param = this.pesquisaTextArea.getText();
			pessoas = dao.listar("from Pessoa as pessoa where pessoa.nome like '%" + param + "%' or pessoa.email like '%" + param + "%'");
			this.pessoasO.clear();
			for (Pessoa pessoa : pessoas) {
				this.pessoasO.add(pessoa);
			}
			this.pessoaTableView.refresh();
		} else {
			AlertsUtil.AlertError("Por favor informe um nome ou email para realizar a filtragem.");
		}
	}

	public TableView<Pessoa> getPessoaTableView() {
		return pessoaTableView;
	}

	public void setPessoaTableView(TableView<Pessoa> pessoaTableView) {
		this.pessoaTableView = pessoaTableView;
	}

	public TableColumn<Pessoa, String> getNomeTableColumn() {
		return nomeTableColumn;
	}

	public void setNomeTableColumn(TableColumn<Pessoa, String> nomeTableColumn) {
		this.nomeTableColumn = nomeTableColumn;
	}

	public TableColumn<Pessoa, String> getEmailTableColumn() {
		return emailTableColumn;
	}

	public void setEmailTableColumn(TableColumn<Pessoa, String> emailTableColumn) {
		this.emailTableColumn = emailTableColumn;
	}

	public TableColumn<Pessoa, String> getTelefoneTableColumn() {
		return telefoneTableColumn;
	}

	public void setTelefoneTableColumn(TableColumn<Pessoa, String> telefoneTableColumn) {
		this.telefoneTableColumn = telefoneTableColumn;
	}

	public TableColumn<Pessoa, String> getEnderecoTableColumn() {
		return enderecoTableColumn;
	}

	public void setEnderecoTableColumn(TableColumn<Pessoa, String> enderecoTableColumn) {
		this.enderecoTableColumn = enderecoTableColumn;
	}

	public TextArea getPesquisaTextArea() {
		return pesquisaTextArea;
	}

	public void setPesquisaTextArea(TextArea pesquisaTextArea) {
		this.pesquisaTextArea = pesquisaTextArea;
	}

	public Button getPesquisarButton() {
		return pesquisarButton;
	}

	public void setPesquisarButton(Button pesquisarButton) {
		this.pesquisarButton = pesquisarButton;
	}

	public Button getEditarButton() {
		return editarButton;
	}

	public void setEditarButton(Button editarButton) {
		this.editarButton = editarButton;
	}

	public Button getExcluirButton() {
		return excluirButton;
	}

	public void setExcluirButton(Button excluirButton) {
		this.excluirButton = excluirButton;
	}

	public Button getCadastrarButton() {
		return cadastrarButton;
	}

	public void setCadastrarButton(Button cadastrarButton) {
		this.cadastrarButton = cadastrarButton;
	}

	public ObservableList<Pessoa> getPessoasO() {
		return pessoasO;
	}

	public void setPessoasO(ObservableList<Pessoa> pessoasO) {
		this.pessoasO = pessoasO;
	}

}
