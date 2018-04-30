package elements

import org.scalajs.dom
import wrappers.{HTMLElement, HTMLTemplateElement}

import scala.scalajs.js.JSON

class HeaderBarElement extends HTMLElement {

  var template: HTMLTemplateElement = dom.document.getElementById("header-bar-template").asInstanceOf[HTMLTemplateElement]
  var shadow = this.attachShadow(JSON.parse("{\"mode\": \"open\"}"));
  shadow.appendChild(template.content.cloneNode(true));

}
