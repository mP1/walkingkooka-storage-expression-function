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

import walkingkooka.locale.LocaleContexts;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.storage.expression.function.StorageExpressionEvaluationContextDelegatorTest.TestStorageExpressionEvaluationContextDelegator;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionReference;

import java.math.MathContext;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public final class StorageExpressionEvaluationContextDelegatorTest implements StorageExpressionEvaluationContextTesting<TestStorageExpressionEvaluationContextDelegator>,
    DecimalNumberContextDelegator {

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

    // DecimalNumberContext.............................................................................................

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
    DecimalNumberContextDelegator{

        @Override
        public DecimalNumberContext decimalNumberContext() {
            return DECIMAL_NUMBER_CONTEXT;
        }

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
        public StorageExpressionEvaluationContext storageExpressionEvaluationContext() {
            return StorageExpressionEvaluationContexts.fake();
        }

        @Override
        public Set<Locale> findByLocaleText(final String text,
                                            final int offset,
                                            final int count) {
            return LocaleContexts.jre(Locale.ENGLISH)
                .findByLocaleText(
                    text,
                    offset,
                    count
                );
        }

        @Override
        public Optional<String> localeText(final Locale locale) {
            return LocaleContexts.jre(Locale.ENGLISH)
                .localeText(locale);
        }

        @Override
        public StorageExpressionEvaluationContext setLocale(final Locale locale) {
            Objects.requireNonNull(locale, "locale");
            throw new UnsupportedOperationException();
        }

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}
