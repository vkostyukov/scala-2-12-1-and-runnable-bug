package foo.bar

import java.util.concurrent.ThreadFactory

class Simple(name: String, makeDaemons: Boolean) extends ThreadFactory {
  def this(name: String) = this(name, false)

  def newThread(r: Runnable): Thread = {
    val thread = new Thread(r, name)
    thread.setDaemon(makeDaemons)
    if (thread.getPriority != Thread.NORM_PRIORITY) {
      thread.setPriority(Thread.NORM_PRIORITY)
    }
    thread
  }
}
