package loader

import modules.TemplateModule
import com.softwaremill.macwire.wire
import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext, Logger, LoggerConfigurator}
import play.api.routing.Router
import play.api.libs.ws.ahc.AhcWSComponents
import play.filters.HttpFiltersComponents
import router.Routes

import scala.concurrent.ExecutionContext

class AppLoader extends ApplicationLoader {

  override def load(context: Context): Application = {
    LoggerConfigurator(context.environment.classLoader).foreach {
      _.configure(context.environment, context.initialConfiguration, Map.empty)
    }
    new AppComponents(context).application
  }

}

class AppComponents(
  context: Context
) extends BuiltInComponentsFromContext(context)
    with TemplateModule
    with AhcWSComponents
    with HttpFiltersComponents {

  implicit lazy val ec: ExecutionContext = actorSystem.dispatcher

  implicit lazy val logger: Logger = Logger("application")

  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }

}
