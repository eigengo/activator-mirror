Google Glass Mirror API app
===========================

To get started, create an App at [https://console.developers.google.com](https://console.developers.google.com), create OAuth credentials, and save them in ``~/.google/mirror`` HOCON file that follows this format:

```hocon
{
   clientId: "xyz.apps.googleusercontent.com"
   clientSecret: "12312312321asda-sdfuhsif"
}

```

Then run ``sbt run`` and head over to [http://localhost:8080/index.html](http://localhost:8080/index.html) and send messages to your timeline!
