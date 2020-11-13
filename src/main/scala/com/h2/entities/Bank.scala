package com.h2.entities

import com.h2.services._

class Bank(val name: String,
           val city: String,
           val country: String,
           val email: Email) extends AccountService with CustomerService with ProductService with StatisticsService {

  println(s"$name Established 2018.")

  override def toString: String = s"[$name]" +
    s" - $numCustomers customers" +
    s" - $numDepositsProducts deposits products" +
    s" - $numDepositsAccounts deposits accounts" +
    s" - $numLendingProducts lending products" +
    s" - $numLendingAccounts lending accounts"
}
