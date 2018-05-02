package elements

import org.scalajs.dom
import org.scalajs.dom.raw.HTMLAnchorElement
import wrappers.{HTMLElement, HTMLTemplateElement}

import scala.scalajs.js.JSON

class SideNavElement extends HTMLElement{

  var template: HTMLTemplateElement = dom.document.getElementById("side-nav-template").asInstanceOf[HTMLTemplateElement]
  var shadow = this.attachShadow(JSON.parse("{\"mode\": \"open\"}"))
  shadow.appendChild(template.content.cloneNode(true))

  def addLink(title: String) : Unit = {
    val link: HTMLAnchorElement = dom.document.createElement("a").asInstanceOf[HTMLAnchorElement]
    link.text = title
    link.href= "#"+title.toLowerCase
     this.shadow.querySelector(".sidenav").appendChild(link)
  }
}