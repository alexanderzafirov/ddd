import java.util.Date

type Amount = BigDecimal

case class Balance(amount: Amount = 0)

class Account(no: String, name: String, dateOfOpening: Date, val balance: Balance = Balance()) {

  def debit(amount: Amount) = {
    if (balance.amount < amount)
      throw new Exception("Insufficient balance in account")
    new Account(no, name, dateOfOpening, Balance(balance.amount - amount))
  }

  def credit(amount: Amount) =
    new Account(no, name, dateOfOpening, Balance(balance.amount + amount))
}

val a = new Account("a1", "John", new Date())
a.balance == Balance(0)

val b = a.credit(100)
a.balance == Balance(0)
b.balance == Balance(100)

val c = b.debit(20)
b.balance == Balance(100)
c.balance == Balance(80)