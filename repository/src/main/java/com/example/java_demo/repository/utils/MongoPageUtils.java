package com.example.java_demo.repository.utils;

import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;

public class MongoPageUtils {
    public static <T> PagedResponse<T> paginate(
            MongoCollection<T> collection,
            Bson filter,
            PagedRequest<?> request,
            Bson sort
    ) {
        int pageIndex = Math.max(1, request.getPageIndex());
        int pageSize = Math.max(1, request.getPageSize());
        int skip = (pageIndex - 1) * pageSize;

        long totalRecords = filter != null ? collection.countDocuments(filter) : collection.countDocuments();

        List<T> items;
        var findIterable = filter != null ? collection.find(filter) : collection.find();
        if (sort != null) {
            findIterable = findIterable.sort(sort);
        }
        items = findIterable.skip(skip).limit(pageSize).into(new ArrayList<>());

        return PagedResponse.of(items, totalRecords, pageIndex, pageSize);
    }

    public static <T> PagedResponse<T> paginate(
            MongoCollection<T> collection,
            Bson filter,
            PagedRequest<?> request
    ) {
        return paginate(collection, filter, request, null);
    }
}
