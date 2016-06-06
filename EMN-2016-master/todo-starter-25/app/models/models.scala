package models

import java.util.UUID

import anorm.SqlParser._
import anorm._
import play.api.db.Database
import play.api.libs.functional.syntax._
import play.api.libs.json._

import scala.Option

case class Task(id: String = UUID.randomUUID().toString, name: String, done: Boolean)

object Task {


  implicit val taskFormat: Format[Task] = new Format[Task] {

    override def reads(json: JsValue): JsResult[Task] = {
      try {
        JsSuccess(new Task(
          (json \ "id").as[String],
          (json \ "name").as[String],
          (json \ "done").as[Boolean]
        ))
      } catch {
        case e => JsError("error")
      }
    }

    override def writes(o: Task): JsValue = {
      Json.obj(
        "id" -> o.id,
        "name" -> o.name,
        "done" -> o.done
      )
    }
  }

  val parser: RowParser[Task] = str("id") ~ str("name") ~ bool("done") map {
    case id ~ name ~ done => Task(id, name, done)
  }

  def findAll()(implicit db: Database): List[Task] = db.withConnection { implicit connection =>
    SQL("select * from task").as(parser *)
  }

  def findById(id: UUID)(implicit db: Database): Option[Task] = db.withConnection { implicit connection =>
    val task = SQL("select * from task where id = {id} ;").on('id -> id).as(parser single)
    Option(task)
  }

  def create(model: Task)(implicit db: Database): Task = db.withConnection { implicit connection =>
    model match {
      case Task(id, name, done) => val task = SQL("insert into task values ( {id}, {name}, {done} );")
        .on('id -> id, "name" -> name, 'done -> done).executeInsert(); model
    }
  }

  def delete(model: Task)(implicit db: Database): Unit = db.withConnection { implicit connection =>
    model match {
      case Task(id, name, done) => val task = SQL("delete from task where id = {id} and name = {name} and done = {done};")
        .on('id -> id, "name" -> name, 'done -> done).execute; task
    }
  }

  def delete(id: UUID)(implicit db: Database): Unit = db.withConnection { implicit connection =>
    val task = SQL("delete from task where id = {id};")
      .on('id -> id).execute
  }

  def deleteAll()(implicit db: Database): Unit = db.withConnection { implicit connection =>
    SQL("delete from task").execute
  }

  def update(model: Task)(implicit db: Database): Task = db.withConnection { implicit connection =>
    model match {
      case Task(id, name, done) => SQL("UPDATE task SET name={name}, done={done} WHERE ID={id};")
        .on('id -> id, 'name -> name, 'done -> done).executeUpdate() match {
        case 1 => model
        case _ => throw new IllegalArgumentException
      }
    }
  }

  def count()(implicit db: Database): Long = db.withConnection { implicit connection =>
    SQL("select * from task;").as(parser.*) length
  }

  def exists(model: Task)(implicit db: Database): Boolean = db.withConnection { implicit connection =>
    model match
    {
      case Task(id, name, done) =>
        SQL("select * from task where id = {id} and name = {name} and done = {done} ;")
          .on('id -> id, 'name -> name, 'done -> done).as(parser.singleOpt).isDefined

    }
  }

}