import me.zafirov.ddd.model.SavingsAccount

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

def calculateInterest[A <: SavingsAccount](acc: A,
                                           balance: BigDecimal): Try[BigDecimal] = {
  if (acc.rateOfInterest == 0) Failure(new Exception("Interest Rate not found"))
  else Success(BigDecimal(10000))
}
def getCurrencyBalance[A <: SavingsAccount](account: A): Try[BigDecimal] = {
  Success(BigDecimal(1000L))
}
def calculateNetAssetValue[A <: SavingsAccount](account: A,
                                                ccyBalance: BigDecimal, interest: BigDecimal): Try[BigDecimal] = {
  Success(ccyBalance + interest + 200)
}
//
//for {
//  b <- getCurrencyBalance(s1)
//  i <- calculateInterest(s1, b)
//  v <- calculateNetAssetValue(s1, b, i)
//} yield (s1, v)


def calculateInterest[A <: SavingsAccount](account: A,
                                           balance: BigDecimal):Future[BigDecimal] = Future {
  if (account.rateOfInterest == 0) throw new Exception("Interest Rate not found")
  else BigDecimal(10000)
}

def getCurrencyBalance[A <: SavingsAccount](account: A):Future[BigDecimal] = Future {
  BigDecimal(1000L)
}

def calculateNetAssetValue[A <: SavingsAccount](account: A,
                                                ccyBalance: BigDecimal, interest: BigDecimal): Future[BigDecimal] =
  Future {
    ccyBalance + interest + 200
  }

//val result = for {
//  b <- getCurrencyBalance(s4)
//  i <- calculateInterest(s4, b)
//  v <- calculateNetAssetValue(s4, b, i)
//} yield (v)
//
//result onComplete {
//  case Success(v) => //.. success #A
//  case Failure(ex) => //.. failure #B
//}