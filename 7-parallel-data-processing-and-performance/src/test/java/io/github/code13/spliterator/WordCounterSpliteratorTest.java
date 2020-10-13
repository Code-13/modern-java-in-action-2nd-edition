package io.github.code13.spliterator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Code13
 * @date 2020-10-13 17:22
 */
public class WordCounterSpliteratorTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(WordCounterSpliteratorTest.class);

  static final String SENTENCE =
    " Nel mezzo del cammin di nostra vita " +
      "mi ritrovai in una selva oscura" +
      " ché la dritta via era smarrita ";

  @Test
  public void test() {
    final WordCounterSpliterator wordCounterSpliterator = new WordCounterSpliterator(SENTENCE);
    final Stream<Character> stream = StreamSupport.stream(wordCounterSpliterator, true);
    final WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
      WordCounter::accumulate,
      WordCounter::combine);

    LOGGER.info("字符计算: {}", wordCounter.getCounter());
    
  }


}
