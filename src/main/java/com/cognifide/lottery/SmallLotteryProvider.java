package com.cognifide.lottery;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class SmallLotteryProvider implements LotteryProvider {

	protected Vector results; // vector?

	private Random random;

	private boolean active;

	public SmallLotteryProvider() {
		results = new Vector();
		random = new Random();
		active = true;
	}

	public LotteryResult getResults() {
		List<Integer> response = results;
		results = new Vector();
		return new LotteryResult(response);
	}

	@Override
	public List<Integer> call() throws Exception {
		while (active) {
			final int lotteryValue = random.nextInt(100);
			if (lotteryValue == 0) {
				active = false;
			}
			results.add(lotteryValue);
			Thread.sleep(1);
		}
		return results;
	}

}
