package io.github.code13.forkjoinpool;

import java.util.concurrent.RecursiveTask;

/**
 * @author Code13
 * @date 2020-10-13 15:23
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
  private static final long serialVersionUID = -3322738049676748590L;
  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ForkJoinSumCalculator.class);

  private static final long THRESHOLD = 10000L;

  private final long[] numbers;
  private final int start;
  private final int end;

  public ForkJoinSumCalculator(long[] numbers) {
    this(numbers, 0, numbers.length);
  }

  private ForkJoinSumCalculator(long[] numbers, int start, int end) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
  }

  @Override
  protected Long compute() {

    int length = end - start;

    // 如果小于或等于阈值，就计算结果
    if (length <= THRESHOLD) {
      LOGGER.info("阈值已过开始计算");
      return computeSequentially();
    }

    LOGGER.info("尚未超过阈值");

    // 创建子任务来为数组的前一半来求值
    final ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);

    // 利用 ForkJoinPool 的另一个线程异步地执行新建的子任务
    leftTask.fork();

    //创建一个子任务来为数组的后一半求和
    final ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);

    // 当前线程同步执行第二个子任务，有可能进行进一步的递归划分
    final Long rightResult = rightTask.compute();

    // 读取第一个子任务的结果，如果还未完成就等待
    final Long leftResult = leftTask.join();

    // 整合结果
    return rightResult + leftResult;
  }

  /**
   * 任务足够小时采用的简单算法
   *
   * @return {@link long} 计算结果
   */
  private long computeSequentially() {
    long sum = 0;
    for (int i = start; i < end; i++) {
      sum += numbers[i];
    }
    return sum;
  }

}
