package io.github.code13.reactive;

import java.util.ArrayList;
import java.util.List;


/**
 * 单元格
 *
 * @author Code13
 * @date 2020-11-18 10:21
 */
public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {

  private int value = 0;

  private String name;

  private List<Subscriber<? super Integer>> subscriberList = new ArrayList<>();

  public SimpleCell(String name) {
    this.name = name;
  }

  @Override
  public void subscribe(Subscriber<? super Integer> subscriber) {
    subscriberList.add(subscriber);
  }

  @Override
  public void onNext(Integer item) {
    value = item;
    System.out.println(name + ":" + value);
    notifyAllSubscribers();
  }

  private void notifyAllSubscribers() {
    subscriberList.forEach(subscriber -> subscriber.onNext(value));
  }

}

class ArithmeticCell extends SimpleCell {

  private int left;

  private int right;

  public ArithmeticCell(String name) {
    super(name);
  }

  public void setLeft(int left) {
    this.left = left;
    onNext(left + right);
  }

  public void setRight(int right) {
    this.right = right;
    onNext(right + left);
  }

}
