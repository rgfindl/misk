import com.vanniktech.maven.publish.JavadocJar.Dokka
import com.vanniktech.maven.publish.KotlinJvm

plugins {
  alias(libs.plugins.kotlinJvm)
  alias(libs.plugins.mavenPublishBase)
  id("java-test-fixtures")
}

dependencies {
  api(libs.gax)
  api(libs.gcpCloudCore)
  api(libs.googleCloudStorage)
  api(libs.gcpDatastore) {
    exclude(group = "com.google.protobuf")
    exclude(group = "com.google.api.grpc")
    exclude(group = "io.grpc")
  }
  api(libs.gcpKms)
  api(libs.gcpLogging)
  api(libs.gcpSpanner)
  api(libs.googleApiServicesStorage)
  api(libs.googleAuthLibraryCredentials)
  api(libs.guava)
  api(libs.guice)
  api(libs.jakartaInject)
  api(libs.logbackClassic)
  api(libs.moshiCore)
  api(libs.okio)
  api(libs.openTracing)
  api(project(":wisp:wisp-config"))
  api(project(":wisp:wisp-deployment"))
  api(project(":misk-config"))
  api(project(":misk-inject"))
  implementation(libs.gcpLogback)
  implementation(libs.googleApiClient)
  implementation(libs.googleAuthLibraryOauth2Http)
  implementation(libs.googleCloudCoreHttp)
  implementation(libs.googleHttpClient)
  implementation(libs.googleHttpClientJackson)
  implementation(libs.loggingApi)
  implementation(libs.logbackCore)
  implementation(libs.openTracingDatadog)
  implementation(libs.slf4jApi)
  implementation(libs.threeTenBp)
  implementation(libs.wireRuntime)
  implementation(project(":wisp:wisp-logging"))
  implementation(project(":wisp:wisp-moshi"))
  implementation(project(":misk"))
  implementation(project(":misk-service"))

  testFixturesApi(libs.dockerApi)
  testFixturesApi(libs.dockerCore)
  testFixturesApi(libs.dockerTransport)
  testFixturesApi(libs.findbugsJsr305)
  testFixturesApi(libs.gcpCloudCore)
  testFixturesApi(libs.googleCloudStorage)
  testFixturesApi(libs.gcpDatastore) {
    exclude(group = "com.google.protobuf")
    exclude(group = "com.google.api.grpc")
    exclude(group = "io.grpc")
  }
  testFixturesApi(libs.googleApiServicesStorage)
  testFixturesApi(libs.googleHttpClient)
  testFixturesApi(libs.guice)
  testFixturesApi(libs.jakartaInject)
  testFixturesApi(libs.loggingApi)
  testFixturesApi(project(":misk-gcp"))
  testFixturesApi(project(":misk-inject"))
  testFixturesImplementation(libs.assertj)
  testFixturesImplementation(libs.dockerTransportCore)
  testFixturesImplementation(libs.gax)
  testFixturesImplementation(libs.gcpSpanner)
  testFixturesImplementation(libs.googleAuthLibraryCredentials)
  testFixturesImplementation(libs.googleHttpClientJackson)
  testFixturesImplementation(libs.junitApi)
  testFixturesImplementation(libs.kotlinRetry)
  testFixturesImplementation(libs.kotlinTest)
  testFixturesImplementation(libs.kotlinxCoroutinesCore)
  testFixturesImplementation(libs.moshiCore)
  testFixturesImplementation(project(":wisp:wisp-containers-testing"))
  testFixturesImplementation(project(":wisp:wisp-moshi"))
  testFixturesImplementation(project(":misk-service"))
  testFixturesImplementation(project(":misk-testing"))

  testImplementation(libs.assertj)
  testImplementation(libs.dockerApi)
  testImplementation(libs.dockerCore)
  testImplementation(libs.dockerTransport)
  testImplementation(libs.dockerTransportCore)
  testImplementation(libs.junitApi)
  testImplementation(libs.kotlinTest)
  testImplementation(libs.openTracingDatadog)
  testImplementation(project(":wisp:wisp-containers-testing"))
  testImplementation(project(":wisp:wisp-tracing"))
  testImplementation(project(":misk-gcp"))
  testImplementation(project(":misk-testing"))
  testImplementation(testFixtures(project(":misk-gcp")))

  testImplementation(libs.assertj)
  testImplementation(libs.dockerTransportCore)
  testImplementation(libs.gax)
  testImplementation(libs.gcpSpanner)
  testImplementation(libs.googleAuthLibraryCredentials)
  testImplementation(libs.googleHttpClientJackson)
  testImplementation(libs.junitApi)
  testImplementation(libs.kotlinRetry)
  testImplementation(libs.kotlinTest)
  testImplementation(libs.kotlinxCoroutinesCore)
  testImplementation(libs.moshiCore)
  testImplementation(project(":wisp:wisp-containers-testing"))
  testImplementation(project(":wisp:wisp-moshi"))
  testImplementation(project(":misk-service"))
  testImplementation(project(":misk-testing"))

  testFixturesImplementation(libs.gcpLogback)
  testFixturesImplementation(libs.googleApiClient)
  testFixturesImplementation(libs.googleAuthLibraryOauth2Http)
  testFixturesImplementation(libs.googleCloudCoreHttp)
  testFixturesImplementation(libs.googleHttpClient)
  testFixturesImplementation(libs.googleHttpClientJackson)
  testFixturesImplementation(libs.loggingApi)
  testFixturesImplementation(libs.logbackCore)
  testFixturesImplementation(libs.openTracingDatadog)
  testFixturesImplementation(libs.slf4jApi)
  testFixturesImplementation(libs.threeTenBp)
  testFixturesImplementation(libs.wireRuntime)
  testFixturesImplementation(project(":wisp:wisp-logging"))
  testFixturesImplementation(project(":wisp:wisp-moshi"))
  testFixturesImplementation(project(":misk"))
  testFixturesImplementation(project(":misk-service"))
}

mavenPublishing {
  configure(
    KotlinJvm(javadocJar = Dokka("dokkaGfm"))
  )
}
