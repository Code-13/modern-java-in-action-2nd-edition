package io.github.code13.reactive;

/**
 * @author Code13
 * @date 2020-11-18 10:38
 */
public interface Publisher<T> {

  void subscribe(Subscriber<? super T> subscriber);

}
