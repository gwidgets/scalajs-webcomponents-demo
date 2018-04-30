package example

import elements._
import org.scalajs.dom.Event
import wrappers.Window

import scala.scalajs.js


object Main {

  def main(args: Array[String]): Unit = {
    val window = js.Dynamic.global.window.asInstanceOf[Window]

    window.document.addEventListener("DOMContentLoaded", (event: Event) => {

      window.customElements.define("add-section", js.constructorOf[AddSectionElement])
      window.customElements.define("list-section", js.constructorOf[ListSectionElement])
      window.customElements.define("delete-section", js.constructorOf[DeleteSectionElement])
      window.customElements.define("side-nav", js.constructorOf[SideNavElement])
      window.customElements.define("main-area", js.constructorOf[MainAreaElement])
      window.customElements.define("header-bar", js.constructorOf[HeaderBarElement])
      window.customElements.define("app-element", js.constructorOf[AppElement])
      println("elements registered")
    })
  }
}