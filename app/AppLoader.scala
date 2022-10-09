package loader

import modules.TemplateModule
import akka.actor.ActorSystem
import akka.stream._
import com.softwaremill.macwire.wire
import play.api.ApplicationLoader.Context
import play.api.{ApplicationLoader, BuiltInComponentsFromContext}
import play.api.routing.Router
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import play.filters.HttpFiltersComponents
import play.shaded.ahc.org.asynchttpclient.{DefaultAsyncHttpClient, DefaultAsyncHttpClientConfig}
import router.Routes

import scala.concurrent.ExecutionContext

class AppLoader extends ApplicationLoader {

  override def load(context: Context) = new AppComponents(context).application

}

class AppComponents(
  context: Context
) extends BuiltInComponentsFromContext(context)
    with TemplateModule
    with HttpFiltersComponents {

  implicit lazy val ec: ExecutionContext = actorSystem.dispatcher

  implicit lazy val wsClient = {
    implicit val materializer = SystemMaterializer(actorSystem).materializer

    val asyncHttpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
      .setMaxRequestRetry(0)
      .setShutdownQuietPeriod(0)
      .setShutdownTimeout(0)
      .build

    lazy val asyncHttpClient: DefaultAsyncHttpClient = wire[DefaultAsyncHttpClient]

    wire[StandaloneAhcWSClient]
  }

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

}
