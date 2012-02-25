package net.wschat.engine;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.wschat.chat.MessageSender;

import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

/**
 * Represents a connection from a chat-client.
 * 
 * @author achim, 25.02.2012
 */
class ChatConnection implements OnTextMessage, MessageSender {
	private List<ConnectionListener> messageListeners = new CopyOnWriteArrayList<>();
	private Connection conn;

	@Override
	public void onOpen(Connection conn) {
		this.conn = conn;
		fireOpen();
	}

	@Override
	public void onClose(int closeCode, String msg) {
		fireClose();
		this.conn = null;
	}

	@Override
	public void onMessage(String msg) {
		fireMessage(msg);
	}

	@Override
	public void sendMessage(String msg) {
		try {
			conn.sendMessage(msg);
		} catch (IOException e) {
			close();
		}
	}

	public void addMessageListener(ConnectionListener l) {
		messageListeners.add(l);
	}

	public void removeMessageListener(ConnectionListener l) {
		messageListeners.remove(l);
	}

	private void fireMessage(String msg) {
		for (ConnectionListener l : messageListeners)
			l.onMessage(this, msg);
	}

	private void fireClose() {
		for (ConnectionListener l : messageListeners)
			l.onClose(this);
	}

	private void fireOpen() {
		for (ConnectionListener l : messageListeners)
			l.onOpen(this);
	}

	private void close() {
		conn.disconnect();
	}
}