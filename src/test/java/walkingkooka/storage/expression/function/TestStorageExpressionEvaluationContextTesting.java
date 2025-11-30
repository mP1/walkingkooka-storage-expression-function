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

import walkingkooka.environment.EnvironmentContext;
import walkingkooka.environment.EnvironmentContexts;
import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.locale.LocaleContexts;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.expression.function.TestStorageExpressionEvaluationContextTesting.TestStorageExpressionEvaluationContext;
import walkingkooka.text.LineEnding;

import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public final class TestStorageExpressionEvaluationContextTesting implements StorageExpressionEvaluationContextTesting<TestStorageExpressionEvaluationContext>,
    DecimalNumberContextDelegator {

    @Override
    public TestStorageExpressionEvaluationContext createContext() {
        return new TestStorageExpressionEvaluationContext();
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

    @Override
    public void testEnvironmentValueWithNullFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testRemoveEnvironmentValueWithNullNameFails() {
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
    public void testUserNotNull() {
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
    public Class<TestStorageExpressionEvaluationContext> type() {
        return TestStorageExpressionEvaluationContext.class;
    }

    static class TestStorageExpressionEvaluationContext extends FakeStorageExpressionEvaluationContext {

        @Override
        public String currencySymbol() {
            return DECIMAL_NUMBER_CONTEXT.currencySymbol();
        }

        @Override
        public char decimalSeparator() {
            return DECIMAL_NUMBER_CONTEXT.decimalSeparator();
        }

        @Override
        public String exponentSymbol() {
            return DECIMAL_NUMBER_CONTEXT.exponentSymbol();
        }

        @Override
        public char groupSeparator() {
            return DECIMAL_NUMBER_CONTEXT.groupSeparator();
        }

        @Override
        public String infinitySymbol() {
            return DECIMAL_NUMBER_CONTEXT.infinitySymbol();
        }

        @Override
        public MathContext mathContext() {
            return DECIMAL_NUMBER_CONTEXT.mathContext();
        }

        @Override
        public char monetaryDecimalSeparator() {
            return DECIMAL_NUMBER_CONTEXT.monetaryDecimalSeparator();
        }

        @Override
        public String nanSymbol() {
            return DECIMAL_NUMBER_CONTEXT.nanSymbol();
        }

        @Override
        public char negativeSign() {
            return DECIMAL_NUMBER_CONTEXT.negativeSign();
        }

        @Override
        public char percentSymbol() {
            return DECIMAL_NUMBER_CONTEXT.percentSymbol();
        }

        @Override
        public char permillSymbol() {
            return DECIMAL_NUMBER_CONTEXT.permillSymbol();
        }

        @Override
        public char positiveSign() {
            return DECIMAL_NUMBER_CONTEXT.positiveSign();
        }

        @Override
        public char zeroDigit() {
            return DECIMAL_NUMBER_CONTEXT.zeroDigit();
        }

        @Override
        public StorageExpressionEvaluationContext setLineEnding(final LineEnding lineEnding) {
            this.environmentContext.setLineEnding(lineEnding);
            return this;
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
        public Locale locale() {
            return this.environmentContext.locale();
        }

        @Override
        public StorageExpressionEvaluationContext setLocale(final Locale locale) {
            this.environmentContext.setLocale(locale);
            return this;
        }

        @Override
        public Optional<EmailAddress> user() {
            return this.environmentContext.user();
        }

        @Override
        public StorageExpressionEvaluationContext setUser(final Optional<EmailAddress> user) {
            this.environmentContext.setUser(user);
            return this;
        }

        @Override
        public <T> Optional<T> environmentValue(final EnvironmentValueName<T> environmentValueName) {
            return this.environmentContext.environmentValue(environmentValueName);
        }

        private final EnvironmentContext environmentContext = EnvironmentContexts.map(
            EnvironmentContexts.empty(
                LineEnding.NL,
                Locale.ENGLISH,
                () -> LocalDateTime.MIN,
                ANONYMOUS
            )
        );

        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}
