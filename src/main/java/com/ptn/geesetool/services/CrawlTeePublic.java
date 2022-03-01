package com.ptn.geesetool.services;

import com.ptn.geesetool.dao.DataCrudImpl;
import com.ptn.geesetool.dao.IDataCrud;
import com.ptn.geesetool.model.ImageInfo;
import com.ptn.geesetool.utils.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrawlTeePublic extends CrawlBase implements ICrawl {

    private static final Object syncObj = new Object();

    @Override
    public String getUrlOrKeywords() {
        return this.getKeyWords();
    }

    @Override
    public String typeDownload() {
        return this.getTypeDownload();
    }

    @Override
    public int getThreadNumber() {
        return this.getFixedThread();
    }


    @Override
    public void crawlFromWebsite(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();


        Elements elementList = doc.getElementsByClass("jsDesignContainer");

        elementList.stream().forEach(x ->
                {
                    Element eUrl = x.getElementsByClass("cld-hidpi").get(0);
                    String eUrlValue = eUrl.attr("data-src");
                    Element eTag = x.getElementsByClass("ellip").get(0);
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.setId(FileUtil.regexTeepublic(eUrlValue)[0]);
                    imageInfo.setName(FileUtil.regexTeepublic(eUrlValue)[0] + ".png");
                    imageInfo.setUrl(FileUtil.regexTeepublic(eUrlValue)[1]);
                    imageInfo.setDescriptions(FileUtil.handleTags(eTag.text())[1]);
                    imageInfo.setTags(FileUtil.handleTags(eTag.text())[0]);


                    IDataCrud iDataCrud = new DataCrudImpl();
                    try {

                        iDataCrud.saveOrUpdate(imageInfo);
                        ExecutorService exeUpdate = Executors.newCachedThreadPool();
                        exeUpdate.submit(new UpdateTask(imageInfo.getId(),"U"));
                        exeUpdate.shutdown();

                        ExecutorService exeDownload = Executors.newCachedThreadPool();
                        exeDownload.submit(new UpdateTask(imageInfo.getId(),"D"));
                        exeDownload.shutdown();

//                        FileUtil.WriteToCSV(row, "output.csv", true);
                    } catch (Exception e) {
                        iDataCrud.saveOrUpdate(imageInfo);
                        e.printStackTrace();
                    }

                }
        );
    }
}
