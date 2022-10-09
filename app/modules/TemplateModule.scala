package modules

import clients.TemplateClient
import controllers.TemplateController
import services.{TemplateService, TemplateServiceImpl}
import com.softwaremill.macwire.wire
import play.api.mvc.ControllerComponents
import play.api.libs.ws.WSClient

import scala.concurrent.ExecutionContext

trait TemplateModule {

  implicit def ec: ExecutionContext

  def controllerComponents: ControllerComponents
  def wsClient: WSClient

  lazy val templateClient: TemplateClient = wire[TemplateClient]
  lazy val templateService: TemplateService = wire[TemplateServiceImpl]
  lazy val templateController: TemplateController = wire[TemplateController]

}
