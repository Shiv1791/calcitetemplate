#Apache Calcite
Apache Calcite is a dynamic data management framework.

It contains many of the pieces that comprise a typical database management system but omits the storage primitives. It provides an industry standard SQL parser and validator, a customisable optimizer with pluggable rules and cost functions, logical and physical algebraic operators, various transformation algorithms from SQL to algebra (and the opposite), and many adapters for executing SQL queries over Cassandra, Druid, Elasticsearch, MongoDB, Kafka, and others, with minimal configuration.

#Important note
In this project we used the BABEL parser through which we can parse the database query of different databases like Postgres, Oracle and SqlServer etc.

#How to parse database query
`parseSql(sql: String)`
1. Created the method which takes the database query as a string
2. It return the `SqlNode` if the database query is syntactically correct.
3. If the query query is looks good and not syntactically correct then it will throw exception.
