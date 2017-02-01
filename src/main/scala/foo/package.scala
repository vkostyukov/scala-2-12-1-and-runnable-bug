
import foo.bar.{Simple, Worker, WorkerPool}
import java.util.concurrent.{CountDownLatch, Executor, ExecutorService, Executors}
import scala.collection.mutable.ArrayBuffer

package object foo {

  import bar.Proxy

  val Executor: ExecutorService = {
    val threadFactory = new Proxy(
      new Simple("foo/bar", makeDaemons = true),
      Proxy.newProxy(
        () => println("pre"),
        () => println("post")
      )
    )
    Executors.newCachedThreadPool(threadFactory)
  }

  val Pool: WorkerPool = new WorkerPool(Executor)
}