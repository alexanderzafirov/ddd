package me.zafirov.ddd.model

import java.util.Date

sealed trait Account {
  def no: String

  def name: String

  def bank: Bank

  def address: Address

  def dateOfOpening: Date

  def dateOfClose: Option[Date]

  def balance: Balance

  // ...
}

case class CheckingAccount(
                            no: String,
                            name: String,
                            bank: Bank,
                            address: Address,
                            dateOfOpening: Date,
                            dateOfClose: Option[Date],
                            balance: Balance
                            // ...
                          ) extends Account

trait InterestBearingAccount extends Account {
  def rateOfInterest: BigDecimal
}

case class SavingsAccount(
                           no: String,
                           name: String,
                           bank: Bank,
                           address: Address,
                           dateOfOpening: Date,
                           dateOfClose: Option[Date],
                           balance: Balance,
                           // ...
                           rateOfInterest: BigDecimal
                           // ...
                         ) extends InterestBearingAccount

case class MoneyMarketAccount(
                               no: String,
                               name: String,
                               bank: Bank,
                               address: Address,
                               dateOfOpening: Date,
                               dateOfClose: Option[Date],
                               balance: Balance,
                               // ...
                               rateOfInterest: BigDecimal
                               // ...
                             ) extends InterestBearingAccount

object Account {
  def apply(no: String, name: String, bank: Bank, address: Address, dateOfOpening: Date,
            dateOfClose: Option[Date]) =
    no match {
      case "1" => CheckingAccount(no, name, bank, address, dateOfOpening, dateOfClose, balance = Balance(0))
      case "3" => SavingsAccount(no, name, bank, address, dateOfOpening, dateOfClose, balance = Balance(0),
        rateOfInterest = 1500)
    }

  def apply(no: String, name: String, bank: Bank, address: Address, dateOfOpening: Date,
            dateOfClose: Option[Date], balance: Balance): Account =
    no match {
      case "1" => CheckingAccount(no, name, bank, address, dateOfOpening, dateOfClose, balance)
      case "3" => SavingsAccount(no, name, bank, address, dateOfOpening, dateOfClose, balance, rateOfInterest = 1500)
    }
}