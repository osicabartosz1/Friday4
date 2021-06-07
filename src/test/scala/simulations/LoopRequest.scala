package simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

class LoopRequest extends Simulation{

  val httpConf = http.baseUrl("https://reqres.in/")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  def getAllUsersRequest(): ChainBuilder={
    repeat(2){
      exec(http("get all users request")
      .get("/api/users?page=2")
      .check(status.is(200)))
    }
  }
  def getAUsersRequest(): ChainBuilder={
    repeat(2){
      exec(http("get a users request")
      .get("api/users/2")
      .check(status.is(200)))
    }
  }

  def addUser():ChainBuilder = {
    repeat(2) {

      exec(http("add a user request")
        .post("api/users")
        .body(RawFileBody("./src/test/resources/bodies/AddUser.json"))
        .check(status.is(201)))
    }
  }
  val scn = scenario("user request scenario")
    .exec(getAllUsersRequest())
    .pause(2)
    .exec(getAUsersRequest())
    .pause(2)
    .exec(addUser())

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}


