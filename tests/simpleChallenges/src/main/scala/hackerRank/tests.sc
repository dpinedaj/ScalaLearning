var s = "hackerhappy"
var t = "hackerrank"
val k = 9
for (i <- 0 to 9) {
  if (s != t) {
    if (s.length > t.length) {
      s = s.dropRight(1)
      println(s)
    }
    else if (t.length > s.length) {
      t = t.dropRight(1)
      println(t)
    }else{
      s = s.dropRight(1)
      t = t.dropRight(1)
      println(t,s)
    }
  }
}


































