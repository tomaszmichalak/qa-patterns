package com.cognifide.lottery;

import java.util.List;

public class LotteryResult {

	private List<Integer> lotteryNumbers;

	private long timestamp;

	LotteryResult(List<Integer> lotteryNumbers) {
		this.lotteryNumbers = lotteryNumbers;
		timestamp = System.currentTimeMillis();
	}

	public List<Integer> getLotteryNumbers() {
		return lotteryNumbers;
	}

	public long getTimestamp() {
		return timestamp;
	}
}
