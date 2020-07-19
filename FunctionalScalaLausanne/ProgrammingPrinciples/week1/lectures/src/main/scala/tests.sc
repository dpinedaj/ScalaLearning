val money = 4
val coins = List(1,2)

def countChange(money: Int, coins: List[Int]): Int =
{
  if (money < 0 || coins.isEmpty) 0
  else if (money == 0) 1
  else{
    val h = coins.head
    countChange(money - h, coins) + countChange(money, coins.tail)
  }
}

countChange(money, coins)







