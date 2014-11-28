package com.cognifide.lottery;

import java.util.List;

public class LotteryResult {

	public List<Integer> lotteryNumbers;

	public long timestamp;

	LotteryResult(List<Integer> lotteryNumbers) {
		this.lotteryNumbers = lotteryNumbers;
	}

}
