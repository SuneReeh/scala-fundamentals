package com.h2.entities

class Email(val value: String, val domain: String) {
  override def toString: String = s"$value@$domain"
}

object Email {
  def apply(value: String, domain: String): Email = new Email(value, domain)
}
