package example

import elements._
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLScriptElement
import wrappers.Window

import scala.scalajs.js


object Main {

  def main(args: Array[String]): Unit = {
    val window = js.Dynamic.global.window.asInstanceOf[Window]

    if (!window.navigator.userAgent.contains("Chrome")) {
      window.document.addEventListener("DOMContentLoaded", (event: Event) => {
        var templateScript = window.document.createElement("script").asInstanceOf[HTMLScriptElement]
        templateScript.src = "./template.js"
        window.document.body.appendChild(templateScript)
        var webComponentsScript = window.document.createElement("script").asInstanceOf[HTMLScriptElement]
        webComponentsScript.src = "./webcomponentsjs/webcomponents.js"
        window.document.body.appendChild(webComponentsScript)
      })
      window.document.addEventListener("WebComponentsReady", (event: Event) => {
        println("web components polyfill loaded")
        window.customElements.define("add-section", js.constructorOf[AddSectionElement])
        window.customElements.define("list-section", js.constructorOf[ListSectionElement])
        window.customElements.define("delete-section", js.constructorOf[DeleteSectionElement])
        window.customElements.define("side-nav", js.constructorOf[SideNavElement])
        window.customElements.define("main-area", js.constructorOf[MainAreaElement])
        window.customElements.define("header-bar", js.constructorOf[HeaderBarElement])
        window.customElements.define("app-element", js.constructorOf[AppElement])
        println("elements registered")
        val appElement = new AppElement()

        val headerBarElement = new HeaderBarElement()
        headerBarElement.setAttribute("slot", "header-bar")
        val sideNavElement = new SideNavElement()
        sideNavElement.setAttribute("slot", "side-nav")
        val mainAreaSectionElement = new MainAreaElement()
        mainAreaSectionElement.setAttribute("slot", "main-area")

        val addSectionElement = new AddSectionElement()
        val listSectionElement = new ListSectionElement()
        val deleteSectionElement = new DeleteSectionElement()
        mainAreaSectionElement.appendChild(addSectionElement)
        mainAreaSectionElement.appendChild(listSectionElement)
        mainAreaSectionElement.appendChild(deleteSectionElement)

        appElement.appendChild(headerBarElement)
        appElement.appendChild(sideNavElement)
        appElement.appendChild(mainAreaSectionElement)

        window.document.body.appendChild(appElement)
        println("app loaded")
      })
    } else {
      window.document.addEventListener("DOMContentLoaded", (event: Event) => {
        window.customElements.define("add-section", js.constructorOf[AddSectionElement])
        window.customElements.define("list-section", js.constructorOf[ListSectionElement])
        window.customElements.define("delete-section", js.constructorOf[DeleteSectionElement])
        window.customElements.define("side-nav", js.constructorOf[SideNavElement])
        window.customElements.define("main-area", js.constructorOf[MainAreaElement])
        window.customElements.define("header-bar", js.constructorOf[HeaderBarElement])
        window.customElements.define("app-element", js.constructorOf[AppElement])
        println("elements registered")
        val appElement = new AppElement()

        val headerBarElement = new HeaderBarElement()
        headerBarElement.setAttribute("slot", "header-bar")
        val sideNavElement = new SideNavElement()
        sideNavElement.setAttribute("slot", "side-nav")
        val mainAreaSectionElement = new MainAreaElement()
        mainAreaSectionElement.setAttribute("slot", "main-area")

        val addSectionElement = new AddSectionElement()
        val listSectionElement = new ListSectionElement()
        val deleteSectionElement = new DeleteSectionElement()
        mainAreaSectionElement.appendChild(addSectionElement)
        mainAreaSectionElement.appendChild(listSectionElement)
        mainAreaSectionElement.appendChild(deleteSectionElement)

        appElement.appendChild(headerBarElement)
        appElement.appendChild(sideNavElement)
        appElement.appendChild(mainAreaSectionElement)

        window.document.body.appendChild(appElement)
        println("app loaded")
      })

    }
  }
}