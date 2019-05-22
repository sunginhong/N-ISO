package com.example.n_iso;

// 네이버 블로그 Open API 예제 - 카테고리조회
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIExamSearchBlog {
    public static StringBuilder sb;//

    public static void main(String[] args) {
        String clientId = "sr8uCRwBgajxP4fEdHvQ";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "gTGTVRABeZ";// 애플리케이션 클라이언트 시크릿값";\
        int display = 5; // 검색결과갯수. 최대100개
        try {
            String text = URLEncoder.encode("아트메가128", "utf-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog.json?query=" + text + "&display=" + display + "&";


            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            con.disconnect();
            System.out.println(sb);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
