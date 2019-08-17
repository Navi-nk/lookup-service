package com.navi.lookupservice.dao

import java.sql.{Connection, PreparedStatement, ResultSet, Statement}
import java.util

import com.navi.lookupservice.dao.repository.Repository
import com.navi.lookupservice.model.Lookup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.{JdbcTemplate, PreparedStatementCreator}
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component

@Component
class LookupDao @Autowired()(val jdbcTemplate: JdbcTemplate) extends Repository[Lookup]{

  override def find(id: Long): Lookup = {
    val result = jdbcTemplate.query("select * from lookup where id = ?",
      Array(id.asInstanceOf[AnyRef]),
      (rs: ResultSet, _: Int) => {
        val lookup: Lookup = new Lookup()
        lookup setId rs.getLong("id")
        lookup setIdentifier rs.getString("identifier")
        lookup setName rs.getString("name")
        lookup
      })
    result.size() match {
      case 1 => result.get(0)
      case _ => null
    }
  }

  override def findAll(): util.Collection[Lookup] = {
    jdbcTemplate.query("select * from lookup",
      (rs: ResultSet, _: Int) => {
        val lookup: Lookup = new Lookup()
        lookup setId rs.getLong("id")
        lookup setIdentifier rs.getString("identifier")
        lookup setName rs.getString("name")
        lookup
      })
  }

  override def save(value: Lookup): Long = {
    val holder = new GeneratedKeyHolder
    val preparedStmtCreator = new PreparedStatementCreator {
      override def createPreparedStatement(con: Connection): PreparedStatement = {
        val statement: PreparedStatement = con.prepareStatement("insert into lookup (identifier, name) values (?, ?) ", Statement.RETURN_GENERATED_KEYS)
        statement.setString(1, value.identifier)
        statement.setString(2, value.name)
        statement
      }
    }
    jdbcTemplate.update( preparedStmtCreator,holder)
    holder.getKey.longValue()
  }

  override def saveAll(args: util.Collection[Lookup]): Unit = ???

  override def update(value: Lookup): Lookup = ???

  override def delete(id: Long): Lookup = ???
}
