package util;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserMenu {
	
	public static FileChooser getOpenFileChooser() {
		FileChooser fileOpener = new FileChooser();
		fileOpener.setTitle("Open A Text File");
		fileOpener.setInitialDirectory(new File(System.getProperty("user.dir")));
		fileOpener.getExtensionFilters().addAll(new ExtensionFilter("Text Documents", "*.txt"),
				new ExtensionFilter("All Files", "*"));
		return fileOpener;
	}
	
	public static FileChooser getSaveFileChooser() {
		FileChooser fileSaver = new FileChooser();
		fileSaver.setTitle("Save A Text File");
		fileSaver.setInitialDirectory(new File(System.getProperty("user.dir")));
		fileSaver.getExtensionFilters().addAll(new ExtensionFilter("Text Documents", "*.txt"),
				new ExtensionFilter("All Files", "*"));
		return fileSaver;
	}
}
