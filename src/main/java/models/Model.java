package models;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import util.UndoHistory;

public class Model {
	private File currFile = null;
	private UndoHistory undoHistory = new UndoHistory();

	public void open(File f) {
		currFile = f;
	}

	public String getFileContent() {
		String fileText = "";
		try {
			fileText = FileUtils.readFileToString(currFile, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileText;
	}

	public void saveAs(String editorContent, File newFile) {
		try {
			FileUtils.writeStringToFile(newFile, editorContent, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		currFile = newFile;
	}

	public File getCurrFile() {
		return currFile;
	}

	public void closeFile() {
		currFile = null;

	}

	public UndoHistory getUndoHistory() {
		return undoHistory;
	}


}
