package mtp.view.forward;

public class CommandAction {
	private boolean isSend;
	private String where;

	public CommandAction() {
	}

	public CommandAction(boolean isSend, String where) {
		this.isSend = isSend;
		this.where = where;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}
}
