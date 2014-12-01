package com.cognifide.lottery;

public class MediumLotteryProvider extends AbstractLotteryProvider {

	@Override
	protected int getRangeNumber() {
		return LotteryProviderRange.MEDIUM.getRange();
	}
}
