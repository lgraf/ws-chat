package net.wschat.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

	private final List<User> users = new ArrayList<>();

	public void enter(User newUser) {
		if (isUserNameFree(newUser)) {
			users.add(newUser);
			sendToAll(createWelcomeMessage(newUser));
		}
	}

	String createWelcomeMessage(User newUser) {
		return "user " + newUser.getName() + " entered the chatroom";
	}

	private boolean isUserNameFree(User user) {
		for (User u : users) {
			if (user.getName().equals(u.getName())) {
				return false;
			}
		}
		return true;
	}

	boolean isUserInRoom(User user) {
		if (users.contains(user)) {
			return true;
		}

		return false;
	}

	public void sendToAll(String message) {
		for (User u : users) {
			u.receiveMessage(message);
		}

	}

}
