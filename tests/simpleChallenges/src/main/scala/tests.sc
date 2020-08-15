def factorial(i: Int): Int = {
  if (i == 1) 1
  else i * factorial(i - 1)
}

factorial(4)

def ownFactorial(i: Int): Int = {
  (1 to i).foldLeft(1){case (acc, x) => acc * x}
}

def ownFactorial2(i: Int): Int = {
  (1 to i).reduce((acc, y) => acc * y)
}

ownFactorial(4)
ownFactorial2(4)

(1 to 4).product















































































































