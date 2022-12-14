package com.sparta.miniproject2.crawling;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class YoutubeCrawling {


    @Transactional
    public String crawling(String url) {
//        String url = "https://www.youtube.com/watch?v=wkBRKBLDNAc";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();    //Document에는 페이지의 전체 소스가 저장된다
        } catch (IOException e) {
            e.printStackTrace();
        }

        //select를 이용하여 원하는 태그를 선택한다.


        assert doc != null;
        String element = Objects.requireNonNull(doc.select("meta[property^=og:image]").first()).attr("content");
        System.out.println("썸네일 " + element);

        return element;
    }

    @Transactional
    public boolean urlCheck(String url) {
//        String url = "https://www.youtube.com/watch?v=wkBRKBLDNAc";
        String urlCheck = url.substring(0,23);
        String urlCheck1 = new String("https://www.youtube.com");
        String urlCheck2 = new String("http://www.youtube.com/");

        boolean result = false;
        if (!urlCheck.equals(urlCheck1) && !urlCheck.equals(urlCheck2)) {
        } else {
            result = true;
        }
        return result;
    }

}

