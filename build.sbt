name := "Akka playground"
version := "1.0"
scalaVersion := "2.12.6"
lazy val akkaVersion = "2.5.25"


libraryDependencies ++= Seq(
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "0.55.2" % Compile,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.lihaoyi" % "ammonite" % "1.6.9-19-827dffe" % "test" cross CrossVersion.full,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main.main(args) }""")
  Seq(file)
}.taskValue
