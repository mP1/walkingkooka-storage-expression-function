package walkingkooka.storage.expression.function;

import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.storage.StorageMountPoint;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

/**
 * A function that returns all available {@link StorageMountPoint}.
 */
final class StorageExpressionFunctionMountPoints<C extends StorageExpressionEvaluationContext> extends StorageExpressionFunction<C, List<StorageMountPoint<C>>> {

    /**
     * Type-safe getter.
     */
    static <C extends StorageExpressionEvaluationContext> StorageExpressionFunctionMountPoints<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static StorageExpressionFunctionMountPoints<?> INSTANCE = new StorageExpressionFunctionMountPoints<>();

    private StorageExpressionFunctionMountPoints() {
        super("mountPoints");
    }

    private final Class<List<StorageMountPoint<?>>> LIST_STORAGE_MOUNT_POINTS = Cast.to(Lists.class);

    @Override
    public Class<List<StorageMountPoint<C>>> returnType() {
        return Cast.to(LIST_STORAGE_MOUNT_POINTS);
    }

    @Override
    public List<StorageMountPoint<C>> apply(final List<Object> values,
                                            final C context) {
        return Cast.to(
            context.storageMountPoints()
        );
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return NO_PARAMETERS;
    }
}
