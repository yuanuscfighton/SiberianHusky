package com.laioffer.source.okhttp3.internal.concurrent

/**
 * A unit of work that can be executed one or more times.
 *
 * Recurrence
 * ----------
 *
 * Tasks control their recurrence schedule. The [runOnce] function returns -1L to signify that the
 * task should not be executed again. Otherwise it returns a delay until the next execution.
 *
 * A task has at most one next execution. If the same task instance is scheduled multiple times, the
 * earliest one wins. This applies to both executions scheduled with [TaskRunner.Queue.schedule] and
 * those implied by the returned execution delay.
 *
 * Cancellation
 * ------------
 *
 * Tasks may be canceled while they are waiting to be executed, or while they are executing.
 *
 * Canceling a task that is waiting to execute prevents that upcoming execution. Canceling a task
 * that is currently executing does not impact the ongoing run, but it does prevent a recurrence
 * from being scheduled.
 *
 * Tasks may opt-out of cancellation with `cancelable = false`. Such tasks will recur until they
 * decide not to by returning -1L.
 *
 * Task Queues
 * -----------
 *
 * Tasks are bound to the [TaskQueue] they are scheduled in. Each queue is sequential and the tasks
 * within it never execute concurrently. It is an error to use a task in multiple queues.
 */
abstract class Task(
  val name: String,
  val cancelable: Boolean = true
) {
  // Guarded by the TaskRunner.
  internal var queue: TaskQueue? = null

  /** Undefined unless this is in [TaskQueue.futureTasks]. */
  internal var nextExecuteNanoTime = -1L

  /** Returns the delay in nanoseconds until the next execution, or -1L to not reschedule. */
  abstract fun runOnce(): Long

  internal fun initQueue(queue: TaskQueue) {
    if (this.queue === queue) return

    check(this.queue === null) { "task is in multiple queues" }
    this.queue = queue
  }

  override fun toString() = name
}
