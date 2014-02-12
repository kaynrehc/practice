package com.enthusys.play.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: mchernyak
 * Date: 1/25/14
 * Time: 2:39 PM
 */
public class Stack {
	private static final Logger log = LoggerFactory.getLogger(Stack.class);

	List<Integer> stackData = null;
	List<Integer> maxStackData = null;

	int topIndex = -1;
	int maxTopIndex = -1;

	public Stack() {
		stackData = new ArrayList<Integer>(10);
		maxStackData = new ArrayList<Integer>(10);
	}

	@Override
	public String toString() {
		return "" + stackData;
	}

	public synchronized void push(Integer n) {
		stackData.add(n);
		topIndex++;

		if (maxTopIndex > -1) {
			if (n.compareTo(maxStackData.get(maxTopIndex)) <= 0) {
				return;
			}
		}

		maxStackData.add(n);
		maxTopIndex++;
	}

	public boolean isEmpty() {
		return (topIndex == -1);
	}

	public synchronized Integer pop() {
		Integer rv = null;

		if (!isEmpty()) {
			rv = stackData.remove(topIndex--);
		}

		Integer currentMax = maxStackData.get(maxTopIndex);

		if (currentMax.equals(rv)) {
			maxStackData.remove(maxTopIndex--);
		}

		return rv;
	}

	public Integer maxValue() {
		Integer rv = null;

		if ( maxTopIndex > -1) {
			rv = maxStackData.get(maxTopIndex);
		}

		return rv;
	}

	public static void main(String[] args) {
		Random random = new Random();
		Stack integerStack = new Stack();

		for (int i = 0; i < 12; i++) {
			integerStack.push(random.nextInt(100));
		}

		log.info("***** {}......{}", integerStack.toString(), integerStack.maxStackData);

		while (!integerStack.isEmpty()) {
			log.info("***** {}.....{}", integerStack.pop(), integerStack.maxValue());
		}
	}
}
