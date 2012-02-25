package net.wschat;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {

	private final List<User> users = new ArrayList<>();

	public void enter(User newUser) {
		if (isUserNameFree(newUser)) {
			users.add(newUser);
			for (User u : users) {
				u.receiveMessage("user " + newUser.getName() + " entered the chatroom!");
			}
		}
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

}
