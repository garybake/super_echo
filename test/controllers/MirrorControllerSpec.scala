package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._


class MirrorControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "MirrorController GET" should {

    "return the request description from a get request" in {
      val controller = new MirrorController(stubControllerComponents())
      val mirrorGet = controller.mirrorGet().apply(FakeRequest(GET, "/mirror"))

      status(mirrorGet) mustBe OK
      contentType(mirrorGet) mustBe Some("text/plain")
      contentAsString(mirrorGet) must include ("GET /mirror")
    }

    "render the index page from a new instance of controller" in {
      val controller = new MirrorController(stubControllerComponents())
      val fakeString = """{"name" : "johndoe"}"""
      val fakeJson = play.api.libs.json.Json.parse(fakeString)
      val mirrorPost = controller.mirrorPost().apply(
          FakeRequest(POST, "/mirror")
            .withJsonBody(fakeJson)
      )

      status(mirrorPost) mustBe OK
      contentType(mirrorPost) mustBe Some("text/plain")
      contentAsString(mirrorPost) must include ("""name" : "johndoe""")
    }

  }
}
