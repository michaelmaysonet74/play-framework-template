package controllers

import models.TemplateResponse
import services.TemplateService
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Result}

import scala.concurrent.ExecutionContext

class TemplateController(
  templateService: TemplateService,
  cc: ControllerComponents
)(implicit
  ec: ExecutionContext
) extends AbstractController(cc) {

  def get(): Action[AnyContent] =
    Action.async {
      templateService.getStatus.map { status =>
        Ok(
          Json.toJson(TemplateResponse(status))
        )
      }
    }

}
