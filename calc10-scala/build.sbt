name := "calc10-scala"

version := "1.0.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
	"org.apache.commons" % "commons-lang3" % "3.2.1",
	"org.slf4j" % "slf4j-api" % "1.7.5",
	"ch.qos.logback" % "logback-core" % "1.0.13",
	"ch.qos.logback" % "logback-classic" % "1.0.13"
)

scalacOptions ++= Seq(
	"-language:postfixOps",
	"-feature",
	"-encoding",
	"UTF-8"
)
