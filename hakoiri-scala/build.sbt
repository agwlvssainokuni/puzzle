name := "hakoiri-scala"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
	"org.slf4j" % "slf4j-api" % "1.7.3",
	"ch.qos.logback" % "logback-classic" % "1.0.11"
)

scalacOptions ++= Seq(
	"-language:postfixOps",
	"-feature",
	"-encoding",
	"UTF-8"
)
