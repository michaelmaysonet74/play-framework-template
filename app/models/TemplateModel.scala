package models

import play.api.libs.json.{Json, OWrites}

final case class TemplateModel(
  status: String
)

object TemplateModel {
  implicit val encoder: OWrites[TemplateModel] = Json.writes[TemplateModel]
}
