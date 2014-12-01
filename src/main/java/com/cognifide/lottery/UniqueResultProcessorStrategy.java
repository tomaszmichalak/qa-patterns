package com.cognifide.lottery;

import java.util.Set;
import java.util.TreeSet;

public class UniqueResultProcessorStrategy implements ResultProcessorStrategy {

	@Override
	public void execute(LotteryResult lotteryResult) {
		Set<Integer> sortedNumbers = new TreeSet(lotteryResult.getLotteryNumbers());
		if (!sortedNumbers.isEmpty()) {
			System.out.println("Min number is :" + sortedNumbers.iterator().next());
		}
	}

}
