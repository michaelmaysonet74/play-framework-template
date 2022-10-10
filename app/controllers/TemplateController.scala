package controllers

import models.TemplateResponse
import services.TemplateService
import play.api.Logging
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Result}

import scala.concurrent.ExecutionContext
import play.api.Logger

class TemplateController(
  templateService: TemplateService,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext,
  implicit val logger: Logger
) extends AbstractController(cc) {

  def get(): Action[AnyContent] =
    Action.async {
      logger.info("GET /")
      templateService.getStatus.map { status =>
        Ok(Json.toJson(TemplateResponse(status)))
      }
    }

}
