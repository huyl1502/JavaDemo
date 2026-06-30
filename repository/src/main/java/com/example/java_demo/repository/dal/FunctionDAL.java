package com.example.java_demo.repository.dal;

import com.example.java_demo.model.SystemConfig.FunctionModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IFunctionDAL;
import com.example.java_demo.repository.utils.MongoPageUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

public class FunctionDAL implements IFunctionDAL {
    private final MongoCollection<FunctionModel> _collection;

    public FunctionDAL(MongoDatabase database) {
        String collectionName = "FunctionCollection";
        this._collection = database.getCollection(collectionName, FunctionModel.class);
    }

    @Override
    public boolean insert(FunctionModel function) {
        _collection.insertOne(function);
        return true;
    }

    @Override
    public boolean update(FunctionModel function) {
        var result = _collection.replaceOne(eq("functionId", function.getFunctionId()), function);
        return result.getModifiedCount() > 0;
    }

    @Override
    public boolean delete(String functionId) {
        var result = _collection.deleteOne(eq("functionId", functionId));
        return result.getDeletedCount() > 0;
    }

    @Override
    public FunctionModel getById(String functionId) {
        return _collection.find(eq("functionId", functionId)).first();
    }

    @Override
    public List<FunctionModel> getAll() {
        return _collection.find().into(new ArrayList<>());
    }

    @Override
    public PagedResponse<FunctionModel> getAll(PagedRequest<?> request) {
        return MongoPageUtils.paginate(_collection, null, request);
    }
}
