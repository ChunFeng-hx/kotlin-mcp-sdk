package io.modelcontextprotocol.kotlin.sdk.client

import kotlinx.io.Buffer

/**
 * Coroutine-friendly byte source used by stdio transports.
 *
 * Implementations are not required to support concurrent reads.
 */
public interface CoroutineStdioSource {
    /**
     * Reads at most [byteCount] bytes into [sink], suspending until data or EOF is available.
     *
     * @return the number of bytes read, or `-1` at EOF.
     */
    public suspend fun readAtMostTo(sink: Buffer, byteCount: Long): Long

    /** Closes the source and unblocks any suspended read. */
    public suspend fun close()
}

/**
 * Coroutine-friendly byte sink used by stdio transports.
 *
 * Implementations are not required to support concurrent writes.
 */
public interface CoroutineStdioSink {
    /** Writes exactly [byteCount] bytes from [source]. */
    public suspend fun write(source: Buffer, byteCount: Long)

    /** Flushes bytes accepted by this sink. */
    public suspend fun flush()

    /** Closes the sink and releases its resources. */
    public suspend fun close()
}
