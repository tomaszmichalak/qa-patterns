package com.cognifide.lottery;

public class LotteryProviderFactory {

	public static LotteryProvider create(LotteryProviderType type) {
		LotteryProvider provider = null;
		switch (type) {
			case SMALL:
				provider = new SmallLotteryProvider();
				break;
			case MEDIUM:
				provider = new MediumLotteryProvider();
				break;
			case BIG:
				provider = new BigLotteryProvider();
				break;
			default:
				new UnsupportedOperationException("This provider is not permitted");
		}
		return provider;
	}

}
