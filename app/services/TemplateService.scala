package services

import clients.TemplateClient
import scala.concurrent.{ExecutionContext, Future}

trait TemplateService {

  def getStatus: Future[String]

}

class TemplateServiceImpl(
  templateClient: TemplateClient
)(implicit
  ec: ExecutionContext
) extends TemplateService {

  override def getStatus: Future[String] = templateClient.getGoogleStatus

}
