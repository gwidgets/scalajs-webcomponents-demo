package elements

import org.scalajs.dom.Element
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.HTMLStyleElement
import wrappers.{HTMLElement, HTMLTemplateElement}

import scala.scalajs.js.Dynamic.literal
import scalatags.JsDom.short._

object TemplateTags {
  val template = typedTag[HTMLTemplateElement]("template")
  val sstyle = typedTag[HTMLStyleElement]("style")
}

abstract class MainAreaSectionElement extends HTMLElement {
  import TemplateTags._

  var shadow = this.attachShadow(literal(mode = "open"))

  private val container: Div = div(*.cls := "container").render

  shadow.appendChild(sstyle(
    """.container {
        display: flex;
        width: 40%;
        flex-direction: column;
        margin: auto;
        padding-top: 15%;
         }
         tr:nth-child(even) {background-color: #f2f2f2;}
         th {
            background-color: #4CAF50;
            color: white;
        }
        .action-button {
         width: 100px;
         margin: auto;
        }
        .data-table {
         border-collapse: collapse
        }"""
  ).render)

  shadow.appendChild(container)

  def getContainer(): Element = container


  def setName(name: String): Unit = {
    this.setAttribute("name", name)
  }

  def getName(): String = {
    this.getAttribute("name")
  }

  def clear(): Unit = {
    var firstChild = getContainer().firstChild
    while (firstChild != null) {
      getContainer().removeChild(firstChild)
      firstChild = getContainer().firstChild
    }
  }

}