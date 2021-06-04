package simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._


class TestAPI extends Simulation
{
  //http conf
  val httpConf = http.baseUrl("https://reqres.in/")
    .header("Accept", "applicatioin/json")
    .header("content-type", "applicatioin/json")
  //scenario
  val scn = scenario("get user")
    .exec(http("get user request")
      .get("/api/users/2")
      .check(status is 200))
  //setup
  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)
}