package net.wschat.chat;

public class User {

	private String name;
	private MessageSender messageSender;

	public User(MessageSender messageSender, String name) {
		this.messageSender = messageSender;
		this.name = name;
	}

	public void receiveMessage(String msg) {
		messageSender.sendMessage(msg);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
