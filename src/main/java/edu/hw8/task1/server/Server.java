package edu.hw8.task1.server;

import edu.hw8.task1.Storage;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.task1.Utils.getAns;

@SuppressWarnings("UncommentedMain")
public class Server {

    private Server() {

    }

    private static final int PORT = 8080;
    private static final String HOST = "localhost";
    private static final Storage KVSTORAGE = new Storage();
    private final static int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final AtomicInteger ACTIVE = new AtomicInteger(0);
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BUFFER_CAPACITY = 200;
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newWorkStealingPool(PROCESSORS);

    public static void main(String[] args) {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(new InetSocketAddress(HOST, PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        if (ACTIVE.get() < PROCESSORS) {
                            SocketChannel client = serverSocketChannel.accept();
                            client.configureBlocking(false);
                            EXECUTOR_SERVICE.submit(() -> {
                                ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_CAPACITY);
                                try {
                                    while (client.read(byteBuffer) != -1) {
                                        String request = getAns(byteBuffer, client);
                                        client.write(ByteBuffer.wrap(KVSTORAGE.getReply(request.substring(
                                                0,
                                                request.length() - 1
                                            ))
                                            .getBytes(StandardCharsets.UTF_16)));
                                    }

                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } finally {
                                    ACTIVE.decrementAndGet();

                                }

                            });
                            ACTIVE.incrementAndGet();
                        } else {
                            LOGGER.info("Client is waiting.");

                        }
                    }

                    iter.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            EXECUTOR_SERVICE.shutdown();
        }
    }
}


