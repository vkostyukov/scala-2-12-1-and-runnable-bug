package foo.bar

abstract class Worker extends Runnable {
  def run(): Unit = println("worker run")
}
