package com.example.java_demo.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedRequest<T> {
    private int pageIndex = 1;
    private int pageSize = 10;
    private T filter;
}

