package examples.extra


case class Customer(firstName: String = "", lastName: String = "")

object Volatile {
  // The @volatile annotation must be used to ensure that all threads see your updates
  @volatile var customer = Customer("Martin", "odersky")
  customer = customer.copy(lastName = "Doe")
}
