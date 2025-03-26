/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.storage.expression.function;

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.FakeStorageStoreContext;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageStore;
import walkingkooka.storage.StorageStores;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;

import java.time.LocalDateTime;
import java.util.Optional;

public final class StorageExpressionFunctionWriteTextTest implements ExpressionFunctionTesting<StorageExpressionFunctionWriteText<StorageExpressionEvaluationContext>, Void, StorageExpressionEvaluationContext> {

    private final static StoragePath PATH = StoragePath.parse("/dir1/file2.txt");

    private final static String TEXT = "Hello World";

    @Test
    public void testApplyStorageEntryPresent() {
        final StorageStore store = StorageStores.tree(
            new FakeStorageStoreContext() {
                @Override
                public LocalDateTime now() {
                    return LocalDateTime.of(1999, 12, 31, 12, 58, 59);
                }

                @Override
                public Optional<EmailAddress> user() {
                    return Optional.of(
                        EmailAddress.parse("user@example.com")
                    );
                }
            }
        );

        this.applyAndCheck(
            StorageExpressionFunctionWriteText.instance(),
            Lists.of(
                PATH,
                TEXT
            ),
            this.createContext(store),
            null
        );

        this.checkEquals(
            TEXT,
            store.loadOrFail(PATH)
                .value()
                .orElse(null)
        );
    }

    @Override
    public StorageExpressionFunctionWriteText<StorageExpressionEvaluationContext> createBiFunction() {
        return StorageExpressionFunctionWriteText.instance();
    }

    @Override
    public StorageExpressionEvaluationContext createContext() {
        return this.createContext(StorageStores.empty());
    }

    private StorageExpressionEvaluationContext createContext(final StorageStore storage) {
        return new FakeStorageExpressionEvaluationContext() {

            @Override
            public StorageStore storage() {
                return storage;
            }
        };
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            StorageExpressionFunctionWriteText.instance(),
            "StorageWriteText"
        );
    }

    // class............................................................................................................

    @Override
    public Class<StorageExpressionFunctionWriteText<StorageExpressionEvaluationContext>> type() {
        return Cast.to(StorageExpressionFunctionWriteText.class);
    }
}
