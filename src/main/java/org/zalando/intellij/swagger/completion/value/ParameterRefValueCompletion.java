package org.zalando.intellij.swagger.completion.value;

import com.intellij.codeInsight.completion.CompletionResultSet;
import org.zalando.intellij.swagger.completion.value.model.StringValue;
import org.zalando.intellij.swagger.completion.value.model.Value;
import org.zalando.intellij.swagger.traversal.PositionResolver;

import java.util.List;
import java.util.stream.Collectors;

class ParameterRefValueCompletion extends ValueCompletion {

    protected ParameterRefValueCompletion(final PositionResolver positionResolver, final CompletionResultSet completionResultSet) {
        super(positionResolver, completionResultSet);
    }

    @Override
    public void fill() {
        getParameterKeys().stream().forEach(this::addValue);
    }

    private List<Value> getParameterKeys() {
        return positionResolver.getKeyNamesOf("parameters").stream()
                .map(keyName -> "#/parameters/" + keyName)
                .map(StringValue::new)
                .collect(Collectors.toList());
    }
}
