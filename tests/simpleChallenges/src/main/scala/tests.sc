def pascal(k: Int): Unit = {
    for (row <- 1 to k){
        for (column <- 1 to k){
            print(iterPascal(row, column, k))
        }
        println()
    }
}

def iterPascal(row: Int, column: Int, k: Int): Int = {
    if (column == 1 || column == k) 1
    else iterPascal(row - 1, column - 1, k) + iterPascal(row, column - 1, k)
}


pascal(4)










































































































