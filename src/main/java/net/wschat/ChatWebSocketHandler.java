package net.wschat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;
import org.eclipse.jetty.websocket.WebSocketHandler;

public class ChatWebSocketHandler extends WebSocketHandler {

	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		return new ChatWebSocket();
	}
}

class ChatWebSocket implements OnTextMessage {
	private Connection conn;

	@Override
	public void onClose(int closeCode, String msg) {
		System.out.println("ChatWebSocket.onClose()");
		close();
	}

	@Override
	public void onOpen(Connection conn) {
		System.out.println("ChatWebSocket.onOpen()");
		this.conn = conn;
	}

	@Override
	public void onMessage(String msg) {
		System.out.println("ChatWebSocket.onMessage(" + msg + ")");
		try {
			conn.sendMessage(msg);
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}

	}

	private void close() {
		conn.disconnect();
	}
}
