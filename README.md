A minimal reproducer Finagle's bug with Scala 2.12 (see https://github.com/twitter/finagle/issues/581).

This dead-locks with Scala 2.12.1 but works fine with 2.11.7.

To reproduce:

1) clone the repo
2) `cd scala-2-12-1-and-runnable-bug`
3) `sbt ++2.11.7 run` (program finishes)
4) `sbt ++2.12.1 run` (program dead-locks)
