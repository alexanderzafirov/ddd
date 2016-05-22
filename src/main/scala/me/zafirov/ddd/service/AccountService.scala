package me.zafirov.ddd.service

import java.util.{Calendar, Date}

import me.zafirov.ddd.model._

import scala.util.{Failure, Success, Try}

trait AccountService {

  def debit(a: Account, amount: BigDecimal): Try[Account] = {
    if (a.balance.amount < amount)
      Failure(new Exception("Insufficient balance in account"))
    else
      Success(Account(a.no, a.name, a.bank, a.address, a.dateOfOpening, None, balance = Balance(a.balance.amount - amount))
  }

  def credit(a: Account, amount: BigDecimal): Try[Account] =
    Success(Account(a.no, a.name, a.bank, a.address, a.dateOfOpening, None, balance = Balance(a.balance.amount + amount))

  def transfer(from: Account, to: Account, amount: BigDecimal): Try[Account] =
    for {
      d <- debit(from, amount)
      c <- credit(to, amount)
    } yield c

  def verifyCustomer(customer: Customer): Option[Customer] = {
    if (Verifications.verifyRecord(customer)) Some(customer)
    else None
  }

  def openCheckingAccount(customer: Customer, effectiveDate: Date) = {
    // ...
    Account(no = "ac1", customer.name, Bank(), customer.address, Calendar.getInstance().getTime, None)
  }
}
