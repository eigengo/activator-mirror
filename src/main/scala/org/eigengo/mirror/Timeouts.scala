package org.eigengo.mirror

import java.util.concurrent.TimeUnit

import akka.util.Timeout

object Timeouts {

  implicit val internalTimeout = Timeout(5000, TimeUnit.MILLISECONDS)

}
