package walkingkooka.storage.expression.function;

import walkingkooka.Cast;
import walkingkooka.storage.Storage;
import walkingkooka.storage.Storages;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

/**
 * A function that returns a new empty {@link Storages#treeMapStore()}.
 */
final class StorageExpressionFunctionTreeMapStorage<C extends StorageExpressionEvaluationContext> extends StorageExpressionFunction<C, Storage<C>> {

    /**
     * Type-safe getter.
     */
    static <C extends StorageExpressionEvaluationContext> StorageExpressionFunctionTreeMapStorage<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static StorageExpressionFunctionTreeMapStorage<?> INSTANCE = new StorageExpressionFunctionTreeMapStorage<>();

    private StorageExpressionFunctionTreeMapStorage() {
        super("treeMapStorage");
    }

    @Override
    public Class<Storage<C>> returnType() {
        return Cast.to(Storage.class);
    }

    @Override
    public Storage<C> apply(final List<Object> values,
                            final C context) {
        return Storages.treeMapStore();
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return NO_PARAMETERS;
    }
}
