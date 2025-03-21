package wisp.launchdarkly

import com.launchdarkly.sdk.server.Components
import com.launchdarkly.sdk.server.LDClient
import com.launchdarkly.sdk.server.LDConfig
import com.launchdarkly.sdk.server.integrations.EventProcessorBuilder.DEFAULT_CAPACITY
import com.launchdarkly.sdk.server.interfaces.LDClientInterface
import wisp.resources.ResourceLoader
import wisp.security.ssl.SslContextFactory
import wisp.security.ssl.SslLoader
import java.net.URI
import java.time.Duration
import javax.net.ssl.X509TrustManager

object LaunchDarklyClient {

    /**
     * Creates the LaunchDarkly client interface with the supplied config
     */
    fun createLaunchDarklyClient(
        config: LaunchDarklyConfig,
        sslLoader: SslLoader,
        sslContextFactory: SslContextFactory,
        resourceLoader: ResourceLoader
    ): LDClientInterface {
        val baseUri = URI.create(config.base_uri)
        val ldConfig = LDConfig.Builder()
            .offline(config.offline)
            // Set wait to 0 to not block here. Block in service initialization instead.
            .startWait(Duration.ofMillis(0))
            .serviceEndpoints(
                Components.serviceEndpoints()
                    .streaming(baseUri)
                    .events(baseUri)
            )
            .events(
              Components.sendEvents()
                .capacity(config.event_capacity)
                .flushInterval(config.flush_interval)
            )

        config.ssl?.let {
            val trustStore = sslLoader.loadTrustStore(config.ssl.trust_store)!!
            val trustManagers = sslContextFactory.loadTrustManagers(trustStore.keyStore)
            val x509TrustManager = trustManagers.mapNotNull { it as? X509TrustManager }.firstOrNull()
                ?: throw IllegalStateException("no x509 trust manager in ${it.trust_store}")
            val sslContext = sslContextFactory.create(it.cert_store, it.trust_store)
            var httpConfiguration = Components.httpConfiguration()
                .sslSocketFactory(sslContext.socketFactory, x509TrustManager)
            val proxyHost = System.getProperty("http.proxyHost")
            if (proxyHost != null) {
                httpConfiguration = httpConfiguration.proxyHostAndPort(
                    proxyHost,
                    System.getProperty("http.proxyPort", "3128").toInt()
                )
            }
            ldConfig.http(httpConfiguration)
        }

        return LDClient(resourceLoader.requireUtf8(config.sdk_key).trim(), ldConfig.build())
    }
}

