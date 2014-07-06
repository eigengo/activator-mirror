package org.eigengo.mirror

import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory

object ClientComponents {
  val transport = new NetHttpTransport()
  val jsonFactory = new JacksonFactory()
}
