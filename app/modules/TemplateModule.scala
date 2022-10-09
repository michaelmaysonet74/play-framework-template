package modules

import clients.TemplateClient
import controllers.TemplateController
import services.{TemplateService, TemplateServiceImpl}
import com.softwaremill.macwire.wire
import play.api.mvc.ControllerComponents

import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.ExecutionContext

trait TemplateModule {

  implicit def ec: ExecutionContext
  implicit def wsClient: StandaloneAhcWSClient

  def controllerComponents: ControllerComponents

  lazy val templateClient: TemplateClient = wire[TemplateClient]
  lazy val templateService: TemplateService = wire[TemplateServiceImpl]
  lazy val templateController: TemplateController = wire[TemplateController]

}
