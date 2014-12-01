package com.cognifide.lottery;

public class SmallLotteryProvider extends AbstractLotteryProvider {

	@Override
	protected int getRangeNumber() {
		return LotteryProviderRange.SMALL.getRange();
	}

}
