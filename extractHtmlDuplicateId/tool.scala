import scala.io.Source

object Extract {
  def extractId(s: String): String = {
    val search = "id="
    val i1 = s.indexOf(search)
    val s2 = s.substring(i1 + search.length + 1)
    val i2 = s2.indexOf("\"")
    s2.substring(0, i2)
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("missing filename argument.")
      System.exit(-1)
    }
   val filename = args(0)
   var l = List[String]()
   for (line <- Source.fromFile(filename).getLines) {
     if (line.contains("id=")) {
       l = extractId(line)::l
     }
   }
   l.groupBy(identity).filter { case (k, v) => v.size >= 2 }.map { case (k, v) => println(k + " -> " + v.size + " x") }
  }
}
