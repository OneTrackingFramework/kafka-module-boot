/**
 *
 */
package one.tracking.framework.kafka.consumer.handler;

/**
 * @author Marko Voß
 *
 */
public interface IEventHandler<T> {

  void consume(T event) throws Exception;
}
