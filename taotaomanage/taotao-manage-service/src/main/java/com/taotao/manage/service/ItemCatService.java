package com.taotao.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.pojo.ItemCat;

@Service
public class ItemCatService extends BaseService<ItemCat> {

    // @Autowired
    // private ItemCatMapper itemCatMapper;

    // public List<ItemCat> queryItemCatListByParentId(Long pid) {
    // ItemCat record = new ItemCat();
    // record.setParentId(pid); // 查询参数
    // return this.itemCatMapper.select(record);
    // }

    // @Override
    // public Mapper<ItemCat> getMapper() {
    // return this.itemCatMapper;
    // }


    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String REDIS_KEY = "TAOTAO_MANAGE_ITEM_CAT_API"; // 规则：项目名_模块名_业务名

    private static final Integer REDIS_TIME = 60 * 60 * 24 * 30 * 3;




}
