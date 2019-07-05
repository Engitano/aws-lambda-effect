package com.engitano.lambdaeffect

import java.io.{InputStream, OutputStream}
import java.util.concurrent.Executors

import cats.implicits._
import cats.effect.implicits._
import cats.effect.{ConcurrentEffect, ContextShift, Resource, Sync}
import com.amazonaws.services.lambda.runtime.Context
import fs2.Pipe
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

abstract class StreamLambda[F[_]: ConcurrentEffect: ContextShift] {

  import StreamLambda._

  private val F = ConcurrentEffect[F]

  protected val errorLogger: (String, Option[Throwable]) => F[Unit] = Logger.error[F]

  protected def threadPool: Resource[F, ExecutionContext] =
    Resource(F.delay {
      val executor = Executors.newCachedThreadPool()
      val ec       = ExecutionContext.fromExecutor(executor)
      (ec, F.delay(executor.shutdown()))
    })

  protected def handle(c: Context): Pipe[F, Byte, Byte]

  private def handleCore(
      input: InputStream,
      output: OutputStream,
      context: Context
  ): F[Unit] =
    threadPool.use { ec =>
      _root_.fs2.io
        .readInputStream(F.delay(input), input.available(), ec)
        .through(handle(context))
        .through(_root_.fs2.io.writeOutputStream(F.delay(output), ec))
        .compile
        .drain
        .as(())
    }

  final def handle(
      input: InputStream,
      output: OutputStream,
      context: Context
  ): Unit =
    handleCore(input, output, context)
      .handleErrorWith { t =>
        errorLogger(
          s"Error while executing lambda: ${t.getMessage}",
          Some(t)
        ) *>
          F.raiseError(t)
      }
      .toIO
      .unsafeRunSync()
}

object StreamLambda {
  private val logger = LoggerFactory.getLogger(getClass)

  private object Logger {
    def debug[F[_]: Sync](msg: String) = Sync[F].delay(logger.debug(msg))
    def info[F[_]: Sync](msg: String)  = Sync[F].delay(logger.info(msg))
    def warn[F[_]: Sync](msg: String)  = Sync[F].delay(logger.warn(msg))
    def error[F[_]: Sync](msg: String, t: Option[Throwable]) =
      t.fold(Sync[F].delay(logger.error(msg)))(
        t => Sync[F].delay(logger.error(msg, t))
      )
  }
}