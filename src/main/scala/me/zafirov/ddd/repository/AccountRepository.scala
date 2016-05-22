package me.zafirov.ddd.repository

import me.zafirov.ddd.model.Account

trait AccountRepository {
  def query(accountNo: String): Option[Account]
  def query(criteria: Criteria[Account]): Seq[Account]
  def write(accounts: Seq[Account]): Boolean
  def delete(account: Account): Boolean
}
