package elements

import org.scalajs.dom
import org.scalajs.dom.raw.Event
import wrappers.{HTMLElement, HTMLTemplateElement}
import scalajs.js.Dynamic.literal

class AppElement extends HTMLElement {
  var template: HTMLTemplateElement = dom.document.getElementById("app-element-template").asInstanceOf[HTMLTemplateElement]
  var shadow = this.attachShadow(literal(mode = "open"))
  shadow.appendChild(template.content.cloneNode(true))

  def connectedCallback(): Unit = {
    initRouter()
    var sections = getMainArea().getAllSections()

    for (i <- 0 until sections.length) {
      var section = sections.item(i).asInstanceOf[MainAreaSectionElement]
      getSideNav().addLink(section.getName())
    }
    updateUI()
  }

  private def getSideNav(): SideNavElement = {
    return this.querySelector("side-nav").asInstanceOf[SideNavElement]
  }

  private def getMainArea(): MainAreaElement = {
    return this.querySelector("main-area").asInstanceOf[MainAreaElement]
  }


  def initRouter(): Unit = {

    dom.window.addEventListener("hashchange", (event: Event) => {

      val hash = dom.window.location.hash.replace("#", "")
      getMainArea().select(hash)

    }, false)
  }

  def updateUI(): Unit = {
    val hash = dom.window.location.hash.replace("#", "")
    if (!hash.isEmpty) {
      getMainArea().select(hash)
    } else {
      getMainArea().selectFirst()
    }
  }
}