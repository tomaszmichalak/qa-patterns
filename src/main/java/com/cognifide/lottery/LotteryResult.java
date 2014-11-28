package com.cognifide.lottery;

import java.util.List;

public class LotteryResult {

//	public List<Integer> lotteryNumbers;

	private List<Integer> lotteryNumbers;

	public long timestamp; // value never assigned

	LotteryResult(List<Integer> lotteryNumbers) {
		this.lotteryNumbers = lotteryNumbers;
		this.timestamp = System.currentTimeMillis();
	}

	public List<Integer> getLotteryNumbers() {
		return lotteryNumbers;
	}

	public long getTimestamp() {
		return timestamp;
	}

}
