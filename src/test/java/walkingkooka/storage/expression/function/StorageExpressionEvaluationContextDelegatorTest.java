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

import walkingkooka.Either;
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.datetime.DateTimeContextDelegator;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.environment.EnvironmentContext;
import walkingkooka.environment.EnvironmentContexts;
import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.environment.EnvironmentValueWatcher;
import walkingkooka.locale.LocaleContext;
import walkingkooka.locale.LocaleContextDelegator;
import walkingkooka.locale.LocaleContexts;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.Storage;
import walkingkooka.storage.StorageContext;
import walkingkooka.storage.StorageContexts;
import walkingkooka.storage.StoragePath;
import walkingkooka.storage.StorageValue;
import walkingkooka.storage.StorageValueInfo;
import walkingkooka.storage.Storages;
import walkingkooka.storage.expression.function.StorageExpressionEvaluationContextDelegatorTest.TestStorageExpressionEvaluationContextDelegator;
import walkingkooka.text.CaseSensitivity;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionFunctionName;
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.ExpressionReference;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.math.MathContext;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public final class StorageExpressionEvaluationContextDelegatorTest implements StorageExpressionEvaluationContextTesting2<TestStorageExpressionEvaluationContextDelegator>,
    DecimalNumberContextDelegator {

    private final static Locale LOCALE = Locale.ENGLISH;

    private final static StoragePath CURRENT_WORKING_PATH = StoragePath.parse("/current1/working2/directory3");

    @Override
    public TestStorageExpressionEvaluationContextDelegator createContext() {
        return new TestStorageExpressionEvaluationContextDelegator();
    }

    @Override
    public void testEnterScopeGivesDifferentInstance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testEnterScopeWithNullFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testEvaluateExpressionUnknownFunctionNameFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testEnvironmentValueLineEndingEqualsLineEnding() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testEnvironmentValueLocaleEqualsLocale() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testEnvironmentValueNowEqualsNow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testEnvironmentValueUserEqualsUser() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testIsPureNullNameFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testDateTimeSymbolsForLocaleWithNullFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testDecimalNumberSymbolsForLocaleWithNullFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testEnvironmentValueWithNullFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testRemoveEnvironmentValueWithNullNameFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testRemoveEnvironmentValueWithNowFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetEnvironmentValueWithNowFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetEnvironmentValueWithNullNameFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetEnvironmentValueWithNullValueFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetIndentationWithDifferentAndWatcher() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetLineEndingWithDifferentAndWatcher() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetLocaleWithDifferent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetLocaleWithDifferentAndWatcher() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetTimeOffsetWithDifferentAndWatcher() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testUserNotNull() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testSetUserWithDifferentAndWatcher() {
        throw new UnsupportedOperationException();
    }


    // DecimalNumberContext.............................................................................................

    @Override
    public int decimalNumberDigitCount() {
        return DECIMAL_NUMBER_CONTEXT.decimalNumberDigitCount();
    }

    @Override
    public DecimalNumberContext decimalNumberContext() {
        return DECIMAL_NUMBER_CONTEXT;
    }

    @Override
    public MathContext mathContext() {
        return DECIMAL_NUMBER_CONTEXT.mathContext();
    }

    private final static DecimalNumberContext DECIMAL_NUMBER_CONTEXT = DecimalNumberContexts.american(MathContext.DECIMAL64);

    // class............................................................................................................

    @Override
    public Class<TestStorageExpressionEvaluationContextDelegator> type() {
        return TestStorageExpressionEvaluationContextDelegator.class;
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    static class TestStorageExpressionEvaluationContextDelegator implements StorageExpressionEvaluationContextDelegator,
        DecimalNumberContextDelegator {

        @Override
        public ExpressionEvaluationContext enterScope(final Function<ExpressionReference, Optional<Optional<Object>>> function) {
            Objects.requireNonNull(function, "function");
            throw new UnsupportedOperationException();
        }

        @Override
        public Optional<Optional<Object>> reference(final ExpressionReference expressionReference) {
            Objects.requireNonNull(expressionReference, "expressionReference");
            throw new UnsupportedOperationException();
        }

        @Override
        public StorageExpressionEvaluationContext cloneEnvironment() {
            throw new UnsupportedOperationException();
        }

        @Override
        public StorageExpressionEvaluationContext setEnvironmentContext(final EnvironmentContext environmentContext) {
            Objects.requireNonNull(environmentContext, "environmentContext");

            return new TestStorageExpressionEvaluationContextDelegator();
        }

        @Override
        public StorageExpressionEvaluationContext storageExpressionEvaluationContext() {
            return this.storageExpressionEvaluationContext;
        }

        private final TestStorageExpressionEvaluationContext storageExpressionEvaluationContext = new TestStorageExpressionEvaluationContext();

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }

    static final class TestStorageExpressionEvaluationContext implements StorageExpressionEvaluationContext,
        DateTimeContextDelegator,
        DecimalNumberContextDelegator,
        LocaleContextDelegator {

        @Override
        public boolean isText(final Object value) {
            return false;
        }

        @Override
        public CaseSensitivity stringEqualsCaseSensitivity() {
            return CaseSensitivity.SENSITIVE;
        }

        @Override
        public Object evaluate(final String expression) {
            Objects.requireNonNull(expression, "expression");
            throw new UnsupportedOperationException();
        }

        @Override
        public Optional<Optional<Object>> reference(final ExpressionReference reference) {
            Objects.requireNonNull(reference, "reference");
            throw new UnsupportedOperationException();
        }

        @Override
        public Object handleException(final RuntimeException thrown) {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> T prepareParameter(final ExpressionFunctionParameter<T> parameter,
                                      final Object value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ExpressionEvaluationContext enterScope(final Function<ExpressionReference, Optional<Optional<Object>>> function) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isPure(final ExpressionFunctionName functionName) {
            return false;
        }

        @Override
        public ExpressionFunction<?, ExpressionEvaluationContext> expressionFunction(final ExpressionFunctionName functionName) {
            Objects.requireNonNull(functionName, "functionName");
            throw new UnsupportedOperationException();
        }

        @Override
        public DateTimeContext dateTimeContext() {
            return DateTimeContexts.basic(
                DateTimeSymbols.fromDateFormatSymbols(
                    new DateFormatSymbols(StorageExpressionEvaluationContextDelegatorTest.LOCALE)
                ),
                StorageExpressionEvaluationContextDelegatorTest.LOCALE,
                1950,
                50,
                LocalDateTime::now
            );
        }

        @Override
        public DecimalNumberContext decimalNumberContext() {
            return DECIMAL_NUMBER_CONTEXT;
        }

        @Override
        public Optional<StoragePath> currentWorkingDirectory() {
            return Optional.of(CURRENT_WORKING_PATH);
        }

        @Override
        public void setCurrentWorkingDirectory(final Optional<StoragePath> currentWorkingDirectory) {
            this.setOrRemoveEnvironmentValue(
                CURRENT_WORKING_DIRECTORY,
                currentWorkingDirectory
            );
        }

        @Override
        public LineEnding lineEnding() {
            return this.environmentContext.lineEnding();
        }

        @Override
        public void setLineEnding(final LineEnding lineEnding) {
            this.environmentContext.setLineEnding(lineEnding);
        }

        @Override
        public LocaleContext localeContext() {
            return LocaleContexts.jre(StorageExpressionEvaluationContextDelegatorTest.LOCALE);
        }

        @Override
        public Indentation indentation() {
            return this.environmentContext.indentation();
        }

        @Override
        public void setIndentation(final Indentation indentation) {
            this.environmentContext.setIndentation(indentation);
        }

        @Override
        public Locale locale() {
            return this.environmentContext.locale();
        }

        @Override
        public void setLocale(final Locale locale) {
            this.environmentContext.setLocale(locale);
        }

        @Override
        public LocalDateTime now() {
            return this.environmentContext.now();
        }

        @Override
        public ZoneOffset timeOffset() {
            return this.environmentContext.timeOffset();
        }

        @Override
        public void setTimeOffset(final ZoneOffset timeOffset) {
            this.environmentContext.setTimeOffset(timeOffset);
        }

        @Override
        public Optional<EmailAddress> user() {
            return this.environmentContext.user();
        }

        @Override
        public void setUser(final Optional<EmailAddress> user) {
            this.environmentContext.setUser(user);
        }

        @Override
        public StorageExpressionEvaluationContext cloneEnvironment() {
            return new StorageExpressionEvaluationContextTestingTest.TestStorageExpressionEvaluationContext();
        }

        @Override
        public StorageExpressionEvaluationContextTestingTest.TestStorageExpressionEvaluationContext setEnvironmentContext(final EnvironmentContext environmentContext) {
            Objects.requireNonNull(environmentContext, "environmentContext");

            return new StorageExpressionEvaluationContextTestingTest.TestStorageExpressionEvaluationContext();
        }

        @Override
        public <T> Optional<T> environmentValue(final EnvironmentValueName<T> environmentValueName) {
            return this.environmentContext.environmentValue(environmentValueName);
        }

        @Override
        public Set<EnvironmentValueName<?>> environmentValueNames() {
            return this.environmentContext.environmentValueNames();
        }

        @Override
        public <T> void setEnvironmentValue(final EnvironmentValueName<T> name,
                                            final T value) {
            this.environmentContext.setEnvironmentValue(
                name,
                value
            );
        }

        @Override
        public void removeEnvironmentValue(final EnvironmentValueName<?> name) {
            this.environmentContext.removeEnvironmentValue(name);
        }

        private final EnvironmentContext environmentContext = EnvironmentContexts.map(
            EnvironmentContexts.empty(
                Indentation.SPACES2,
                LineEnding.NL,
                StorageExpressionEvaluationContextDelegatorTest.LOCALE,
                () -> LocalDateTime.MIN,
                ANONYMOUS
            )
        );

        @Override
        public Runnable addEventValueWatcher(final EnvironmentValueWatcher watcher) {
            Objects.requireNonNull(watcher, "watcher");
            throw new UnsupportedOperationException();
        }

        @Override
        public Runnable addEventValueWatcherOnce(final EnvironmentValueWatcher watcher) {
            Objects.requireNonNull(watcher, "watcher");
            throw new UnsupportedOperationException();
        }

        @Override
        public Optional<StorageValue> loadStorage(final StoragePath path) {
            return this.storage.load(
                path,
                StorageContexts.fake()
            );
        }

        @Override
        public StorageValue saveStorage(final StorageValue value) {
            return this.storage.save(
                value,
                StorageContexts.fake()
            );
        }

        @Override
        public void deleteStorage(final StoragePath path) {
            this.storage.delete(
                path,
                StorageContexts.fake()
            );
        }

        @Override
        public List<StorageValueInfo> listStorage(final StoragePath parent,
                                                  final int offset,
                                                  final int count) {
            return this.storage.list(
                parent,
                offset,
                count,
                StorageContexts.fake()
            );
        }

        private final Storage<StorageContext> storage = Storages.tree();

        @Override
        public <T> Either<T, String> convert(final Object value,
                                             final Class<T> type) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean canConvert(final Object value,
                                  final Class<?> type) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean canNumbersHaveGroupSeparator() {
            return false;
        }

        @Override
        public long dateOffset() {
            return 0;
        }

        @Override
        public ExpressionNumberKind expressionNumberKind() {
            return ExpressionNumberKind.BIG_DECIMAL;
        }

        @Override
        public StoragePath parseStoragePath(final String text) {
            return StoragePath.parseMaybeRelative(
                text,
                this.currentWorkingDirectory()
            );
        }

        @Override
        public char valueSeparator() {
            return ',';
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}
