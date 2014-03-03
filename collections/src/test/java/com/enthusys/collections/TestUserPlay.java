package com.enthusys.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class TestUserPlay {
	private static final Logger logger = LoggerFactory.getLogger(TestUserPlay.class);
	private static final int TOTAL_USERS = 100000;

	@Test
	public void testLoadUsers() {
		UserPlay userPlay = new UserPlay();
		logger.info("*** total users: {}", userPlay.getNumberOfUsers());
		assertEquals("expect exact number of users", TOTAL_USERS, userPlay.getNumberOfUsers());

		User testUser = userPlay.getUserByNumberViaList(50000 + 49945);
		logger.info("*** {}", testUser);
		assertEquals("name must match", "Lang", testUser.getSurname());
		assertEquals("username must match", "Theirequal", testUser.getUsername());

		testUser = userPlay.getUserByNumberViaList(2006);
		logger.info("*** {}", testUser);
		assertEquals("name must match", "Shcherbakov", testUser.getSurname());
		assertEquals("username must match", "Tatelly1963", testUser.getUsername());
	}

	@Test
	public void testRandomNames() {
		Random random = new Random();
		UserPlay userPlay = new UserPlay();

		logger.info("*** testRandomNames()");

		long lStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			User user1 = userPlay.getUserByNumberViaList((random.nextInt(Integer.MAX_VALUE)) % TOTAL_USERS);
		}
		logger.info("*** Access via List:{}", System.currentTimeMillis() - lStartTime);

		lStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			User user1 = userPlay.getUserByNumberViaList((random.nextInt(Integer.MAX_VALUE)) % TOTAL_USERS);
		}
		logger.info("*** Access via List:{}", System.currentTimeMillis() - lStartTime);

		lStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			User user1 = userPlay.getUserByNumberViaList((random.nextInt(Integer.MAX_VALUE)) % TOTAL_USERS);
		}
		logger.info("*** Access via List:{}", System.currentTimeMillis() - lStartTime);

		lStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			User user1 = userPlay.getUserByNumberViaMap((random.nextInt(Integer.MAX_VALUE)) % TOTAL_USERS);
		}
		logger.info("*** Access via Map:{}", System.currentTimeMillis() - lStartTime);

		lStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			User user1 = userPlay.getUserByNumberViaMap((random.nextInt(Integer.MAX_VALUE)) % TOTAL_USERS);
		}
		logger.info("*** Access via Map:{}", System.currentTimeMillis() - lStartTime);

		lStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			User user1 = userPlay.getUserByNumberViaMap((random.nextInt(Integer.MAX_VALUE)) % TOTAL_USERS);
		}
		logger.info("*** Access via Map:{}", System.currentTimeMillis() - lStartTime);


	}

	@Test
	public void testAccessUsers() {
		long lStartTime = System.currentTimeMillis();
		UserPlay userPlay = new UserPlay();
		logger.info("*** total users:{}", userPlay.getNumberOfUsers());
		logger.info("*** time to load:{}", System.currentTimeMillis() - lStartTime);

		// iterate over Map
		lStartTime = System.currentTimeMillis();
		Map<Integer, User> userMap = userPlay.getUserMap();
		for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
			entry.getKey();
			entry.getValue();
		}
		logger.info("*** time to iterate map:{}", System.currentTimeMillis() - lStartTime);

		// iterate over TreeSet
		lStartTime = System.currentTimeMillis();
		Set<User> userSet = userPlay.getUserTreeSet();
		assertEquals("expect exact number of users", 100000, userSet.size());

		for (User u : userSet) {
//			logger.debug("*** {}", u);
		}
		logger.info("*** time to iterate TreeSet:{}", System.currentTimeMillis() - lStartTime);
	}
}
