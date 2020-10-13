package io.github.code13.forkjoinpool;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author Code13
 * @date 2020-10-13 16:03
 */
public class ForkJoinSumCalculatorTest {

  private static final long THRESHOLD = 10000000L;

  @Test
  public void forkJoinSum() {
    final long[] longs = LongStream.rangeClosed(1L, THRESHOLD).toArray();
    final ForkJoinTask<Long> forkJoinSumCalculator = new ForkJoinSumCalculator(longs);
    final Long invoke = ForkJoinPool.commonPool().invoke(forkJoinSumCalculator);
    System.out.println(invoke);
  }

}
