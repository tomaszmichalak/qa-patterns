package com.cognifide.lottery;

import java.util.HashSet;
import java.util.Set;

public class PrimesResultProcessorStrategy implements ResultProcessorStrategy {

	@Override
	public void execute(LotteryResult lotteryResult) {
		Set<Integer> lotteryNumbers = new HashSet(lotteryResult.getLotteryNumbers());
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

}
