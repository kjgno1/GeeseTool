package com.ptn.geesetool.services;

import com.ptn.geesetool.dao.DataCrudImpl;
import com.ptn.geesetool.dao.IDataCrud;
import com.ptn.geesetool.model.ImageInfo;
import javafx.concurrent.Task;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

public class UpdateTask extends Task<Void> {
    private String id;
    private String type;
    private ImageInfo imageInfo;
    private IDataCrud iDataCrud;



    public UpdateTask(String id, String type) {
        this.id = id;
        this.type = type;
        iDataCrud = new DataCrudImpl();
        imageInfo = iDataCrud.findImgById(id);
    }

    public UpdateTask() {
    }

    @Override
    protected Void call() throws Exception {
        if("D".equalsIgnoreCase(type)){
            download();
        } else {
            update();
        }

        return null;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void update() throws IOException {

        Document doc2 = Jsoup.connect(iDataCrud.findTypeById(1).getValue() + imageInfo.getId()).get();

        try {
            Element e1 = doc2.getElementsByClass("text-note").get(0);
            if(!StringUtil.isBlank(e1.text()))
                imageInfo.setDescriptions(e1.text());
        } catch (Exception e) {
        }

        try {
            Element e2 = doc2.getElementsByClass("m-search__related-results").get(0);

            Elements e3 = e2.getElementsByClass("c-link__pill");
            if(e3 != null) {
                imageInfo.setTags(e3.stream().map(x1 -> x1.text()).collect(Collectors.joining(",")));
            }

        } catch (Exception e) {
        }

        iDataCrud.saveOrUpdate(imageInfo);
    }

    private void download() throws Exception {
        DownloadImage.action(imageInfo.getUrl(),imageInfo.getName());
    }
}
