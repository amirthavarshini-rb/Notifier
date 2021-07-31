package com.notifier.model;

public class NewNote {
	private String nbName;
	private int note_id;
	public String getNbName() {
		return nbName;
	}
	public void setNbName(String nbName) {
		this.nbName = nbName;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public NewNote( int note_id,String nbName) {
		super();
		this.nbName = nbName;
		this.note_id = note_id;
	}

	
	
}
