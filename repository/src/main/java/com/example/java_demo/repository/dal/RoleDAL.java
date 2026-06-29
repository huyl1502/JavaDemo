package com.example.java_demo.repository.dal;

import com.example.java_demo.model.SystemConfig.RoleModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IRoleDAL;
import com.example.java_demo.repository.utils.MongoPageUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

public class RoleDAL implements IRoleDAL {
    private final MongoCollection<RoleModel> _collection;

    public RoleDAL(MongoDatabase database) {
        String collectionName = "RoleCollection";
        this._collection = database.getCollection(collectionName, RoleModel.class);
    }

    @Override
    public boolean insert(RoleModel role) {
        _collection.insertOne(role);
        return true;
    }

    @Override
    public boolean update(RoleModel role) {
        var result = _collection.replaceOne(eq("RoleId", role.getRoleId()), role);
        return result.getModifiedCount() > 0;
    }

    @Override
    public boolean delete(String roleId) {
        var result = _collection.deleteOne(eq("RoleId", roleId));
        return result.getDeletedCount() > 0;
    }

    @Override
    public RoleModel getById(String roleId) {
        return _collection.find(eq("RoleId", roleId)).first();
    }

    @Override
    public List<RoleModel> getAll() {
        return _collection.find().into(new ArrayList<>());
    }

    @Override
    public PagedResponse<RoleModel> getAll(PagedRequest<?> request) {
        return MongoPageUtils.paginate(_collection, null, request);
    }
}
