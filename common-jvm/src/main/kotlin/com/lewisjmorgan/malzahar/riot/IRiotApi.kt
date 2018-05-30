package com.lewisjmorgan.malzahar.riot

import com.github.kittinunf.fuel.core.Response
import io.reactivex.Single

/**
 * Created by lewis on 5/29/18.
 */
interface IRiotApi {
  /**
   * Creates a request to the Riot API using the specified path and maps the response message to a
   * string for manipulation.
   * @param path String
   * @return Single<Result<String, FuelError>>
   */
  fun getJsonResponseString(path: String): Single<String>

  /**
   * Creates a request to the Riot API using the specified path and parameters. The response is then mapped
   * to a string for manipulation.
   * @param path String
   * @param params List<Pair<String, String>>
   * @return Single<String>
   */
  fun getJsonResponseString(path: String, params: List<Pair<String, Any>>): Single<String>

  /**
   * Creates a request to the API with the given path and parameters. Requests do not assume a thread
   * to subscribe to. Requests that throw a RiotRateLimitException will retry after the specified
   * amount of seconds within the received response header.
   * @param path String
   * @param params List<Pair<String, String>>
   * @return Single<Pair<Response, String>>
   */
  fun request(path: String, params: List<Pair<String, Any>>): Single<Pair<Response, String>>
}