package com.example.java_demo.repository.dal;

import com.example.java_demo.model.SystemConfig.RightModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IRightDAL;
import com.example.java_demo.repository.utils.MongoPageUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

public class RightDAL implements IRightDAL {
    private final MongoCollection<RightModel> _collection;

    public RightDAL(MongoDatabase database) {
        String collectionName = "RightCollection";
        this._collection = database.getCollection(collectionName, RightModel.class);
    }

    @Override
    public boolean insert(RightModel right) {
        _collection.insertOne(right);
        return true;
    }

    @Override
    public boolean update(RightModel right) {
        var result = _collection.replaceOne(eq("rightId", right.getRightId()), right);
        return result.getModifiedCount() > 0;
    }

    @Override
    public boolean delete(String rightId) {
        var result = _collection.deleteOne(eq("rightId", rightId));
        return result.getDeletedCount() > 0;
    }

    @Override
    public RightModel getById(String rightId) {
        return _collection.find(eq("rightId", rightId)).first();
    }

    @Override
    public List<RightModel> getAll() {
        return _collection.find().into(new ArrayList<>());
    }

    @Override
    public PagedResponse<RightModel> getAll(PagedRequest<?> request) {
        return MongoPageUtils.paginate(_collection, null, request);
    }
}
