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
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.convert.Converters;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.FakeStorage;
import walkingkooka.storage.Storage;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;

import java.time.LocalDateTime;
import java.util.Optional;

public final class StorageExpressionEvaluationFunctionReadTextTest implements ExpressionFunctionTesting<StorageExpressionEvaluationFunctionReadText<StorageExpressionEvaluationContext>, String, StorageExpressionEvaluationContext> {

    private final static StoragePath PATH = StoragePath.parse("/dir1/file2.txt");

    private final static String TEXT = "Hello World";

    @Test
    public void testApplyStorageEntryPresent() {
        this.applyAndCheck(
            Lists.of(PATH),
            TEXT
        );
    }

    @Test
    public void testApplyStorageEntryMissing() {
        this.applyAndCheck(
            Lists.of(StoragePath.parse("/dir1/missing.txt")),
            null
        );
    }

    @Override
    public StorageExpressionEvaluationFunctionReadText<StorageExpressionEvaluationContext> createBiFunction() {
        return StorageExpressionEvaluationFunctionReadText.instance();
    }

    @Override
    public StorageExpressionEvaluationContext createContext() {
        return new FakeStorageExpressionEvaluationContext() {

            @Override
            public Storage<StorageExpressionEvaluationContext> storage() {
                return this.storage;
            }

            private final Storage<StorageExpressionEvaluationContext> storage = new FakeStorage<>() {
                @Override
                public Optional<StorageValue> load(final StoragePath path,
                                                   final StorageExpressionEvaluationContext context) {
                    return Optional.ofNullable(
                        path.equals(PATH) ?
                            StorageValue.with(path, Optional.of(TEXT)) :
                            null
                    );
                }
            };

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return Converters.simple()
                    .convert(
                        value,
                        target,
                        this
                    );
            }

            @Override
            public LocalDateTime now() {
                return LocalDateTime.now();
            }

            @Override
            public Optional<EmailAddress> user() {
                return Optional.of(
                    EmailAddress.parse("user@example.com")
                );
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
            StorageExpressionEvaluationFunctionReadText.instance(),
            "StorageReadText"
        );
    }

    // class............................................................................................................

    @Override
    public Class<StorageExpressionEvaluationFunctionReadText<StorageExpressionEvaluationContext>> type() {
        return Cast.to(StorageExpressionEvaluationFunctionReadText.class);
    }
}
