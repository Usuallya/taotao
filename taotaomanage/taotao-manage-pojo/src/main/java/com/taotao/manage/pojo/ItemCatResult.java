package com.taotao.manage.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ItemCatResult {

    @JsonProperty("data")
    private List<ItemCatData> itemCats = new ArrayList<>();

    public List<ItemCatData> getItemCats(){
        return itemCats;
    }
    public void setItemCats(List<ItemCatData> itemCats){
        this.itemCats = itemCats;
    }
}
