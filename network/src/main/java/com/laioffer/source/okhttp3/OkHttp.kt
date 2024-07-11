package com.laioffer.source.okhttp3

object OkHttp {
  /**
   * This is a string like "4.5.0-RC1", "4.5.0", or "4.6.0-SNAPSHOT" indicating the version of
   * OkHttp in the current runtime. Use this to include the OkHttp version in custom `User-Agent`
   * headers.
   *
   * Official OkHttp releases follow [semantic versioning][semver]. Versions with the `-SNAPSHOT`
   * qualifier are not unique and should only be used in development environments. If you create
   * custom builds of OkHttp please include a qualifier your version name, like "4.7.0-mycompany.3".
   * The version string is configured in the root project's `build.gradle`.
   *
   * Note that OkHttp's runtime version may be different from the version specified in your
   * project's build file due to the dependency resolution features of your build tool.
   *
   * [semver]: https://semver.org
   */
  const val VERSION = "4.9.3"
}
