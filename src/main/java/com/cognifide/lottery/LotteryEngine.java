package com.cognifide.lottery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LotteryEngine {

	ExecutorService executorService;

	public void start(String lotteryType) {

		executorService = Executors.newSingleThreadExecutor();
		final LotteryProvider provider;

		if (lotteryType.equals("SMALL")) {
			provider = new SmallLotteryProvider();
		} else if (lotteryType.equals("MEDIUM")) {
			provider = new MediumLotteryProvider();
		} else if (lotteryType.equals("BIG")) {
			provider = new BigLotteryProvider();
		} else {
			throw new IllegalArgumentException();
		}

		final Future<List<Integer>> submit = executorService.submit(provider);

		while (!submit.isDone()) {
			LotteryResult results = provider.getResults();
			calculateLotteryWithUniqueNumbers(results);
			calculateLotteryWithFirstElement(results);
			try {
				synchronized (provider) {
					provider.wait(2);
				}
			} catch (InterruptedException e) {
			}
		}
	}

	public void stop() {
		executorService.shutdownNow();
	}

	private void calculateLotteryWithUniqueNumbers(LotteryResult lotteryResult) {
		Set<Integer> lotteryNumbers = new HashSet<Integer>(lotteryResult.lotteryNumbers);
		for (Integer lotteryNumber : lotteryNumbers) {
			if (isUniqueAcceptable(lotteryNumber)) {
				System.out.println("Unique number found: " + lotteryNumber);
			}
		}
	}

	private boolean isUniqueAcceptable(Integer number) {
		if (number > 2) {
			double sq = Math.sqrt(number);
			if (number % 2 == 0) {
				return false;
			} else {
				for (int i = 3; i <= sq; i += 2) {
					if (number % i == 0) {
						return false;
					}
				}
				return true;
			}
		} else if (number == 2) {
			return true;
		}
		return false;
	}

	private void calculateLotteryWithFirstElement(LotteryResult lotteryResult) {
		Set<Integer> sortedNumbers = new TreeSet<Integer>(lotteryResult.lotteryNumbers);
		if (!sortedNumbers.isEmpty()) {
			System.out.println("Min number is :" + sortedNumbers.iterator().next());
		}
	}

}
