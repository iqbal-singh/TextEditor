package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Model;
import util.FileChooserMenu;

public class MainController implements Initializable {

	@FXML
	private JFXButton NewItem;

	@FXML
	private JFXButton OpenItem;

	@FXML
	private JFXButton FindItem;
	
	@FXML
	private JFXButton SaveItem;

	@FXML
	private JFXButton SaveAsItem;

	@FXML
	private JFXButton ExitItem;

	@FXML
	private JFXButton undoItem;

	@FXML
	private JFXButton redoItem;

	@FXML
	private TextArea Text;

	@FXML
	private Label Status;

	@FXML
	private Label Count;

	private Model model = new Model();

	@Override
	public void initialize(URL location, ResourceBundle resourceBundle) {

		Text.textProperty().addListener((observable, oldValue, newValue) -> {
			model.getUndoHistory().pushToUndoStack(newValue);
			setTextCounters();
		});

	}

	private void setTextCounters() {
		String info = "CHARACTERS:" + Text.getLength() + " WORDS:" + Text.getText().split("\\s+").length;
		Count.setText(info);
	}

	@FXML
	private void OpenFile() {

		File f = FileChooserMenu.getOpenFileChooser().showOpenDialog(null);

		if (f != null) {
			model.open(f);
			Text.setText(model.getFileContent());
			Status.setText(f + " was opened.");

		}
	}

	@FXML
	private void SaveFile() {
		if (model.getCurrFile() == null) {
			SaveAsFile();
		} else {
			model.saveAs(Text.getText(), model.getCurrFile());
			Status.setText(model.getCurrFile() + " was saved.");
		}
	}

	@FXML
	private void SaveAsFile() {

		File f = FileChooserMenu.getSaveFileChooser().showSaveDialog(null);

		if (f != null) {
			model.saveAs(Text.getText(), f);
			Status.setText(f + " was saved.");
		}
	}

	@FXML
	private void NewFile() {
		model.getUndoHistory().clearAll();
		model.closeFile();
		Text.clear();
		Status.setText("A new document was created.");
	}

	@FXML
	private void undo() {
		if (model.getUndoHistory().canUndo()) {
			model.getUndoHistory().pushtoRedoStack(model.getUndoHistory().popFromUndoStack());
			Text.setText(model.getUndoHistory().popFromUndoStack());
			Status.setText("Undo completed.");
		} else {
			Status.setText("Nothing to Undo.");
		}

	}

	@FXML
	private void redo() {
		if (model.getUndoHistory().canRedo()) {
			Text.setText(model.getUndoHistory().popFromRedoStack());
			Status.setText("Redo completed.");
		} else {
			Status.setText("Nothing to Redo.");
		}

	}

	@FXML
	private void find() {

	}

	@FXML
	private void quit() {
		System.exit(0);
	}

}
