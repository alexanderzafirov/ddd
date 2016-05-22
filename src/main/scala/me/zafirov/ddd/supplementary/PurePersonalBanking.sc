import java.util.{Calendar, Date}

import scala.util.{Failure, Success, Try}

def today = Calendar.getInstance.getTime
type Amount = BigDecimal

case class Balance(amount: Amount = 0)

case class Account(no: String, name: String,
                   dateOfOpening: Date, balance: Balance = Balance())

trait AccountService {

  def debit(account: Account, amount: Amount): Try[Account] = {
    if (account.balance.amount < amount)
      Failure(new Exception("Insufficient balance in account"))
    else Success(account.copy(balance = Balance(account.balance.amount - amount)))
  }

  def credit(account: Account, amount: Amount): Try[Account] =
    Success(account.copy(balance = Balance(amount = account.balance.amount + amount)))
}

object AccountService extends AccountService

import AccountService._

val a = Account("a1", "John", today)
a.balance == Balance(0)

val b = credit(a, 1000)

for {
  b <- credit(a, 1000)
  c <- debit(b, 200)
  d <- debit(c, 190)
} yield d

val generateAuditLog: (Account, Amount) => Try[String]
val write: String => Unit

val amount = 9
debit(a, amount)
  .flatMap(b => generateAuditLog(b, amount))
  .foreach(write)
