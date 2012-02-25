package net.wschat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ChatConnectionManagerTest {
	private ChatConnectionManager mgr;

	@Before
	public void setUp() {
		mgr = new ChatConnectionManager();
	}

	@Test
	public void shouldAddConnection_One() {
		mgr.addConnection(new ChatConnection());
		assertEquals(1, mgr.getConnectionCount());
	}

	@Test
	public void shouldAddConnection_Many() {
		mgr.addConnection(new ChatConnection());
		mgr.addConnection(new ChatConnection());
		mgr.addConnection(new ChatConnection());
		assertEquals(3, mgr.getConnectionCount());
	}

	@Test
	public void shouldRemoveConnection() {
		ChatConnection con = new ChatConnection();

		mgr.addConnection(con);
		assertEquals(1, mgr.getConnectionCount());

		mgr.removeConnection(con);
		assertEquals(0, mgr.getConnectionCount());
	}

	@Test
	public void shouldSendMessageToAll() {
		final String msg = "Hi there!";

		ChatConnection con1 = mock(ChatConnection.class);
		ChatConnection con2 = mock(ChatConnection.class);

		mgr.addConnection(con1);
		mgr.addConnection(con2);

		mgr.sendToAll(msg);

		// verfiy message was sent
		verify(con1).sendMessage(msg);
		verify(con2).sendMessage(msg);
	}
}
