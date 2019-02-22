package dtos;

public class Llegada {

	private String user;
	private String date;
	private String time;
	private String hasNote;
	private String noteText;

	public Llegada(String user, String date, String time, String hasNote, String noteText) {
		super();
		this.user = user;
		this.date = date;
		this.time = time;
		this.hasNote = hasNote;
		this.noteText = noteText;
	}

	public String getHasNote() {
		return hasNote;
	}

	public void setHasNote(String hasNote) {
		this.hasNote = hasNote;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
