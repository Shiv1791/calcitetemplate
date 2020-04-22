import sbt.Keys._
import sbt.{Def, _}


object BuildSettings {

  val projectSettings: Seq[Def.Setting[_]] = Defaults.coreDefaultSettings ++
    Seq(
      organization in ThisBuild := "com.knoldus",
      scalaVersion in ThisBuild := "2.12.6",
      version := "0.0.1",
    )

  def BaseProject(name: String): Project =
    Project(name, file(s"$name"))
      .settings(projectSettings: _*)
      .settings(Defaults.itSettings)

}
