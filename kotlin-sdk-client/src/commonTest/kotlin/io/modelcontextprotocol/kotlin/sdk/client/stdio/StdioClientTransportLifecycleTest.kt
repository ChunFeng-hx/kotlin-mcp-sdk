package io.modelcontextprotocol.kotlin.sdk.client.stdio

import io.modelcontextprotocol.kotlin.sdk.client.AbstractClientTransportLifecycleTest
import io.modelcontextprotocol.kotlin.sdk.client.CoroutineStdioSink
import io.modelcontextprotocol.kotlin.sdk.client.CoroutineStdioSource
import io.modelcontextprotocol.kotlin.sdk.client.StdioClientTransport
import kotlinx.io.Buffer
import kotlin.test.Ignore
import kotlin.test.Test

class StdioClientTransportLifecycleTest : AbstractClientTransportLifecycleTest<StdioClientTransport>() {

    /**
     * Dummy method to make IDE treat this class as a test
     */
    @Test
    @Ignore
    fun dummyTest() = Unit

    override fun createTransport(): StdioClientTransport {
        val inputBuffer = Buffer()
        val outputBuffer = Buffer()
        return StdioClientTransport(
            input = BufferCoroutineStdioSource(inputBuffer),
            output = BufferCoroutineStdioSink(outputBuffer),
        )
    }
}

private class BufferCoroutineStdioSource(private val delegate: Buffer) : CoroutineStdioSource {
    override suspend fun readAtMostTo(sink: Buffer, byteCount: Long): Long = delegate.readAtMostTo(sink, byteCount)

    override suspend fun close() {
        delegate.close()
    }
}

private class BufferCoroutineStdioSink(private val delegate: Buffer) : CoroutineStdioSink {
    override suspend fun write(source: Buffer, byteCount: Long) {
        delegate.write(source, byteCount)
    }

    override suspend fun flush() {
        delegate.flush()
    }

    override suspend fun close() {
        delegate.close()
    }
}
