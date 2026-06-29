package com.example.java_demo.repository.dal;

import com.example.java_demo.model.SystemConfig.FeatureModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IFeatureDAL;
import com.example.java_demo.repository.utils.MongoPageUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

public class FeatureDAL implements IFeatureDAL {
    private final MongoCollection<FeatureModel> _collection;

    public FeatureDAL(MongoDatabase database) {
        String collectionName = "FeatureCollection";
        this._collection = database.getCollection(collectionName, FeatureModel.class);
    }

    @Override
    public boolean insert(FeatureModel feature) {
        _collection.insertOne(feature);
        return true;
    }

    @Override
    public boolean update(FeatureModel feature) {
        var result = _collection.replaceOne(eq("FeatureId", feature.getFeatureId()), feature);
        return result.getModifiedCount() > 0;
    }

    @Override
    public boolean delete(String featureId) {
        var result = _collection.deleteOne(eq("FeatureId", featureId));
        return result.getDeletedCount() > 0;
    }

    @Override
    public FeatureModel getById(String featureId) {
        return _collection.find(eq("FeatureId", featureId)).first();
    }

    @Override
    public List<FeatureModel> getAll() {
        return _collection.find().into(new ArrayList<>());
    }

    @Override
    public PagedResponse<FeatureModel> getAll(PagedRequest<?> request) {
        return MongoPageUtils.paginate(_collection, null, request);
    }
}
