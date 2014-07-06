package org.eigengo.mirror

import java.io.File
import java.util.Collections

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.util.store.MemoryDataStoreFactory
import com.typesafe.config.ConfigFactory

object Authorisation {
  import org.eigengo.mirror.ClientComponents._

  private final val GLASS_SCOPE: String =
    "https://www.googleapis.com/auth/glass.timeline " +
    "https://www.googleapis.com/auth/glass.location " +
    "https://www.googleapis.com/auth/userinfo.profile"
  private final val (clientId, clientSecret) = {
    val config = ConfigFactory.parseFile(new File(System.getProperty("user.home"), ".google/mirror"))
    (config.getString("clientId"), config.getString("clientSecret"))
  }
  private final val dataStoreFactory = new MemoryDataStoreFactory()

  def newAuthorizationCodeFlow(): AuthorizationCodeFlow = {
    new GoogleAuthorizationCodeFlow.Builder(transport, jsonFactory, clientId, clientSecret, Collections.singleton(GLASS_SCOPE)).
      setAccessType("offline").
      setDataStoreFactory(dataStoreFactory).
      build()
  }
}
