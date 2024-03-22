package com.laioffer.source.okhttp3.internal.connection

import com.laioffer.source.okhttp3.Route

/**
 * A blacklist of failed routes to avoid when creating a new connection to a target address. This is
 * used so that OkHttp can learn from its mistakes: if there was a failure attempting to connect to
 * a specific IP address or proxy server, that failure is remembered and alternate routes are
 * preferred.
 */
class RouteDatabase {
  private val failedRoutes = mutableSetOf<Route>()

  /** Records a failure connecting to [failedRoute]. */
  @Synchronized fun failed(failedRoute: Route) {
    failedRoutes.add(failedRoute)
  }

  /** Records success connecting to [route]. */
  @Synchronized fun connected(route: Route) {
    failedRoutes.remove(route)
  }

  /** Returns true if [route] has failed recently and should be avoided. */
  @Synchronized fun shouldPostpone(route: Route): Boolean = route in failedRoutes
}
