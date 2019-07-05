package com.engitano.lambdaeffect.apigw

import cats.effect.{ConcurrentEffect, ContextShift}
import com.engitano.lambdaeffect.EffectfulLambda
import io.circe.generic.auto._


abstract class ApiGatewayLambda[F[_]: ConcurrentEffect: ContextShift] extends EffectfulLambda[F, ProxyRequest, ProxyResponse]