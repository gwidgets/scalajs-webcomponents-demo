package elements

import model.Expense
import org.scalajs.dom
import org.scalajs.dom.raw._
import io.circe.parser.decode
import scalatags.JsDom.short._

class ListSectionElement extends MainAreaSectionElement {

  var dataTable: HTMLTableElement = null;
  setName("List")

  def connectedCallback(): Unit = {
    renderTable()
  }

  def readFromDB() = {
    (0 until dom.window.localStorage.length).map { i =>
      val key = dom.window.localStorage.key(i)
      Option(dom.window.localStorage.getItem(key))
    }.collect {
      case Some(e) => decode[Expense](e).toSeq.last
    }
  }

  def renderTable(): Unit = {
    dataTable = table(*.cls := "data-table",
      thead(tr(th("id"), th("amount"), th("date"), th("reason"))),
      readFromDB().map { expense =>
        tr(
          td(expense.id),
          td(expense.amount),
          td(expense.date),
          td(expense.reason)
        )
      }
    ).render

    content.appendChild(dataTable)
  }

  def refreshUI(): Unit = {
    clear()
    renderTable()
  }
}