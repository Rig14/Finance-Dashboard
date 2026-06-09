package db

import klite.Config
import klite.Config.set
import klite.isTest
import klite.jdbc.DBMigrator
import klite.jdbc.useAppDBUser

abstract class DBTest: klite.jdbc.DBTest() {
  companion object {
    init {
      Config["DB_URL"] += "_test"
      DBMigrator().apply {
        if (!Config.isTest) error("Should be test context, but is " + Config.active)
        migrate()
      }
      useAppDBUser()
    }
  }
}
