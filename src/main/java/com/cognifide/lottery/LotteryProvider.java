package com.cognifide.lottery;

import java.util.List;
import java.util.concurrent.Callable;

public interface LotteryProvider extends Callable<List<Integer>> {

	LotteryResult getResults();

}
