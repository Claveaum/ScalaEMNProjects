package controllers

import java.util.UUID
import javax.inject._

import models.Task
import models.Task.taskFormat
import play.api.db.Database
import play.api.libs.iteratee.Enumeratee
import play.api.libs.json.{Json, Writes}
import play.api.mvc._

import scala.Option
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class Application @Inject()()(implicit database: Database, ec: ExecutionContext) extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def allTasks() = Action.async {
    Future {
      Task.findAll() match {
        case Seq() => Ok(Json.arr())
        case tasks:Seq[Task] => Ok(Json.arr(tasks map Task.taskFormat.writes))
      }
    }
  }

  def deleteAllDone() = Action.async {
    Future {
      Task.findAll() filter(_.done) foreach Task.delete
      Ok
    }
  }

  def changeTaskState(id: String) = Action.async(parse.urlFormEncoded) { r =>
    Future {
      //val done: Option[String] = r.body.getOrElse("done", Seq()).headOption
      //Ok(Json.toJson(Task.findById(UUID.fromString(id)) match { case Some(Task(id, name, oldDone)) => Task.update(new Task(id,name, done.get == "true"))}))
      val opt = Task.findById(UUID.fromString(id)).flatMap { task =>
        r.body.getOrElse("done", Seq()).headOption.map { done =>
          Task.update(task.copy(done = done.toBoolean))
        }
      }
      opt.map(t => Ok(Json.toJson(t))).getOrElse(NotFound)
    }
  }

  def createTask() = Action.async(parse.urlFormEncoded) { r =>
    r.body.getOrElse("name", Seq()).headOption
    match {
      case Some(x) =>
        Future {
          Ok(Json.toJson(Task.create(new Task(name = x, done = false))))
        }
      case None => Future {
        BadRequest("Name was not specified")
      }
    }
  }

  def getTask(id: String) = Action.async {
    Future {
      Ok(Json.toJson(Task.findById(UUID.fromString(id))))
    }
  }

  def deleteTask(id: String) = Action.async {
    Future {
      Task.delete(UUID.fromString(id))
      Ok(s"Delete $id done")
    }

  }

  def deleteAll() = Action.async {
    Future {
      Task.deleteAll()
      Ok("Delete all")
    }
  }
}
