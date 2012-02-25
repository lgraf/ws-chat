package net.wschat.chat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author eliyo, 25.02.2012
 */
public class ChatRoomTest {
	private final MessageSender MSG_SENDER = new TestMessageSender();
	private ChatRoom chatRoom;

	@Before
	public void setUp() {
		chatRoom = new ChatRoom();

	}

	@Test
	public void userShouldBeAbleToEnter() {
		User user = createUser("Dummy");
		chatRoom.enter(user);

		assertTrue(chatRoom.isUserInRoom(user));
	}

	@Test
	public void userShouldNotBeAbleToEnterIfSameUserName() {
		User user1 = createUser("Hans");
		User user2 = createUser("Hans");
		chatRoom.enter(user1);
		chatRoom.enter(user2);

		assertFalse(chatRoom.isUserInRoom(user2));
	}

	@Test
	public void allUsersShouldBeInformedIfNewUserEntersRoom() {
		User user1 = createUserMock("dummy1");
		User user2 = createUserMock("dummy2");

		chatRoom.enter(user1);
		chatRoom.enter(user2);

		User user3 = createUser("Dummy3");
		chatRoom.enter(user3);

		verify(user1).receiveMessage(chatRoom.createWelcomeMessage(user3));
		verify(user2).receiveMessage(chatRoom.createWelcomeMessage(user3));
	}

	@Test
	public void allUsersShouldReceiveAMessage() {
		User user1 = createUserMock("dummy1");
		User user2 = createUserMock("dummy2");

		chatRoom.enter(user1);
		chatRoom.enter(user2);
		final String message = "hello";
		chatRoom.sendToAll(message);

		verify(user1).receiveMessage(message);
		verify(user2).receiveMessage(message);
	}

	private User createUserMock(String userName) {
		User user = mock(User.class);
		when(user.getName()).thenReturn(userName);
		return user;
	}

	private User createUser(String userName) {
		return new User(MSG_SENDER, userName);
	}

	private static class TestMessageSender implements MessageSender {
		@Override
		public void sendMessage(String msg) {
		}
	}
}
