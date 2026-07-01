package com.example.java_demo.repository.dal;

import com.example.java_demo.model.UserModel;
import com.example.java_demo.repository.interfaces.*;
import com.mongodb.client.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAL implements IUserDAL {
    private final MongoCollection<UserModel> _collection;

    public UserDAL(MongoDatabase database) {
        String collectionName = "UserCollection";
        this._collection = database.getCollection(collectionName, UserModel.class);
    }

    @Override
    public boolean insert(UserModel user) {
        _collection.insertOne(user);
        return true;
    }

    @Override
    public List<UserModel> getAll() {
        return _collection.find().into(new ArrayList<>());
    }
}
