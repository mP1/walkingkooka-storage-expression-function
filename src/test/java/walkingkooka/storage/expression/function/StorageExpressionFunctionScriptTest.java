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
import walkingkooka.convert.Converter;
import walkingkooka.convert.Converters;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.FakeStorage;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.convert.StorageConverters;
import walkingkooka.storage.expression.function.StorageExpressionFunctionTestCase.TestStorageExpressionEvaluationContext;
import walkingkooka.tree.json.JsonNode;
import walkingkooka.tree.json.convert.JsonNodeConverters;

import java.time.LocalDateTime;
import java.util.Optional;

public final class StorageExpressionFunctionScriptTest extends StorageExpressionFunctionTestCase<StorageExpressionFunctionReadText<TestStorageExpressionEvaluationContext>, String> {

    private final static StoragePath PATH = StoragePath.parse("/dir1/file2.json");

    private final static String TEXT = "Hello World";

    @Test
    public void testApplyStorageEntryPresent() {
        this.applyAndCheck(
            Lists.of(PATH),
            JsonNode.string(TEXT)
                .toJsonText(
                    INDENTATION,
                    LINE_ENDING
                )
        );
    }

    @Test
    public void testApplyStorageEntryPresentAndMissingPath() {
        this.applyAndCheck(
            Lists.empty(),
            null
        );
    }

    @Test
    public void testApplyStorageEntryMissing() {
        this.applyAndCheck(
            Lists.of(StoragePath.parse("/dir1/missing.json")),
            null
        );
    }

    @Override
    public void testSetParametersSame() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StorageExpressionFunctionReadText<TestStorageExpressionEvaluationContext> createBiFunction() {
        return StorageExpressionFunctionReadText.instance();
    }

    @Override
    public TestStorageExpressionEvaluationContext createContext() {
        return new TestStorageExpressionEvaluationContext(
            new FakeStorage<TestStorageExpressionEvaluationContext>() {
                @Override
                public Optional<StorageValue> load(final StoragePath path,
                                                   final TestStorageExpressionEvaluationContext context) {
                    return Optional.ofNullable(
                        path.equals(PATH) ?
                            StorageValue.with(path, Optional.of(TEXT)) :
                            null
                    );
                }
            }
        ) {

            @Override
            public boolean canConvert(final Object value,
                                      final Class<?> type) {
                return this.converters.canConvert(
                    value,
                    type,
                    this
                );
            }

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return this.converters.convert(
                        value,
                        target,
                        this
                    );
            }

            private final Converter<TestStorageExpressionEvaluationContext> converters = Converters.collection(
                Lists.of(
                    Converters.simple(),
                    StorageConverters.storagePathJsonToClass(),
                    JsonNodeConverters.toJsonNode(),
                    JsonNodeConverters.toJsonText()
                )
            );

            @Override
            public Optional<StoragePath> currentWorkingDirectory() {
                return StorageExpressionFunctionScriptTest.CURRENT_WORKING_DIRECTORY;
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

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            StorageExpressionFunctionReadText.instance(),
            "storageReadText"
        );
    }

    // class............................................................................................................

    @Override
    public Class<StorageExpressionFunctionReadText<TestStorageExpressionEvaluationContext>> type() {
        return Cast.to(StorageExpressionFunctionReadText.class);
    }
}
