package net.wschat.engine;

import net.wschat.chat.ChatRoom;
import net.wschat.chat.User;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Manages the "housekeeping" of connections (i.e. connect and close), as well
 * as message distribution.
 * 
 * @author achim, 25.02.2012
 */
public class ChatConnectionManager implements ConnectionListener {
	private final ChatRoom chatRoom = new ChatRoom();

	@Override
	public void onMessage(ChatConnection conn, String msg) {

		try {
			if (isJoinCommand(msg)) {
				User user = new User(conn, new JSONObject(msg).getString("data"));
				chatRoom.enter(user);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO implement
		if (isMessageCommand(msg)) {
			chatRoom.sendToAll(msg);
		}
	}

	private boolean isMessageCommand(String msg) {
		return false;
	}

	boolean isJoinCommand(String msg) throws JSONException {
		JSONObject jSon = new JSONObject(msg);

		return "enter".equals(jSon.getString("type"));
	}

	@Override
	public void onOpen(ChatConnection conn) {

	}

	@Override
	public void onClose(ChatConnection conn) {

	}
}
