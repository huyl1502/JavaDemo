package com.example.java_demo.repository.dal;

import com.example.java_demo.model.SystemConfig.MenuModel;
import com.example.java_demo.model.common.PagedRequest;
import com.example.java_demo.model.common.PagedResponse;
import com.example.java_demo.repository.interfaces.IMenuDAL;
import com.example.java_demo.repository.utils.MongoPageUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

public class MenuDAL implements IMenuDAL {
    private final MongoCollection<MenuModel> _collection;

    public MenuDAL(MongoDatabase database) {
        String collectionName = "MenuCollection";
        this._collection = database.getCollection(collectionName, MenuModel.class);
    }

    @Override
    public boolean insert(MenuModel menu) {
        _collection.insertOne(menu);
        return true;
    }

    @Override
    public boolean update(MenuModel menu) {
        var result = _collection.replaceOne(eq("menuId", menu.getMenuId()), menu);
        return result.getModifiedCount() > 0;
    }

    @Override
    public boolean delete(String menuId) {
        var result = _collection.deleteOne(eq("menuId", menuId));
        return result.getDeletedCount() > 0;
    }

    @Override
    public MenuModel getById(String menuId) {
        return _collection.find(eq("menuId", menuId)).first();
    }

    @Override
    public List<MenuModel> getAll() {
        return _collection.find().into(new ArrayList<>());
    }

    @Override
    public PagedResponse<MenuModel> getAll(PagedRequest<?> request) {
        return MongoPageUtils.paginate(_collection, null, request);
    }
}
