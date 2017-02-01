package foo.bar

import java.util.concurrent.{CountDownLatch, Executor}
import scala.collection.mutable.ArrayBuffer

class WorkerPool(e: Executor) {
  private val startupLatch = new CountDownLatch(50)
  private val pool = new ArrayBuffer[Worker]()

  private class MyWorker(e: Executor) extends Worker {
    println("my worker run")
    e.execute(this)
    override def run(): Unit = { super.run(); startupLatch.countDown(); startupLatch.await() }
  }

  for (i <- 1 to 50) pool += new MyWorker(e)
git
  startupLatch.await()
}
