package com.userzrq.reflection.annotaionDemo.service.impl;


import com.userzrq.reflection.annotaionDemo.ServiceDaoFactory;
import com.userzrq.reflection.annotaionDemo.entity.Category;
import com.userzrq.reflection.annotaionDemo.entity.User;
import com.userzrq.reflection.annotaionDemo.service.CategoryService;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static List<String> permissions = new ArrayList<>();


    User user  = new User(1L,"userZrq","123456",permissions);

    ServiceDaoFactory factoryInstance = ServiceDaoFactory.getInstance();
    CategoryService service = factoryInstance.createDao("com.userzrq.reflection.annotaionDemo.service.CategoryService",CategoryService.class,user);

    @Override
    public void addCategory(Category category) {

    }

    @Override
    public void findCategory(String id) {

    }

    @Override
    public List<Category> getAllCategory() {
        return null;
    }
}
