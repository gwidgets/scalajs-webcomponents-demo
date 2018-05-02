package elements

import org.scalajs.dom
import wrappers.{HTMLElement, HTMLTemplateElement}
import scalajs.js.Dynamic.literal

class HeaderBarElement extends HTMLElement {
  var template: HTMLTemplateElement = dom.document.getElementById("header-bar-template").asInstanceOf[HTMLTemplateElement]
  var shadow = this.attachShadow(literal(mode = "open"))
  shadow.appendChild(template.content.cloneNode(true))
}
