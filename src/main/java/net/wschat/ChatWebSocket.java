package net.wschat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

class ChatWebSocket implements OnTextMessage {
	private List<MessageListener> messageListeners = new CopyOnWriteArrayList<>();
	private Connection conn;

	@Override
	public void onClose(int closeCode, String msg) {
		fireClose();
	}

	@Override
	public void onOpen(Connection conn) {
		this.conn = conn;
		fireOpen();
	}

	@Override
	public void onMessage(String msg) {
		fireMessage(msg);
	}

	public void sendMessage(String msg) {
		try {
			conn.sendMessage(msg);
		} catch (IOException e) {
			close();
		}
	}

	public void addMessageListener(MessageListener l) {
		messageListeners.add(l);
	}

	public void removeMessageListener(MessageListener l) {
		messageListeners.remove(l);
	}

	private void fireMessage(String msg) {
		for (MessageListener l : messageListeners)
			l.onMessage(msg);
	}

	private void fireClose() {
		for (MessageListener l : messageListeners)
			l.onClose(this);
	}

	private void fireOpen() {
		for (MessageListener l : messageListeners)
			l.onOpen(this);
	}

	private void close() {
		conn.disconnect();
	}
}