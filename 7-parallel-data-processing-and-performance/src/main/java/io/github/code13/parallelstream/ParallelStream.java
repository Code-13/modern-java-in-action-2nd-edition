package io.github.code13.parallelstream;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流
 *
 * @author Code13
 * @date 2020-10-13 13:37
 */
public class ParallelStream {

  public long parallelSum(long n) {
    return Stream.iterate(1L, aLong -> aLong + 1)
      .limit(n)
      .parallel() // 将顺序流转换为并行流
      .reduce(0L, Long::sum);
  }

  public long rangedSum(long n) {
    return LongStream.rangeClosed(1, n)
      .reduce(0L, Long::sum);
  }

}
