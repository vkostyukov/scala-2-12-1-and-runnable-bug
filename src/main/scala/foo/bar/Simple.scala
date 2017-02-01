package foo.bar

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

class Simple(name: String, makeDaemons: Boolean) extends ThreadFactory {
  def this(name: String) = this(name, false)

  val group: ThreadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(), name)
  val threadNumber: AtomicInteger = new AtomicInteger(1)

  def newThread(r: Runnable): Thread = {
    val thread = new Thread(group, r, name + "-" + threadNumber.getAndIncrement())
    thread.setDaemon(makeDaemons)
    if (thread.getPriority != Thread.NORM_PRIORITY) {
      thread.setPriority(Thread.NORM_PRIORITY)
    }
    thread
  }
}
