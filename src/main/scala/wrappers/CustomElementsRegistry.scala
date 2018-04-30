package wrappers

import scala.scalajs.js

@js.native
trait CustomElementsRegistry extends js.Any {

   def define(name: String, definition: Any ) :Unit = js.native

}
