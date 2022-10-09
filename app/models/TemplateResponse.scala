package models

import play.api.libs.json.{Json, OWrites}

final case class TemplateResponse(
  status: String
)

object TemplateResponse {
  implicit val encoder: OWrites[TemplateResponse] = Json.writes[TemplateResponse]
}
