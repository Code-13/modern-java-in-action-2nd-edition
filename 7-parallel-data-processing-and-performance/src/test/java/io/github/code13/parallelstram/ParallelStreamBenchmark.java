package io.github.code13.parallelstram;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 测量流性能
 *
 * @author Code13
 * @date 2020-10-13 14:18
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {

  private static final long N = 10000000L;

  @Benchmark
  public long sequentialSum() {
    return Stream.iterate(1L, aLong -> aLong + 1)
      .limit(N)
      .reduce(0L, Long::sum);
  }

  @Benchmark
  public long parallelSum() {
    return Stream.iterate(1L, aLong -> aLong + 1)
      .limit(N)
      .parallel()
      .reduce(0L, Long::sum);
  }

  @Benchmark
  public long rangedSum() {
    return LongStream.rangeClosed(1, N)
      .reduce(0L, Long::sum);
  }

  @Benchmark
  public long rangedParallelSum() {
    return LongStream.rangeClosed(1, N)
      .parallel()
      .reduce(0L, Long::sum);
  }

  @TearDown(Level.Invocation)
  public void tearDown() {
    System.gc();
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(ParallelStreamBenchmark.class.getSimpleName())
      .result("result.json")
      .resultFormat(ResultFormatType.JSON).build();
    new Runner(opt).run();

    //Benchmark                                  Mode  Cnt   Score    Error  Units
    //ParallelStreamBenchmark.parallelSum        avgt   10  92.903 ± 10.458  ms/op
    //ParallelStreamBenchmark.rangedParallelSum  avgt   10   2.712 ±  3.157  ms/op
    //ParallelStreamBenchmark.rangedSum          avgt   10   7.051 ±  0.041  ms/op
    //ParallelStreamBenchmark.sequentialSum      avgt   10  89.648 ±  0.583  ms/op
  }

}
