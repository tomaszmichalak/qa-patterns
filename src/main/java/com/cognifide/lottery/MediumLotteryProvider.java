package com.cognifide.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MediumLotteryProvider implements LotteryProvider {

	// List<Integer> results;
	private List<Integer> results;

	// Random random;
	private Random random;

	// boolean active;
	private boolean active;

	// String errors = "";
	private String errors = "";

	public MediumLotteryProvider() {
		results = Collections.synchronizedList(new ArrayList<Integer>());
		random = new Random();
		active = true;
	}

	@Override
	public List<Integer> call() throws Exception {
		return runSafe();
	}

	@Override
	public LotteryResult getResults() {
		List<Integer> response = results;
		results = Collections.synchronizedList(new ArrayList<Integer>());
		return new LotteryResult(response);
	}

	private List<Integer> runSafe() {
		while (active) {
			int lotteryValue = 0;
			try {
				lotteryValue = getLottery();
				Thread.sleep(1);
				if (lotteryValue == 0) {
					active = false;
				}
				results.add(lotteryValue);
				// } catch (Throwable e) {
			} catch (Exception e) {
				errors += "System fail with error " + e.getMessage() + "check lottery value [" + lotteryValue
						+ "]";
			}
		}
		return results;
	}

	private int getLottery() throws ArithmeticException {
		return random.nextInt(1000);
	}

}
