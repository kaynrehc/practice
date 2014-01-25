package com.enthusys.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserPlay {
	private static final Logger logger = LoggerFactory.getLogger(UserPlay.class);
	private Map<Integer, User> userMap;
	private TreeSet<User> userTreeSet;
	private List<User> userList;

	public Map<Integer,User> getUserMap() {
		return userMap;
	}

	public TreeSet<User> getUserTreeSet() {
		return userTreeSet;
	}

	public UserPlay() {
		userMap = new HashMap<Integer, User>();
		userTreeSet = new TreeSet<User>();
		userList = new CopyOnWriteArrayList<>();

		loadUsersFromFile("/home/mchernyak/data/People1.csv");
		loadUsersFromFile("/home/mchernyak/data/People2.csv");
	}

	public int getNumberOfUsers() {
		return userList.size();
	}

	public User getUserByNumberViaList(Integer number) {
		return userList.get(number);
	}

	public User getUserByNumberViaMap(Integer number) {
		return userMap.get(number);
	}

	public void loadUsersFromFile(String csvFile) {
		BufferedReader br = null;
		String line = "";
		int baseNumber = -1;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			String[] t = line.split(",");
			User.setBaseNumber(Integer.parseInt(t[1]));

			while ((line = br.readLine()) != null) {
				User u = new User(line);
				userMap.put(u.getNumber(), u);
				userTreeSet.add(u);
				userList.add(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
