package elements

import java.util.UUID

import model.Expense
import org.scalajs.dom
import org.scalajs.dom.raw._
import io.circe.syntax._

import scalatags.JsDom.short._

class AddSectionElement extends MainAreaSectionElement {

  setName("Add")
  val amountInput = input(*.tpe := "number", *.id := "amountInput").render
  val dateInput = input(*.tpe := "date", *.id := "dateInput").render
  val reasonInput = textarea(*.id := "reasonInput").render
  val amountLabel = label("Amount: ", *.`for` := "amountInput").render
  val dateLabel = label(*.`for` := "dateInput", "Date: ").render
  val reasonLabel = label(*.`for` := "reasonInput", "Reason: ").render

  val submitButton = button("Add", *.cls := "action-button",
    *.onclick := { () =>
      val id = UUID.randomUUID().toString
      val expense = new Expense(id, getExpenseAmount(), getExpenseDate(), getExpenseReason())
      var expenseAsJson = expense.asJson
      println(expenseAsJson)
      dom.window.localStorage.setItem(expense.id, expenseAsJson.toString())
      dom.document.dispatchEvent(new wrappers.Event("addExpense"))
    }
  ).render

  def connectedCallback(): Unit = {
    Seq(
      amountLabel, amountInput, dateLabel, dateInput,
      reasonLabel, reasonInput, submitButton
    ).applyTo(getContainer())
  }

  def getExpenseAmount(): String = amountInput.value
  def getExpenseDate(): String = dateInput.value
  def getExpenseReason(): String = reasonInput.value
}