package com.notifier.model;

import java.time.LocalDate;

public class Note {
	private int id;
    private String noteName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate remDate;
    private boolean status;
    private String desc;

    protected Note() {

    }

    

    public Note(String noteName, LocalDate startDate, LocalDate endDate, LocalDate remDate, boolean status,
			String desc) {
		super();
		this.noteName = noteName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remDate = remDate;
		this.status = status;
		this.desc = desc;
	}



	public Note(int id, String noteName, LocalDate startDate, LocalDate endDate, LocalDate remDate, boolean status,
			String desc) {
		super();
		this.id = id;
		this.noteName = noteName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remDate = remDate;
		this.status = status;
		this.desc = desc;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNoteName() {
		return noteName;
	}



	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}



	public LocalDate getStartDate() {
		return startDate;
	}



	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	public LocalDate getEndDate() {
		return endDate;
	}



	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	public LocalDate getRemDate() {
		return remDate;
	}



	public void setRemDate(LocalDate remDate) {
		this.remDate = remDate;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Note other = (Note) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
