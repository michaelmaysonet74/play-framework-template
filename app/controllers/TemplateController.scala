package controllers

import models.TemplateResponse
import services.TemplateService
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

class TemplateController(
  templateService: TemplateService,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext,
  implicit val logger: Logger
) extends AbstractController(cc) {

  def getStatus(url: String): Action[AnyContent] =
    Action.async {
      logger.info("GET /")
      templateService.getStatus(url).map { status =>
        Ok(Json.toJson(TemplateResponse(status)))
      }
    }

}
