package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._

class AddUserSimulation extends Simulation {

  val httpConf = http.baseUrl("https://reqres.in/")
    .header("Accept", "application/json")
    .header("content-type", "application/json")

  val scn = scenario("Add User Scenario")
    .exec(http("add user request")
    .post("/api/users")
    .body(RawFileBody("./src/test/resources/bodies/AddUser.json"))
    .asJson
    .header("content-type", "application/json")
    .check(status is 201))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)


}
