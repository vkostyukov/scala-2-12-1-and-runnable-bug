package foo.bar

import java.util.concurrent.ThreadFactory

object Proxy {
  def newProxy(preRun: () => Unit, postRun: () => Unit): Runnable => Runnable = { r: Runnable =>
    new Runnable {
      def run(): Unit = {
        preRun()
        try r.run()
        finally postRun()
      }
    }
  }
}

class Proxy(tf: ThreadFactory, newRunnable: Runnable => Runnable) extends ThreadFactory {
  def newThread(r: Runnable): Thread = {
    val newR = newRunnable(r)
    tf.newThread(newR)
  }
}
