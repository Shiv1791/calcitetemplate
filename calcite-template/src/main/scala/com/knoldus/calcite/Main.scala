package com.knoldus.calcite

object Main {

  def main(args: Array[String]): Unit = {
    val insertQuery =
      s"""
         |INSERT INTO test_table (id, name, ownerId, creation_time, periodicity, expiry_time,
         | last_refreshed, query, orig_query, description, additional_info, last_edit_timestamp, load, load_category, enabled, system, intel, system_state, system_error, score)
         | VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::system_state_type, ?, ?)
        """.stripMargin

    SqlParser.parseSql(insertQuery)

    val selectQuery =
      s"""
             SELECT
                 id
               , last_refreshed
               , load
               , load_category
               , enabled
               , system
               , intel
               , system_state
               , system_error
               , translated_query
               , definition_uuid_
               , definition_uid
             FROM query.test_table
             WHERE query.test_table.uid = ?
           """.stripMargin
    SqlParser.parseSql(selectQuery)

    val updateQuery =
      s"""
              UPDATE query.test_table
              SET system_state = 'Blocked'::system_state_type,
                  system_error = 123,
                  enabled      = FALSE
              WHERE uid = '0000'::uuid
            """.stripMargin
    SqlParser.parseSql(updateQuery)
  }
}
