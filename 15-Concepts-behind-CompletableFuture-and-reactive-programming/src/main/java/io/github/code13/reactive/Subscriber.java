package io.github.code13.reactive;

/**
 * @author Code13
 * @date 2020-11-18 10:39
 */
public interface Subscriber<T> {

  void onNext(T t);

}
