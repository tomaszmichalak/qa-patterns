package com.cognifide.lottery;

public class App {

	static LotteryEngine lotteryEngine;

	public static void main(String[] args) {
		lotteryEngine = new LotteryEngine();
		try {
			final LotteryProviderType lotteryType = LotteryProviderType.valueOf(args[0]);
			lotteryEngine.start(lotteryType);
		} finally {
			lotteryEngine.stop();
		}
	}

}
