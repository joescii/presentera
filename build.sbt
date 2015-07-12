name := "presentera"

version := "0.1.0"

organization := "net.liftweb"

scalaVersion := "2.10.4"

resolvers ++= Seq(
  "snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"      at "http://oss.sonatype.org/content/repositories/releases"
)

seq(webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5.1"
  Seq(
    "net.liftweb"             %% "lift-webkit"            % liftVersion           % "compile",
    "net.liftmodules"         %% "lift-jquery-module_2.5" % "2.4",
    "org.eclipse.jetty"       %  "jetty-webapp"           % "8.1.7.v20120910"     % "container,test",
    "org.eclipse.jetty.orbit" %  "javax.servlet"          % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"          %  "logback-classic"        % "1.0.6",
    "org.scalatest"           %% "scalatest"              % "2.2.4"               % "test->*",
    "org.seleniumhq.selenium" %  "selenium-java"          % "2.46.0"              % "test"
  )
}

initialize~= { _ =>
  System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe")
}

(Keys.test in Test) <<= (Keys.test in Test) dependsOn (start in container.Configuration)
