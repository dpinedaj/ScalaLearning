package utils


object StopWords {
    val words:Set[String] = MyFiles.readFile("utils/utilData/stopWords.txt").toSet

    
}