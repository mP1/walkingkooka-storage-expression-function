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

import walkingkooka.environment.EnvironmentValueName;
import walkingkooka.net.email.EmailAddress;
import walkingkooka.storage.Storage;
import walkingkooka.text.LineEnding;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionEvaluationContextDelegator;

import java.util.Locale;
import java.util.Optional;

public interface StorageExpressionEvaluationContextDelegator extends StorageExpressionEvaluationContext, ExpressionEvaluationContextDelegator {

    // ExpressionEvaluationContextDelegator.............................................................................

    @Override
    default <T> StorageExpressionEvaluationContext setEnvironmentValue(final EnvironmentValueName<T> name,
                                                                       final T value) {
        this.environmentContext()
            .setEnvironmentValue(
                name,
                value
            );
        return this;
    }

    @Override
    default StorageExpressionEvaluationContext removeEnvironmentValue(EnvironmentValueName<?> name) {
        this.environmentContext()
            .removeEnvironmentValue(name);
        return this;
    }

    @Override
    default StorageExpressionEvaluationContext setLineEnding(final LineEnding lineEnding) {
        this.environmentContext()
            .setLineEnding(lineEnding);
        return this;
    }

    @Override
    default StorageExpressionEvaluationContext setLocale(final Locale locale) {
        this.environmentContext()
            .setLocale(locale);
        return this;
    }

    @Override
    default StorageExpressionEvaluationContext setUser(final Optional<EmailAddress> user) {
        this.environmentContext()
            .setUser(user);
        return this;
    }

    @Override
    default ExpressionEvaluationContext expressionEvaluationContext() {
        return this.storageExpressionEvaluationContext();
    }

    // StorageExpressionEvaluationContext...............................................................................

    @Override
    default Storage<StorageExpressionEvaluationContext> storage() {
        return this.storageExpressionEvaluationContext()
            .storage();
    }

    StorageExpressionEvaluationContext storageExpressionEvaluationContext();
}
