package elements

import org.scalajs.dom
import org.scalajs.dom.Element
import wrappers.{HTMLElement, HTMLTemplateElement}

import scala.scalajs.js.Dynamic.literal

object MainAreaSectionElement {
  val template: HTMLTemplateElement =
    dom.document.getElementById("main-area-section-template").asInstanceOf[HTMLTemplateElement]
}

abstract class MainAreaSectionElement extends HTMLElement {
  var shadow = this.attachShadow(literal(mode = "open"))

  shadow.appendChild(
    MainAreaSectionElement.template.content.cloneNode(true)
  )

  val content: Element = this.shadow.querySelector(".container")


  def setName(name: String): Unit = {
    this.setAttribute("name", name)
  }

  def getName(): String = {
    this.getAttribute("name")
  }

  def clear(): Unit = {
    var firstChild = content.firstChild
    while (firstChild != null) {
      content.removeChild(firstChild)
      firstChild = content.firstChild
    }
  }

}