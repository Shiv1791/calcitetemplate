package com.knoldus.calcite

import org.apache.calcite.sql.SqlNode
import org.apache.calcite.sql.parser.babel.SqlBabelParserImpl
import org.apache.calcite.sql.parser.{SqlParseException, SqlParser => CalciteParser}
import org.apache.calcite.sql.validate.SqlConformanceEnum

object SqlParser {
  def parseSql(sql: String): SqlNode =
    try {
      val sqlParserConfig = CalciteParser
        .configBuilder()
        .setParserFactory(SqlBabelParserImpl.FACTORY)
        .setConformance(SqlConformanceEnum.BABEL)
        .build()
      CalciteParser.create(sql, sqlParserConfig).parseQuery()

    } catch {
      case e: SqlParseException =>
        e.printStackTrace()
        throw new Exception("Did not parsed the query")
    }
}