package io.github.code13.spliterator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

/**
 * @author Code13
 * @date 2020-10-13 17:04
 */
public class WordCounterTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(WordCounterTest.class);
  
  static final String SENTENCE =
    " Nel mezzo del cammin di nostra vita " +
      "mi ritrovai in una selva oscura" +
      " ché la dritta via era smarrita ";

  @Test
  public void test() {
    final WordCounter counter =
      IntStream.range(0, SENTENCE.length())
        .mapToObj(SENTENCE::charAt)
        .reduce(new WordCounter(0, true),
          WordCounter::accumulate,
          WordCounter::combine);

    LOGGER.info("结果：{}", counter.getCounter());

    assertTrue(true);
  }

}
