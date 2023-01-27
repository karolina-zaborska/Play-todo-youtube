package controllers

import models.TaskInMemoryModel
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.mvc._

import javax.inject._  //import required to satisfy compiler while using "@Signeton" keyword

/*we include @Inject and extending the properties of the controller so that is actually the controller*/
@Singleton
class Task1 @Inject()(cc: ControllerComponents) extends AbstractController(cc){
  //we are putting login above the task list because when you use the application it will come first
  def login = Action {
    Ok(views.html.login1())
  }

  def validateLoginGet(username:String, password:String) = Action {
    Ok(s"$username logged in with $password")
  }

  def validateLoginPost = Action { request =>
    //right now we are not really taking into account the possibility that it might not exist, as its an Option
    //parsing the body using asFormUrlEncoded
    val postVals = request.body.asFormUrlEncoded
    postVals.map {args =>
      val username = args("username").head
      val password = args("password").head

      if(TaskInMemoryModel.validateUser(username, password)) {
        //we need to validate, and if the passed arguments are correct just proceed to the list of tasks so the Ok response needs to be modified, cause Ok(s"$username logged in with $password") is not a correct response.
        Redirect(routes.Task1.taskList()) // routes.Task1.taskList()) is going to evaluate

      } else {
        Redirect(routes.Task1.login())
      }
    }.getOrElse(Redirect(routes.Task1.login())) //reverse routing
  }


  //now time for the method - Action requires the result in them, redirect or bad request or ok
  def taskList = Action {
    val tasks = List("task1", "task2", "task3","sleep","eat")
    Ok(views.html.taskList1(tasks))
  }

  /*We are changing the Ok("This works!") to calling the view template for taskList1, by saying
  * Ok(views.html.taskList1(Nil)) */
}



