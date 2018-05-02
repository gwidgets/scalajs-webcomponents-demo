package elements

import org.scalajs.dom
import org.scalajs.dom.raw.{Event, NodeList}
import wrappers.{HTMLElement, HTMLTemplateElement}

import scalajs.js.Dynamic.literal

class MainAreaElement extends HTMLElement {

  var template: HTMLTemplateElement = dom.document.getElementById("main-area-template").asInstanceOf[HTMLTemplateElement]
  var shadow = this.attachShadow(literal(mode = "closed"))
  shadow.appendChild(template.content.cloneNode(true))

  def connectedCallback(): Unit = {
    dom.document.addEventListener("addExpense", (event: Event) => {
      getListSection().refreshUI()
      getDeleteSection().refreshUI()
    })

    dom.document.addEventListener("deleteExpense", (event: Event) => {
      getListSection().refreshUI()
      getDeleteSection().refreshUI()
    })
  }


  def select(name: String): Unit = {
    val sections = getAllSections()

    for (i <- 0 until sections.length) {
      val section = sections.item(i).asInstanceOf[MainAreaSectionElement]
      if (section.getName().equalsIgnoreCase(name)) {
        section.style.display = ""
        section.setAttribute("selected", "")
      } else {
        section.style.display = "none"
        section.removeAttribute("selected")
      }
    }
  }

  def selectFirst(): Unit = {
    val sections = getAllSections()
    val first = Option(sections.item(0).asInstanceOf[HTMLElement])
    if (first.isDefined) {
      first.get.style.display = ""
    }

    for (i <- 1 until sections.length) {
      val section = sections.item(i).asInstanceOf[HTMLElement]
      section.style.display = "none"
    }
  }

  def getAllSections(): NodeList = {
    this.querySelectorAll(":not(text)")
  }

  def getListSection(): ListSectionElement = {
    this.querySelector("list-section").asInstanceOf[ListSectionElement]
  }

  def getDeleteSection(): DeleteSectionElement = {
    this.querySelector("delete-section").asInstanceOf[DeleteSectionElement]
  }
}