package io.github.code13.parallelstram;

import io.github.code13.parallelstream.ParallelStream;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 并行流测试
 *
 * @author Code13
 * @date 2020-10-13 13:43
 */
public class ParallelStreamTest {

  private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ParallelStreamTest.class);

  private ParallelStream parallelStream;

  @Before
  public void before() {
    parallelStream = new ParallelStream();
  }

  @Test
  public void parallelSum() {
    int n = 10;
    long start = System.currentTimeMillis();
    final long l = parallelStream.parallelSum(n);
    long end = System.currentTimeMillis();
    LOGGER.info("耗时为: {}", end - start);
    LOGGER.info("结果为:{}", l);
    assertTrue(true);
  }

}
