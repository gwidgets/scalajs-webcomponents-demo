package elements

import org.scalajs.dom
import org.scalajs.dom.raw.HTMLAnchorElement
import wrappers.{HTMLElement, HTMLTemplateElement}

import scalajs.js.Dynamic.literal

object SideNavElement {
  val template: HTMLTemplateElement =
    dom.document.getElementById("side-nav-template").asInstanceOf[HTMLTemplateElement]
}

class SideNavElement extends HTMLElement{
  var shadow = this.attachShadow(literal(mode = "open"))

  shadow.appendChild(SideNavElement.template.content.cloneNode(true))

  def addLink(title: String) : Unit = {
    val link: HTMLAnchorElement = dom.document.createElement("a").asInstanceOf[HTMLAnchorElement]
    link.text = title
    link.href= "#"+title.toLowerCase
     this.shadow.querySelector(".sidenav").appendChild(link)
  }
}