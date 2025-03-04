package com.ToramiStore.ToramiStore.Utils;

public interface AdapterTemplate<TEntity, TRequest> {
    TEntity toEntity(TRequest request);
}
