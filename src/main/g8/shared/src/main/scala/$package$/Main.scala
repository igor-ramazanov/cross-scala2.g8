package $package$

import zio.console._

trait ZioMain extends zio.App {

  def run(args: List[String]) = myAppLogic(args).exitCode

  def myAppLogic(args: List[String]) =
    for {
      _    <- putStrLn(args.mkString(" "))
      _    <- putStrLn("Hello! What is your name?")
      name <- getStrLn
      _    <- putStrLn(s"Hello, \${name}, welcome to ZIO!")
    } yield ()
}
