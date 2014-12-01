package com.cognifide.lottery;

public class BigLotteryProvider extends AbstractLotteryProvider {

	@Override
	protected int getRangeNumber() {
		return LotteryProviderRange.BIG.getRange();
	}

}
