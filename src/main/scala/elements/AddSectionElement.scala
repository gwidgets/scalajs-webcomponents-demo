package elements

import java.util.UUID

import model.Expense
import org.scalajs.dom
import org.scalajs.dom.raw._
import io.circe.syntax._

class AddSectionElement extends MainAreaSectionElement {

  setName("Add")

  def connectedCallback(): Unit  = {


    val amountInput = dom.document.createElement("input").asInstanceOf[HTMLInputElement]
    amountInput.`type` = "number"
    amountInput.id = "amountInput"
    val dateInput = dom.document.createElement("input").asInstanceOf[HTMLInputElement]
    dateInput.`type` = "date"
    dateInput.id = "dateInput"
    val reasonInput = dom.document.createElement("textarea").asInstanceOf[HTMLTextAreaElement]
    reasonInput.id = "reasonInput"

    val amountLabel = dom.document.createElement("label").asInstanceOf[HTMLLabelElement]
    amountLabel.htmlFor = "amountInput"
    amountLabel.textContent = "Amount: "
    val dateLabel = dom.document.createElement("label").asInstanceOf[HTMLLabelElement]
    dateLabel.htmlFor = "dateInput"
    dateLabel.textContent = "Date: "
    val reasonLabel = dom.document.createElement("label").asInstanceOf[HTMLLabelElement]
    reasonLabel.htmlFor = "reasonInput"
    reasonLabel.textContent = "Reason: "

    val submitButton = dom.document.createElement("button").asInstanceOf[HTMLButtonElement]
    submitButton.textContent = "Add"
    submitButton.classList.add("action-button")

    submitButton.addEventListener("click", (event: Event) => {
      val id = UUID.randomUUID().toString
       val expense = new Expense(id, getExpenseAmount(), getExpenseDate(), getExpenseReason())
     var expenseAsJson = expense.asJson
    println(expenseAsJson)
      dom.window.localStorage.setItem(expense.id, expenseAsJson.toString())
      dom.document.dispatchEvent(new wrappers.Event("addExpense"))
    })


    getContainer().appendChild(amountLabel)
    getContainer().appendChild(amountInput)
    getContainer().appendChild(dateLabel)
    getContainer().appendChild(dateInput)
    getContainer().appendChild(reasonLabel)
    getContainer().appendChild(reasonInput)
    getContainer().appendChild(submitButton)
  }

  def getExpenseAmount(): String = {
  this.getContainer().querySelector("#amountInput").asInstanceOf[HTMLInputElement].value
  }

  def getExpenseDate(): String = {
   this.getContainer().querySelector("#dateInput").asInstanceOf[HTMLInputElement].value
  }

  def getExpenseReason(): String = {
    this.getContainer().querySelector("#reasonInput").asInstanceOf[HTMLTextAreaElement].value
  }
}