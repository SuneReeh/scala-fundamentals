import java.util.UUID

import com.h2.entities._

object BankOfScala {
  val random = new scala.util.Random()
  
  def main(args: Array[String]): Unit = {
    println("Opening Bank")

    val bank = new Bank(name = "Bank of Scala", country = "New Zealand", city = "Auckland", email = Email("bank", "scala.com"))
    val customerIds = getCustomers.map(c => bank.createNewCustomer(c._1, c._2, c._3, c._4))
    val depositProductIds = getDepositProducts.map(p => bank.addNewDepositProduct(p._1, p._2, p._3))
    val lendingProductIds = getLendingProducts.map(l => bank.addNewLendingProduct(l._1, l._2, l._3, l._4))

    /* logging */
    println(s"Bank: $bank")
    println(s"CustomerIds: $customerIds")
    println(s"Deposits Products Ids: $depositProductIds")
    println(s"LendingProductIds: $lendingProductIds")

    def openAccount(customerID: UUID, product: Product)(amount: Dollars = Dollars.Zero) = {
      product.category match {
        case ProductCategory.DepositP => bank.openDepositAccount(customerID, product.id, amount)
        case ProductCategory.LendingP => bank.openLendingAccount(customerID, product.id, amount)
      }
    }

    /*
    Bank clerk opening the account.
    There is no money deposited in the account yet, so the accounts are not active
    */
    val depositAccounts = for {
      c <- customerIds
      p <- depositProductIds
    } yield openAccount(c,bank.getDepositProduct(p).get)

    /* Depositing money into the accounts */
    val depositAccountIds = depositAccounts.map(account => account(Dollars(10000 + random.nextInt(10000))))


    /* logging */
    println(s"Deposits Accounts: $depositAccounts")
    println(s"Deposits Account Ids: $depositAccountIds")

    /*
     Open credit card accounts.
     The bank process has not finished the credit check, so, balance will be known later
    */
    val lendingAccounts = for {
      c <- customerIds
      p <- lendingProductIds
    } yield openAccount(c,bank.getLendingProduct(p).get)
    val lendingAccountIds = lendingAccounts.map(account => account(Dollars(random.nextInt(500))))

    /* logging */
    println(s"Lending Accounts: $lendingAccounts")
    println(s"Lending Account Ids: $lendingAccountIds")
    println(s"Bank: $bank")

    /*
      Performing Deposit Accounts transactions
     */
    depositAccountIds.foreach{accountID =>
      bank.deposit(accountID, Dollars(1 + random.nextInt(100)))
      bank.withdraw(accountID, Dollars(1 + random.nextInt(50)))
    }

    /*
      Performing Lending Accounts transactions
    */
    lendingAccountIds.foreach { accountID =>
      bank.useCreditCard(accountID, Dollars(1 + random.nextInt(500)))
      bank.payCreditCardBill(accountID, Dollars(1 + random.nextInt(100)))
    }

    /** Statistics */
    println("-" * 100)
    println("Statistics")
    println("-" * 100)

    val dAccounts = depositAccountIds.flatMap(bank.getDepositAccount)
    println(s"Total Money Deposited to Bank: ${bank.getTotalMoneyDeposited(dAccounts)}")

    val lAccounts = lendingAccountIds.flatMap(bank.getLendingAccount)
    println(s"Total Money Borrowed by Customers: ${bank.getTotalMoneyBorrowed(lAccounts)}")

    val allAccounts = dAccounts ++ lAccounts
    println(s"Number of Transactions By Account: ${bank.getNumTransactionsByAccount(allAccounts)}")
  }

  /* ------------------- Data ------------------- */
  def getCustomers: Seq[(String, String, String, String)] = {
    Seq(
      ("Bob", "Martin", "bob@martin.com", "1976/11/25"),
      ("Amy", "Jones", "amy.jones@google.com", "1983/4/12"),
      ("Taylor", "Jackson", "taylor33@jackson.com", "1985/4/5"),
      ("Rob", "Johnson", "rob@jphnson.com", "1976/3/6"),
      ("Maggie", "Harting", "maggie23@apple.com", "1979/12/3"),
      ("Deb", "Das", "debdas@ge.com", "1981/4/12"),
      ("Chris", "Baron", "chris@ibm.com", "1977/2/12"),
      ("Julie", "James", "julie@james.com", "1983/1/22"),
      ("Oscar", "Chin", "oscar@mama.com", "1982/4/12"),
      ("Sophie", "Smith", "sophie@fb.com", "1990/12/1")
    )
  }

  def getDepositProducts: Seq[(String, Int, Double)] = {
    Seq(
      ("CoreChecking", 1000, 0.025),
      ("StudentChecking", 0, 0.010),
      ("RewardsSavings", 10000, 0.10),
    )
  }

  def getLendingProducts: Seq[(String, Double, Double, Double)] = {
    Seq(("CreditCard", 99.00, 14.23, 20.00))
  }
}
