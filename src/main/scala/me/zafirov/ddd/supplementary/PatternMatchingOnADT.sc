import java.util.Date

case class Account(no: String, name: String, dateOfOpening: Date, balance: Balance)

sealed trait Instrument

case class Equity(isin: String, name: String, dateOfIssue: Date) extends Instrument
case class FixedIncome(isin: String, name: String, dateOfIssue: Date, issueCurrency: Currency, nominal: BigDecimal) extends Instrument

sealed trait Currency extends Instrument
case object USD extends Currency
case object JPY extends Currency

case class Amount(a: BigDecimal, c: Currency) {
  def +(that: Amount) = {
    require(that.c == c)
    Amount(a + that.a, c)
  }
}

case class Balance(amount: BigDecimal, ins: Instrument, asOf: Date)

def getMarketValue(e: Equity, a: BigDecimal) = ???
def getAccruedInterest(i: String) = ???

def getHolding(account: Account) = account.balance match {
  case Balance(a, c: Currency, _) => Amount(a, c)
  case Balance(a, e: Equity, _)   => getMarketValue(e, a)
  case Balance(a, FixedIncome(i, _, _, c, n), _) =>
    Amount(n * a, c) + getAccruedInterest(i)
}