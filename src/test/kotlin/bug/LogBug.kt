package bug

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.UserAgent
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert
import org.junit.Test
import kotlin.system.measureTimeMillis

class LogBug {

    private val timeout = 5000L

    @Test
    fun withLogInfo() {
        val duration = measureTimeMillis {
            runBlocking {
                withTimeout(timeout) {
                    val client = createClient(LogLevel.INFO)
                    client.get<String>("https://www.google.de")
                }
            }
        }

        Assert.assertTrue("$duration < $timeout", duration < timeout)
    }

    @Test
    fun withLogNone() {
        val duration = measureTimeMillis {
            runBlocking {
                withTimeout(timeout) {
                    val client = createClient(LogLevel.NONE)
                    client.get<String>("https://www.google.de")
                }
            }
        }

        Assert.assertTrue("$duration < $timeout", duration < timeout)
    }

    @Test
    fun withoutLog() {
        val duration = measureTimeMillis {
            runBlocking {
                withTimeout(timeout) {
                    val client = createClientWithoutLog()
                    client.get<String>("https://www.google.de")
                }
            }
        }

        Assert.assertTrue("$duration < $timeout", duration < timeout)
    }

    private fun createClientWithoutLog(): HttpClient {
        return HttpClient(Apache) {
            expectSuccess = true
            install(UserAgent) { agent = "KTOR/BUG" }
        }
    }

}

private fun createClient(logLevel: LogLevel): HttpClient {
    return HttpClient(Apache) {
        expectSuccess = true
        install(UserAgent) { agent = "KTOR/BUG" }
        install(Logging) {
            level = logLevel
        }
    }
}