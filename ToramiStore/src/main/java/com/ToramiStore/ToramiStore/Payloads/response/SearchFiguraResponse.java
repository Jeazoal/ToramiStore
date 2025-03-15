package com.ToramiStore.ToramiStore.Payloads.response;

import com.ToramiStore.ToramiStore.Entity.Figura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchFiguraResponse {
    private List<Figura> figuras;
}
