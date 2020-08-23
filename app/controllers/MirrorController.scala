package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._

@Singleton
class MirrorController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with Logging {

  def mirror_post(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    logger.warn("Hello there!")

    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    jsonBody
      .map { json =>
        logger.warn("POST")
        logger.warn("*** START OF BODY ***")
        logger.warn(Json.prettyPrint(json))
        logger.warn("*** END OF BODY ***")
        Ok(Json.prettyPrint(json))
      }
      .getOrElse {
        BadRequest("Expecting application/json request body")
      }
  }

  def mirror_get(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    logger.warn("mirror_get")
    logger.warn("GET")

    if (request.hasBody) {
      logger.warn("*** START OF BODY ***")
      logger.warn(request.body.toString)
      logger.warn("*** END OF BODY ***")
      Ok(request.body.toString)
    }

    Ok(request.toString())
  }
}
