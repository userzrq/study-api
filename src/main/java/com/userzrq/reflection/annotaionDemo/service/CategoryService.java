package com.userzrq.reflection.annotaionDemo.service;


import com.userzrq.reflection.annotaionDemo.ServiceDaoFactory;
import com.userzrq.reflection.annotaionDemo.entity.Category;
import com.userzrq.reflection.annotaionDemo.permission;

import java.util.List;

public interface CategoryService {


    @permission("添加分类")
    /*添加分类*/ void addCategory(Category category);

    /*查找分类*/
    void findCategory(String id);

    @permission("查找分类")
    /*查看分类*/ List<Category> getAllCategory();


}
