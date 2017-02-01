
import foo.bar.{Worker, WorkerPool}
import java.util.concurrent.{CountDownLatch, Executor, ExecutorService, Executors, ThreadFactory}
import scala.collection.mutable.ArrayBuffer

package object foo {

  import bar.Proxy

  val Executor: ExecutorService = {
    val threadFactory = new Proxy(
      new ThreadFactory {
        override def newThread(r: Runnable): Thread = {
          val t = new Thread(r)
          t.setDaemon(true)
          t
        }
      },
      Proxy.newProxy(
        () => println("pre"),
        () => println("post")
      )
    )
    Executors.newCachedThreadPool(threadFactory)
  }

  val Pool: WorkerPool = new WorkerPool(Executor)
}