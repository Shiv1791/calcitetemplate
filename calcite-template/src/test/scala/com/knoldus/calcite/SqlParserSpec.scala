package com.knoldus.calcite

import org.apache.calcite.sql.SqlNode
import org.scalatest.{Matchers, WordSpec}

class SqlParserSpec extends WordSpec with Matchers {

  val insertQuery: String =
    s"""
       |INSERT INTO test_table (id, name, ownerId, creation_time, periodicity, expiry_time,
       | last_refreshed, query, orig_query, description, additional_info, last_edit_timestamp, load, load_category, enabled, system, intel, system_state, system_error, score)
       | VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::system_state_type, ?, ?)
        """.stripMargin


  val selectQuery: String =
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


  val updateQuery: String =
    s"""
              UPDATE query.test_table
              SET system_state = 'Blocked'::system_state_type,
                  system_error = 123,
                  enabled      = FALSE
              WHERE uid = '0000'::uuid
            """.stripMargin


  val invalidQuery: String =
    s"""
              UPDATE query.test_table
              SET system_state = 'Blocked'::system_state_type,
                  system_error = 123,
                  enabled      = FALSE
              WHERE uid = '0000'::uuid;
            """.stripMargin


  "SqlParser" should {
    "parse insert successfully: " in {
      assert(SqlParser.parseSql(insertQuery).isInstanceOf[SqlNode])
    }
    "parse update successfully: " in {
      assert(SqlParser.parseSql(updateQuery).isInstanceOf[SqlNode])
    }
    "parse select successfully: " in {
      assert(SqlParser.parseSql(selectQuery).isInstanceOf[SqlNode])
    }
    "throw exception when parse invalid query: " in {
      val throwsException = intercept[Exception] {
        SqlParser.parseSql(invalidQuery)
      }
      assert(throwsException.getMessage === s"Did not parsed the query")
    }

  }
}
