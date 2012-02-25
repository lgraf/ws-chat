package net.wschat;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages the "housekeeping" of connections (i.e. connect and close), as well
 * as message distribution.
 * 
 * @author achim, 25.02.2012
 */
public class ChatConnectionManager implements MessageListener {
	private final List<ChatConnection> connections = new CopyOnWriteArrayList<>();

	public int getConnectionCount() {
		return connections.size();
	}

	public ChatConnection addConnection(ChatConnection chatConnection) {
		connections.add(chatConnection);
		return chatConnection;
	}

	public ChatConnection removeConnection(ChatConnection chatConnection) {
		connections.remove(chatConnection);
		return chatConnection;
	}

	public void sendToAll(String string) {
		for (ChatConnection c : connections)
			c.sendMessage(string);
	}

	@Override
	public void onMessage(String msg) {
		for (ChatConnection c : connections)
			c.sendMessage(msg);
	}

	@Override
	public void onOpen(ChatConnection conn) {
		connections.add(conn);
	}

	@Override
	public void onClose(ChatConnection conn) {
		connections.remove(conn);
	}
}
