package com.ToramiStore.ToramiStore.Services;

import com.ToramiStore.ToramiStore.Payloads.request.SaveRequest;
import com.ToramiStore.ToramiStore.Payloads.response.SaveResponse;

public interface IToy {
    SaveResponse save (SaveRequest request);
}
