package org.eigengo.mirror

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import spray.http.StatusCodes
import spray.routing.{Directives, Route}

object AuthorisationService extends Directives {

  def route: Route = {
    path("oauth2callback") {
      get {
        parameter('code) { code =>
          val flow = Authorisation.newAuthorizationCodeFlow()
          val tokenResponse = flow.newTokenRequest(code).setRedirectUri("http://localhost:8080/oauth2callback").execute()
          val subject = tokenResponse.asInstanceOf[GoogleTokenResponse].parseIdToken.getPayload.getSubject
          flow.createAndStoreCredential(tokenResponse, subject)

          redirect(s"http://localhost:8080/index.html#/?subject=$subject", StatusCodes.TemporaryRedirect)
        } ~ dynamic {
          val flow = Authorisation.newAuthorizationCodeFlow()
          val url = flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/oauth2callback")
          url.set("approval_prompt", "force")

          redirect(url.build(), StatusCodes.TemporaryRedirect)
        }
      }
    }
  }

}
