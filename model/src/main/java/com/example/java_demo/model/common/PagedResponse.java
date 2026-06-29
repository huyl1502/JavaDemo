package com.example.java_demo.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {
    private List<T> items;
    private long totalRecords;
    private int pageIndex;
    private int pageSize;
    private int totalPages;

    public static <T> PagedResponse<T> of(List<T> items, long totalRecords, int pageIndex, int pageSize) {
        int totalPages = (int) Math.ceil((double) totalRecords / (pageSize > 0 ? pageSize : 1));
        return new PagedResponse<>(items, totalRecords, pageIndex, pageSize, totalPages);
    }
}
