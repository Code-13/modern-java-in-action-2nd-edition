package io.github.code13.reactive;

import org.junit.Test;

/**
 * @author Code13
 * @date 2020-11-18 10:36
 */
public class SimpleCellTest {

  @Test
  public void test1() {
    var c1 = new SimpleCell("C1");
    var c2 = new SimpleCell("c2");
    var c3 = new SimpleCell("c3");

    c1.subscribe(c3);

    c1.onNext(10);
    c2.onNext(20);
  }

  @Test
  public void test2() {
    var c3 = new ArithmeticCell("c3");
    var c1 = new SimpleCell("C1");
    var c2 = new SimpleCell("c2");

    c1.subscribe(c3::setLeft);
    c2.subscribe(c3::setRight);

    c1.onNext(10);
    c2.onNext(20);
    c1.onNext(15);
  }

  @Test
  public void test3() {
    var c5 = new ArithmeticCell("c5");
    var c3 = new ArithmeticCell("c3");
    var c1 = new SimpleCell("C1");
    var c2 = new SimpleCell("c2");
    var c4 = new SimpleCell("c4");

    c1.subscribe(c3::setLeft);
    c2.subscribe(c3::setRight);

    c3.subscribe(c5::setLeft);
    c4.subscribe(c5::setRight);

    c1.onNext(10);
    c2.onNext(20);
    c1.onNext(15);
    c4.onNext(1);
    c4.onNext(3);
  }

}