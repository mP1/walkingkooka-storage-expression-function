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

import walkingkooka.storage.StorageStore;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

import java.util.Locale;

/**
 * A {@link ExpressionEvaluationContext} that adds a storage getter.
 */
public interface StorageExpressionEvaluationContext extends ExpressionEvaluationContext {

    @Override
    StorageExpressionEvaluationContext setLocale(final Locale locale);

    /**
     * Getter that returns the current {@link StorageStore}
     */
    StorageStore storage();
}
