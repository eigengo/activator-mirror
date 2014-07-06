package org.eigengo.mirror

import com.google.api.client.auth.oauth2.Credential
import spray.routing.authentication.{Authentication, ContextAuthenticator}
import spray.routing.{AuthenticationFailedRejection, MissingHeaderRejection}

import scala.concurrent.{ExecutionContext, Future}

object Authentication {
  private val headerName = "Subject"

  def validCredential(implicit ec: ExecutionContext): ContextAuthenticator[Credential] = { ctx => {
      val header = ctx.request.headers.find(_.name == headerName).map(h => doAuth(h.value))
      Future(header.getOrElse(Left(MissingHeaderRejection(headerName))))
    }
  }

  private def doAuth(subject: String): Authentication[Credential] = {
    Option(Authorisation.newAuthorizationCodeFlow().loadCredential(subject))
      .map(Right(_)).getOrElse(Left(AuthenticationFailedRejection(AuthenticationFailedRejection.CredentialsRejected, Nil)))
  }

}
