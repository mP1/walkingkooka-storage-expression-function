package walkingkooka.storage.expression.function;

import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.storage.StoragePath;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that returns the current working directory {@link StoragePath}.
 */
final class StorageExpressionFunctionGetCurrentWorkingDirectory<C extends StorageExpressionEvaluationContext> extends StorageExpressionFunction<C, StoragePath> {

    /**
     * Type-safe getter.
     */
    static <C extends StorageExpressionEvaluationContext> StorageExpressionFunctionGetCurrentWorkingDirectory<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static StorageExpressionFunctionGetCurrentWorkingDirectory<?> INSTANCE = new StorageExpressionFunctionGetCurrentWorkingDirectory<>();

    private StorageExpressionFunctionGetCurrentWorkingDirectory() {
        super("getCurrentWorkingDirectory");
    }

    @Override
    public Class<StoragePath> returnType() {
        return StoragePath.class;
    }

    @Override
    public StoragePath apply(final List<Object> values,
                             final C context) {
        return CURRENT_WORKING_DIRECTORY.get(
            values,
            0
        ).orElseGet(
            () -> context.currentWorkingDirectory().orElse(null)
        );
    }

    /**
     * Given the count assembles the parameters with the correct parameter names and types.
     */
    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        List<ExpressionFunctionParameter<?>> parameters;

        switch (count) {
            case 0:
            case 1:
                parameters = PARAMETERS;
                break;
            default:
                throw new IllegalArgumentException("Too many parameters");
        }

        return parameters;
    }

    private final ExpressionFunctionParameter<StoragePath> CURRENT_WORKING_DIRECTORY = ExpressionFunctionParameterName.with("currentWorkingDirectory")
        .optional(StoragePath.class)
        .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE);

    private final List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(CURRENT_WORKING_DIRECTORY);
}
