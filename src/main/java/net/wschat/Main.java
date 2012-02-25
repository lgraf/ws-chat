package net.wschat;

import org.eclipse.jetty.server.Server;

/**
 * 
 * @author achim, 25.02.2012
 */
public class Main {
	public static void main(String[] args) throws Exception {
//	    final WebSocketHandler wsHandler = new ChatWebSocketHandler();
//	    wsHandler.setHandler( new DefaultHandler() );

		final Server server = new Server( 8080 );
//	    server.setHandler( wsHandler );
		server.start();
		server.join();		
	}
}
