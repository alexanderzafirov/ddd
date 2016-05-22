import java.util.Date

type Amount = BigDecimal

case class Balance(amount: Amount = 0)

class Account(no: String, name: String, dateOfOpening: Date) {
  var balance: Balance = Balance()

  def debit(amount: Amount) = {
    if (balance.amount < amount)
      throw new Exception("Insufficient balance in account")
    balance = Balance(balance.amount - amount)
  }

  def credit(amount: Amount) = balance = Balance(balance.amount + amount)
}

val a = new Account("a1", "John", new Date())
a.balance == Balance(0)

a.credit(100)
a.balance == Balance(100)

a.debit(20)
a.balance == Balance(80)