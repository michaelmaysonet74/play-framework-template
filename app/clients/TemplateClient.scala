package clients

import play.api.libs.ws.StandaloneWSRequest
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{ExecutionContext, Future}

class TemplateClient()(
  implicit ec: ExecutionContext,
  implicit val ws: StandaloneAhcWSClient
) {

  def getGoogleStatus: Future[String] =
    ws.url("https://www.google.com")
      .get()
      .map(res => s"${res.status} ${res.statusText}")

}
