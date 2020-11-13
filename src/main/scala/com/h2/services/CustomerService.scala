package com.h2.services

import java.time.LocalDate
import java.util.UUID

import com.h2.entities._

trait CustomerService extends CustomerDb {

  /**
    * @param first       : first name for the customer
    * @param last        : last name for the customer
    * @param email       : email for the customer in 'value@domain' format
    * @param dateOfBirth : : date of birth for the customer in 'yyyy/mm/dd' format
    * @return the customer id for the new customer
    */
  def createNewCustomer(first: String, last: String, email: String, dateOfBirth: String): UUID = {
    def getEmail: Email =
      val Array(value, domain) = email.split("@")
      Email(value,domain)

    def getDateOfBirth: LocalDate =
      val Array(year, month, day) = dateOfBirth.split("/")
      LocalDate.of(year.toInt, month.toInt, day.toInt)

    val customer = new Customer(first, last, getEmail, getDateOfBirth)
    saveCustomer(customer)
    customer.id
  }
}
