package com.cognifide.lottery;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LotteryEngine implements LotteryObserver {

	private ExecutorService executorService;

	public LotteryEngine() {
		executorService = Executors.newSingleThreadExecutor();
	}

	public void start(LotteryProviderType lotteryType) {
		final LotteryProvider provider = LotteryProviderFactory.create(lotteryType);
		provider.register(this);

		try {
			executorService.submit(provider).get();
		} catch (InterruptedException | ExecutionException e) {
		}
	}

	public void stop() {
		executorService.shutdownNow();
	}

	private void calculateLotteryWithUniqueNumbers(LotteryResult lotteryResult) {
		Set<Integer> lotteryNumbers = new HashSet(lotteryResult.lotteryNumbers);
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
		Set<Integer> sortedNumbers = new TreeSet(lotteryResult.lotteryNumbers);
		if (!sortedNumbers.isEmpty()) {
			System.out.println("Min number is :" + sortedNumbers.iterator().next());
		}
	}

	@Override
	public void notify(LotteryResult lotteryResult) {
		calculateLotteryWithUniqueNumbers(lotteryResult);
		calculateLotteryWithFirstElement(lotteryResult);
	}

}
