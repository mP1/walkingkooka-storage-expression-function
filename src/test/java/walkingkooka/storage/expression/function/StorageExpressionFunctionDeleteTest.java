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
import walkingkooka.storage.Storage;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.Storages;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;

import java.time.LocalDateTime;
import java.util.Optional;

public final class StorageExpressionFunctionDeleteTest implements ExpressionFunctionTesting<StorageExpressionFunctionDelete<StorageExpressionEvaluationContext>, Void, StorageExpressionEvaluationContext> {

    private final static StoragePath PATH = StoragePath.parse("/dir1/file2.txt");

    @Test
    public void testApplyStorageDeleted() {
        final Storage<StorageExpressionEvaluationContext> storage = Storages.tree();
        final StorageExpressionEvaluationContext context = this.createContext(storage);

        storage.save(
            StorageValue.with(
                PATH,
                Optional.of("value456")
            ),
            context
        );

        this.applyAndCheck(
            StorageExpressionFunctionDelete.instance(),
            Lists.of(PATH),
            context,
            null
        );

        this.checkEquals(
            Optional.empty(),
            storage.load(
                PATH,
                context
            )
        );
    }

    @Test
    public void testApplyStorageMissing() {
        final Storage<StorageExpressionEvaluationContext> storage = Storages.tree();
        final StorageExpressionEvaluationContext context = this.createContext(storage);

        this.applyAndCheck(
            StorageExpressionFunctionDelete.instance(),
            Lists.of(PATH),
            context,
            null
        );
    }

    @Override
    public StorageExpressionFunctionDelete<StorageExpressionEvaluationContext> createBiFunction() {
        return StorageExpressionFunctionDelete.instance();
    }

    @Override
    public StorageExpressionEvaluationContext createContext() {
        return this.createContext(Storages.empty());
    }

    private StorageExpressionEvaluationContext createContext(final Storage<StorageExpressionEvaluationContext> storage) {
        return new FakeStorageExpressionEvaluationContext() {

            @Override
            public Storage<StorageExpressionEvaluationContext> storage() {
                return storage;
            }

            @Override
            public Optional<EmailAddress> user() {
                return Optional.of(
                    EmailAddress.parse("user@example.com")
                );
            }

            @Override
            public LocalDateTime now() {
                return LocalDateTime.now();
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
            StorageExpressionFunctionDelete.instance(),
            "StorageDelete"
        );
    }

    // class............................................................................................................

    @Override
    public Class<StorageExpressionFunctionDelete<StorageExpressionEvaluationContext>> type() {
        return Cast.to(StorageExpressionFunctionDelete.class);
    }
}
