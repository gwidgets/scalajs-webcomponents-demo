package elements

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import model.Expense
import org.scalajs.dom
import org.scalajs.dom.raw._
import io.circe.parser.decode

class ListSectionElement extends MainAreaSectionElement {

  var dataTable: HTMLTableElement = null;
  implicit val expenseDecoder: Decoder[Expense] = deriveDecoder[Expense]
  setName("List")

  def connectedCallback(): Unit = {
    renderTable()
  }

  def renderTable(): Unit = {

    dataTable = dom.document.createElement("table").asInstanceOf[HTMLTableElement]
    dataTable.classList.add("data-table")
    val tableHeader = dom.document.createElement("thead").asInstanceOf[HTMLElement]
    val idHeaderCell = dom.document.createElement("th").asInstanceOf[HTMLElement]
    idHeaderCell.textContent = "id"
    val amountHeaderCell = dom.document.createElement("th").asInstanceOf[HTMLElement]
    amountHeaderCell.textContent = "amount"
    val dateHeaderCell = dom.document.createElement("th").asInstanceOf[HTMLElement]
    dateHeaderCell.textContent = "date"
    val reasonHeaderCell = dom.document.createElement("th").asInstanceOf[HTMLElement]
    reasonHeaderCell.textContent = "reason"

    val tableHeaderRow = dom.document.createElement("tr").asInstanceOf[HTMLTableRowElement]

    tableHeaderRow.appendChild(idHeaderCell)
    tableHeaderRow.appendChild(amountHeaderCell)
    tableHeaderRow.appendChild(dateHeaderCell)
    tableHeaderRow.appendChild(reasonHeaderCell)

    tableHeader.appendChild(tableHeaderRow)
    dataTable.appendChild(tableHeader)

    for (i <- 0 until dom.window.localStorage.length) {
      val key = dom.window.localStorage.key(i)
      val expenseJsonOption = Option(dom.window.localStorage.getItem(key))
      if (expenseJsonOption.isDefined) {
        val expense = decode[Expense](expenseJsonOption.get).toSeq.last
        val row = dom.document.createElement("tr").asInstanceOf[HTMLTableRowElement]
        val idCell = dom.document.createElement("td").asInstanceOf[HTMLTableDataCellElement]
        idCell.textContent = expense.id
        val amountCell = dom.document.createElement("td").asInstanceOf[HTMLTableDataCellElement]
        amountCell.textContent = expense.amount
        val dateCell = dom.document.createElement("td").asInstanceOf[HTMLTableDataCellElement]
        dateCell.textContent = expense.date
        val reasonCell = dom.document.createElement("td").asInstanceOf[HTMLTableDataCellElement]
        reasonCell.textContent = expense.reason
        row.appendChild(idCell)
        row.appendChild(amountCell)
        row.appendChild(dateCell)
        row.appendChild(reasonCell)
        dataTable.appendChild(row)
      }
    }
    getContainer().appendChild(dataTable)
  }

  def refreshUI(): Unit = {
    clear()
    renderTable()
  }
}