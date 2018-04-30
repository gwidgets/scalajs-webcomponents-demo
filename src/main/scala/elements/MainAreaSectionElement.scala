package elements

import org.scalajs.dom
import org.scalajs.dom.Element
import wrappers.{HTMLElement, HTMLTemplateElement}

import scala.scalajs.js.JSON

abstract class MainAreaSectionElement extends HTMLElement {

  var template: HTMLTemplateElement = dom.document.getElementById("main-area-section-template").asInstanceOf[HTMLTemplateElement]
  var shadow = this.attachShadow(JSON.parse("{\"mode\": \"open\"}"))
  shadow.appendChild(template.content.cloneNode(true))


  def getContainer(): Element = {
    return this.shadow.querySelector(".container")
  }

  def setName(name: String): Unit = {
    this.setAttribute("name", name)
  }

  def getName(): String = {
    this.getAttribute("name")
  }

  def clear(): Unit = {
    var firstChild = getContainer().firstChild
    while(firstChild != null) {
      getContainer().removeChild(firstChild)
      firstChild = getContainer().firstChild
    }
  }

}