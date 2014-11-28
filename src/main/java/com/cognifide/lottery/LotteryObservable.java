package com.cognifide.lottery;

public interface LotteryObservable {

	boolean register(LotteryObserver observer);

	boolean remove(LotteryObserver observer);

}
