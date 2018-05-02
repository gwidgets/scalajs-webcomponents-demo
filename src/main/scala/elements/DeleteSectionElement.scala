package elements

import org.scalajs.dom
import org.scalajs.dom.raw._

class DeleteSectionElement extends ListSectionElement {

  setName("Delete")

  override def connectedCallback(): Unit = {
    this.renderTable()
  }

  override def renderTable(): Unit = {
    super.renderTable()
    val rows = dataTable.querySelectorAll("tr")

    val headerRow = dataTable.querySelector("thead > tr").asInstanceOf[HTMLTableRowElement]
    val emptyHeaderCell = dom.document.createElement("th")
    headerRow.insertBefore(emptyHeaderCell, headerRow.firstChild)

    for (i <- 1 until rows.length) {
      val row = rows.item(i)
      val deleteCell = dom.document.createElement("td").asInstanceOf[HTMLTableDataCellElement]
      val deleteCheckBox = dom.document.createElement("input").asInstanceOf[HTMLInputElement]
      deleteCheckBox.`type` = "checkbox"
      deleteCheckBox.id = row.firstChild.textContent
      deleteCell.appendChild(deleteCheckBox)
      row.insertBefore(deleteCell, row.firstChild)
    }

    val deleteButton = dom.document.createElement("button").asInstanceOf[HTMLButtonElement]
    deleteButton.textContent = "Delete selection"
    deleteButton.classList.add("action-button")

    deleteButton.addEventListener("click", (event: Event) => {
      val rows = dataTable.querySelectorAll("tr")
      for (i <- 1 until rows.length) {
        val row = rows.item(i)
        val input = row.asInstanceOf[HTMLTableRowElement].querySelector("td > input").asInstanceOf[HTMLInputElement]
        if (input.checked) {
          dom.window.localStorage.removeItem(input.id)
        }
      }
      dom.document.dispatchEvent(new wrappers.Event("deleteExpense"))
    })

    getContainer().appendChild(deleteButton)
  }

  override def refreshUI(): Unit = {
    clear()
    renderTable()
  }
}
