package net.wschat;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.websocket.WebSocketHandler;

/**
 * Starts embedded jetty which runs the chat-server.
 * 
 * @author achim, 25.02.2012
 */
public class Main {
	public static void main(String[] args) throws Exception {
		final WebSocketHandler wsHandler = new ChatWebSocketHandler();
		wsHandler.setHandler(new DefaultHandler());

		final Server server = new Server(8080);
		server.setHandler(wsHandler);
		server.start();
		server.join();
	}
}
