package com.cognifide.lottery;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LotteryEngine implements LotteryObserver {

	private ExecutorService executorService;

	private ResultProcessorStrategy primesProcessorStrategy;

	private ResultProcessorStrategy uniqueProcessorStrategy;

	public LotteryEngine() {
		executorService = Executors.newSingleThreadExecutor();
		primesProcessorStrategy = new PrimesResultProcessorStrategy();
		uniqueProcessorStrategy = new UniqueResultProcessorStrategy();
	}

	@Override
	public void notify(LotteryResult lotteryResult) {
		final ResultProcessorStrategy strategy;
		if (lotteryResult.getLotteryNumbers().size() > 2) {
			strategy = primesProcessorStrategy;
		} else {
			strategy = uniqueProcessorStrategy;
		}
		strategy.execute(lotteryResult);
	}

	public void start(LotteryProviderType lotteryType) {
		final LotteryProvider provider = LotteryProviderFactory.create(lotteryType);
		provider.register(this);

		try {
			executorService.submit(provider).get();
		} catch (InterruptedException | ExecutionException e) {
		}
	}

	public void stop() {
		executorService.shutdownNow();
	}

}
