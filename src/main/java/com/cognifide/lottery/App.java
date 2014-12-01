package com.cognifide.lottery;

public class App {

	public static void main(String[] args) {
		LotteryEngine lotteryEngine = new LotteryEngine();
		try {
			final LotteryProviderType lotteryType = LotteryProviderType.valueOf(args[0]);
			lotteryEngine.start(lotteryType);
		} finally {
			lotteryEngine.stop();
		}
	}

}
