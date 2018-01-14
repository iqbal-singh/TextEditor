package util;

import java.util.Stack;

public class UndoHistory {
	private Stack<String> undoStack = new Stack<String>();
	private Stack<String> redoStack = new Stack<String>();

	public void pushToUndoStack(String text) {
		undoStack.push(text);
	}

	public String popFromUndoStack() {
		return undoStack.pop();
	}

	public void pushtoRedoStack(String text) {
		redoStack.push(text);
	}

	public String popFromRedoStack() {
		return redoStack.pop();
	}

	public boolean canUndo() {
		return undoStack.size() > 1;
	}

	public boolean canRedo() {
		return redoStack.size() > 1;
	}

	public void clearAll() {
		undoStack.clear();
		redoStack.clear();
	}

	public String getLastState() {
		return undoStack.peek();
	}

	public String toString() {
		return "undo:" + undoStack.toString() + "\nredo:" + redoStack.toString();
	}
}
