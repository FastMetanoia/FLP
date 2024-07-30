import java.io.File
import scala.io.Source
import scala.util.{Success, Try}

object Test extends App{
  def countLines(fileNames:List[String]):Long = {
    fileNames
      .flatMap(name => Try{
        ((x:String)=>new File(x))
          .andThen(Source.fromFile)
          .andThen(_.getLines().length)(name)
      }.toOption)
      .sum
  }

  println(s"countLines(List()) = ${countLines(List())}")
}
