package net.wschat.engine;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Test;

public class ChatConnectionManagerTest {

	@Test
	public void shouldRecognizeJoinCommand() throws JSONException {
		String jSonDummyMessage = "{\"type\":\"enter\",\"data\":\"dummy1\"}";
		ChatConnectionManager manager = new ChatConnectionManager();
		assertTrue(manager.isJoinCommand(jSonDummyMessage));
	}
}
