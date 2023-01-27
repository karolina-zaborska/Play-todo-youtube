package models

import collection.mutable


//creating a model that will sit in our memory.
//The model will store information on our users and their tasks.
object TaskInMemoryModel {
 /* What we need first?
   1.validate the user*/

  // Map that takes a user name and gives you back a password
  private val  users = mutable.Map[String,String]("Mark"->"pass")

  //validation of our user - boolean telling us whether they are a valid user
  //.get returns to us an option of our string. We map that to the value, so either map if the password is the match, if not we get false in return
  def validateUser(username:String, password:String):Boolean = {
    users.get(username).map(_ == password).getOrElse(false)
  }

  //creation of our user
  def createUser(username:String, password:String):Boolean = ???

  def getTasks(username:String):Seq[String] = ???

  def addTask(username:String, task:String):Unit = ???

  //how we can point on the task itself?
  def removeTask(username:String,index:Int):Boolean = ???

}

