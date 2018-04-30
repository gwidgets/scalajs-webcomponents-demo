package wrappers

import org.scalajs.dom


import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal


@js.native
@JSGlobal
class Window extends dom.Window {

   val customElements: CustomElementsRegistry = js.native

}