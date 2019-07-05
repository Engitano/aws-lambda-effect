package com.engitano.lambdaeffect.apigw

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import cats.effect.IO
import cats.syntax.option._
import com.amazonaws.services.lambda.runtime.Context
import com.engitano.lambdaeffect.apigw.Messages._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.ExecutionContext

class ProxyLambdaSpec extends WordSpec with Matchers with MockFactory {

  implicit val ec = ExecutionContext.global
  implicit val cs = IO.contextShift(ec)

  import ProxyMarshallers._

  "The ApiGatewayLambda" should {
    "return a valid JSON response" in {
      object sut extends ApiGatewayLambda[IO] with Dsl[IO] {

        override protected def handle(
            i: ProxyRequest,
            c: Context
        ): IO[ProxyResponse] =
          i.as[Input].map { ip =>
            ProxyResponse(
              200,
              Output(ip.name + "!").asJson.toString().some
            )
          }
      }

      val inputStream =
        new ByteArrayInputStream(Messages.requestWithInputName.getBytes)
      val output = new ByteArrayOutputStream()
      sut.handle(inputStream, output, mock[Context])
      val respJson = output.toString
      val response = decode[ProxyResponse](respJson)
      response should matchPattern {
        case Right(_) =>
      }
      val respBody = decode[Output](response.right.get.body.getOrElse(""))
      respBody.right.get.greeting shouldEqual "Hello Functional World!"

    }
  }
}
