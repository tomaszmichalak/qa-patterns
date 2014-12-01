package com.cognifide.lottery;

public enum LotteryProviderRange {

	SMALL(100), MEDIUM(1000), BIG(10000);

	private int range;

	private LotteryProviderRange(int range) {
		this.range = range;
	}

	public int getRange() {
		return range;
	}
}
