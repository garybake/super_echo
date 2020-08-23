package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with Logging {

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    logger.warn("Index requested")
    Ok(views.html.index())
  }

}
