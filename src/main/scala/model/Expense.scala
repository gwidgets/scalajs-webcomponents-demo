package model

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._


case class Expense(id: String, amount:String, date: String, reason: String)

object Expense {
  implicit val expenseDecoder: Decoder[Expense] = deriveDecoder
  implicit val expenseEncoder: Encoder[Expense] = deriveEncoder
}