package com.cognifide.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class AbstractLotteryProvider implements LotteryProvider {

	private List<LotteryObserver> observers;

	private List<Integer> results;

	private Random random;

	private boolean active;

	public AbstractLotteryProvider() {
		results = Collections.synchronizedList(new ArrayList<Integer>());
		observers = new LinkedList<>();
		random = new Random();
		active = true;
	}

	protected abstract int getRangeNumber();

	private void notifyObservers() {
		List<Integer> response = results;
		results = Collections.synchronizedList(new ArrayList<Integer>());

		LotteryResult lotteryResult = new LotteryResult(Collections.unmodifiableList(response));
		for (LotteryObserver observer : observers) {
			observer.notify(lotteryResult);
		}
	}

	@Override
	public List<Integer> call() throws Exception {
		while (active) {
			final int lotteryValue = random.nextInt(getRangeNumber());
			if (lotteryValue == 0) {
				active = false;
			}
			results.add(lotteryValue);
			notifyObservers();
			Thread.sleep(1);
		}
		return results;
	}

	@Override
	public boolean register(LotteryObserver observer) {
		return observers.add(observer);
	}

	@Override
	public boolean remove(LotteryObserver observer) {
		return observers.remove(observer);
	}

}
