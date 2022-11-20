package com.mmc.playground;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

@ExtendWith({ContainerizedMongoDbTest.MongoExtension.class})
public class ContainerizedMongoDbTest {

    @Container
    public static MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer(DockerImageName.parse("mongo:5.0.13"))
            .withReuse(true);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
    }

    public static class MongoExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {
        private boolean started;

        @Override
        public void beforeAll(ExtensionContext extensionContext) throws Exception {
            if (!started) {
                MONGO_DB_CONTAINER.start();
                started = true;
                extensionContext.getRoot()
                        .getStore(GLOBAL)
                        .put("some unique name", this);
            }
        }

        @Override
        public void close() throws Throwable {
            MONGO_DB_CONTAINER.stop();
        }
    }
}
