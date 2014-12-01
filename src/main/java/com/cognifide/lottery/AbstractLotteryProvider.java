package com.cognifide.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class AbstractLotteryProvider implements LotteryProvider {

	public static final int LOTTERY_RETRY_COUNT = 10;

	private List<LotteryObserver> observers;

	private Random random;

	private boolean active;

	public AbstractLotteryProvider() {
		observers = new LinkedList<>();
		random = new Random();
		active = true;
	}

	protected abstract int getRangeNumber();

	public void notifyObservers(List<Integer> results) {
		LotteryResult lotteryResult = new LotteryResult(Collections.unmodifiableList(results));
		for (LotteryObserver observer : observers) {
			observer.notify(lotteryResult);
		}
	}

	@Override
	public List<Integer> call() throws Exception {
		List<Integer> results = new ArrayList();
		while (active) {
			results = new ArrayList();
			for (int i = 0; i < random.nextInt(LOTTERY_RETRY_COUNT); i++) {
				final int lotteryValue = random.nextInt(getRangeNumber());
				if (lotteryValue == 0) {
					active = false;
				}
				results.add(lotteryValue);
			}
			notifyObservers(results);
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
