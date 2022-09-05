package com.sparta.miniproject2.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YoutubeCrawling {

    public void process(String url) {
        Connection connection = Jsoup.connect(url);

        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert document != null;
        List<String> list = getDataList(document);
    }

    private List<String> getDataList(Document document) {
        List<String> list = new ArrayList<>();
        Elements selects = document.select("ytd-thumbnail ytd-img .img");

        for(Element select : selects) {
            System.out.println(select.html());
        }
        return list;
    }
}

