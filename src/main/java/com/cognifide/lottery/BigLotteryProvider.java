package com.cognifide.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BigLotteryProvider implements LotteryProvider {

	private List<Integer> results;

	private Random random;

	private boolean active;

	public BigLotteryProvider() {
		results = Collections.synchronizedList(new ArrayList<Integer>());
		random = new Random();
		active = true;
	}

	public LotteryResult getResults() {
		List<Integer> response = results;
		results = Collections.synchronizedList(new ArrayList<Integer>());
		return new LotteryResult(response);
	}

	@Override
	public List<Integer> call() throws Exception {
		while (active) {
			final int lotteryValue = random.nextInt(10000);
			if (lotteryValue == 0) {
				active = false;
			}
			results.add(lotteryValue);
			Thread.sleep(1);
		}
		return results;
	}

}
