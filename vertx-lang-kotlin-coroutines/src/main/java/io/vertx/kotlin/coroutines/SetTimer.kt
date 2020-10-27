package io.vertx.kotlin.coroutines

import io.vertx.core.Vertx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Set a one-shot timer to fire after {@code delay} milliseconds, at which point a suspending function will be invoked
 *
 * @param delay  the delay in milliseconds, after which the timer will fire
 * @param handler  the handler that will be called with the timer ID when the timer fires
 * @return the unique ID of the timer
 */
fun Vertx.setTimerAwait(delay: Long, handler: suspend (Long) -> Unit): Long {
  return this.setTimer(delay) { timerId ->
    CoroutineScope(Dispatchers.Default).launch {
      handler(timerId)
    }
  }
}
