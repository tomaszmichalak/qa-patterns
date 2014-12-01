package com.cognifide.lottery;

public class App {

	static LotteryEngine lotteryEngine;

	public static void main(String[] args) {
		lotteryEngine = new LotteryEngine();
		try {
			lotteryEngine.start(args[0]);
			lotteryEngine.stop();
		} catch (Exception e) {
			lotteryEngine.stop();
		}
	}

}
