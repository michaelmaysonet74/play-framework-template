package clients

import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}

class TemplateClient(
  ws: WSClient
)(implicit
  ec: ExecutionContext
) {

  def getGoogleStatus: Future[String] =
    ws.url("https://www.google.com")
      .get()
      .map(res => s"${res.status} ${res.statusText}")

}
