package elements

import org.scalajs.dom
import org.scalajs.dom.raw._
import scalatags.JsDom.short._
class DeleteSectionElement extends ListSectionElement {

  setName("Delete")

  override def connectedCallback(): Unit = {
    this.renderTable()
  }

  override def renderTable(): Unit = {
    super.renderTable()
    val rows = dataTable.querySelectorAll("tr")

    val headerRow = dataTable.querySelector("thead > tr").asInstanceOf[HTMLTableRowElement]
    val emptyHeaderCell = th().render
    headerRow.insertBefore(emptyHeaderCell, headerRow.firstChild)

    for (i <- 1 until rows.length) {
      val row = rows.item(i)

      val deleteCell = td(
        input(*.tpe := "checkbox", *.id := row.firstChild.textContent).render
      ).render

      row.insertBefore(deleteCell, row.firstChild)
    }

    val deleteButton = button( "Delete selection", *.cls:="action-button", *.onclick := { () =>
      val rows = dataTable.querySelectorAll("tr")
      for (i <- 1 until rows.length) {
        val row = rows.item(i)
        val input = row.asInstanceOf[HTMLTableRowElement].querySelector("td > input").asInstanceOf[HTMLInputElement]
        if (input.checked) {
          dom.window.localStorage.removeItem(input.id)
        }
      }
      dom.document.dispatchEvent(new wrappers.Event("deleteExpense"))
    }).render

    getContainer().appendChild(deleteButton)
  }

  override def refreshUI(): Unit = {
    clear()
    renderTable()
  }
}
