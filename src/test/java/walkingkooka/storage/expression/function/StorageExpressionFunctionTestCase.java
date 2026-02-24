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

import walkingkooka.convert.ConverterContexts;
import walkingkooka.convert.Converters;
import walkingkooka.currency.CurrencyLocaleContexts;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.environment.EnvironmentContext;
import walkingkooka.environment.EnvironmentContextDelegator;
import walkingkooka.environment.EnvironmentContexts;
import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.Storage;
import walkingkooka.storage.StorageContext;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.StorageValueInfo;
import walkingkooka.storage.expression.function.StorageExpressionFunctionTestCase.TestStorageExpressionEvaluationContext;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.convert.ExpressionNumberConverterContexts;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;
import walkingkooka.tree.json.convert.JsonNodeConverterContext;
import walkingkooka.tree.json.convert.JsonNodeConverterContextDelegator;
import walkingkooka.tree.json.convert.JsonNodeConverterContexts;
import walkingkooka.tree.json.marshall.JsonNodeMarshallContextObjectPostProcessor;
import walkingkooka.tree.json.marshall.JsonNodeMarshallContexts;
import walkingkooka.tree.json.marshall.JsonNodeMarshallUnmarshallContexts;
import walkingkooka.tree.json.marshall.JsonNodeUnmarshallContextPreProcessor;
import walkingkooka.tree.json.marshall.JsonNodeUnmarshallContexts;

import java.math.MathContext;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public abstract class StorageExpressionFunctionTestCase<F extends StorageExpressionFunction<TestStorageExpressionEvaluationContext, T>, T> implements ExpressionFunctionTesting<F, T, TestStorageExpressionEvaluationContext> {

    final static Optional<StoragePath> CURRENT_WORKING_DIRECTORY = Optional.of(
        StoragePath.parse("/dir1")
    );

    final static Indentation INDENTATION = Indentation.SPACES4;

    final static LineEnding LINE_ENDING = LineEnding.NL;

    @Override
    public final void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    static class TestStorageExpressionEvaluationContext extends FakeStorageExpressionEvaluationContext implements StorageContext,
        JsonNodeConverterContextDelegator,
        EnvironmentContextDelegator {

        TestStorageExpressionEvaluationContext(final Storage<TestStorageExpressionEvaluationContext> storage) {
            super();
            this.storage = storage;
        }

        // StorageContext...................................................................................................

        @Override
        public StoragePath parseStoragePath(final String text) {
            throw new UnsupportedOperationException();
        }

        @Override
        public final Optional<StorageValue> loadStorage(final StoragePath path) {
            return this.storage.load(
                path,
                this
            );
        }

        @Override
        public final StorageValue saveStorage(final StorageValue value) {
            return this.storage.save(
                value,
                this
            );
        }

        @Override
        public final void deleteStorage(final StoragePath path) {
            this.storage.delete(
                path,
                this
            );
        }

        @Override
        public final List<StorageValueInfo> listStorage(final StoragePath parent,
                                                        final int offset,
                                                        final int count) {
            return this.storage.list(
                parent,
                offset,
                count,
                this
            );
        }

        private final Storage<TestStorageExpressionEvaluationContext> storage;

        // JsonNodeConverterContextDelegator............................................................................

        @Override
        public JsonNodeConverterContext jsonNodeConverterContext() {
            final ExpressionNumberKind expressionNumberKind = ExpressionNumberKind.BIG_DECIMAL;

            return JsonNodeConverterContexts.basic(
                ExpressionNumberConverterContexts.basic(
                    Converters.fake(),
                    ConverterContexts.basic(
                        false, // canNumbersHaveGroupSeparator
                        0L, // dateTimeOffset
                        StorageExpressionFunctionTestCase.INDENTATION,
                        StorageExpressionFunctionTestCase.LINE_ENDING,
                        ',', // valueSeparator
                        Converters.fake(),
                        CurrencyLocaleContexts.fake(),
                        DateTimeContexts.fake(),
                        DecimalNumberContexts.fake()
                    ),
                    expressionNumberKind
                ),
                JsonNodeMarshallUnmarshallContexts.basic(
                    JsonNodeMarshallContexts.basic(),
                    JsonNodeUnmarshallContexts.basic(
                        (String cc) -> {
                            throw new UnsupportedOperationException();
                        },
                        (String lt) -> {
                            throw new UnsupportedOperationException();
                        },
                        expressionNumberKind,
                        MathContext.DECIMAL32
                    )
                )
            );
        }

        @Override
        public JsonNodeConverterContext setObjectPostProcessor(final JsonNodeMarshallContextObjectPostProcessor posProcessor) {
            throw new UnsupportedOperationException();
        }

        @Override
        public JsonNodeConverterContext setPreProcessor(final JsonNodeUnmarshallContextPreProcessor preProcessor) {
            throw new UnsupportedOperationException();
        }

        // EnvironmentContextDelegator..................................................................................

        @Override
        public final TestStorageExpressionEvaluationContext cloneEnvironment() {
            throw new UnsupportedOperationException();
        }

        @Override
        public final TestStorageExpressionEvaluationContext setEnvironmentContext(final EnvironmentContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> void setEnvironmentValue(final EnvironmentValueName<T> name,
                                            final T reference) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void removeEnvironmentValue(final EnvironmentValueName<?> name) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Optional<StoragePath> currentWorkingDirectory() {
            return StorageExpressionFunctionListTest.CURRENT_WORKING_DIRECTORY;
        }

        @Override
        public Indentation indentation() {
            return StorageExpressionFunctionTestCase.INDENTATION;
        }

        @Override
        public LineEnding lineEnding() {
            return StorageExpressionFunctionTestCase.LINE_ENDING;
        }

        @Override
        public void setLineEnding(final LineEnding lineEnding) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setLocale(final Locale locale) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setUser(final Optional<EmailAddress> user) {
            throw new UnsupportedOperationException();
        }

        @Override
        public final EnvironmentContext environmentContext() {
            return EnvironmentContexts.fake();
        }
    }
}
