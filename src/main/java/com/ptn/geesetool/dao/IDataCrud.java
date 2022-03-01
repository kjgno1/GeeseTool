package com.ptn.geesetool.dao;

import com.ptn.geesetool.model.ImageInfo;
import com.ptn.geesetool.model.TypeCrawl;

import java.util.List;

public interface IDataCrud {


    <T> void saveOrUpdate(T t);

    List<ImageInfo> findAll();

    ImageInfo findImgById(String id);

    TypeCrawl findTypeById(int id);

}
