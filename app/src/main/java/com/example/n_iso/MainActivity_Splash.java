package com.example.n_iso;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity_Splash extends AppCompatActivity {

    static Context context;

    static String streamTitle = "";
    static String streamTitle_desc = "";
    static String streamTitle_link = "";

    static final int display = 6+1;
    static final String[] title = new String[display];
    static final String[] desc = new String[display];
    static final String[] link = new String[display];
    static final String[] thumbImage = new String[display];

    static int mainList_n = 0;
    static List<Integer> mainListSortIndex_Interaction = new ArrayList<Integer>();
    static List<String> mainListTitle = new ArrayList<>();
    static List<String> mainListSubTitle = new ArrayList<>();
    static List<String> mainListSynopTitle = new ArrayList<>();
    static List<String> mainListUrl = new ArrayList<>();
    static List<String> mainListThumb = new ArrayList<>();
    static List<String> mainListCategoryList= new ArrayList<>();
    static List<String> mainListCategory = new ArrayList<>();

    static String aboutTitle = "";
    static String aboutPtag_kor = "";
    static String aboutPtag_eng = "";
    static String aboutFooter = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new NetworkTask_Get_About_Parse("http://n-interaction.com/?page_id=8", null).execute();

        new NetworkTask_Get_Ninteraction_Parse("http://n-interaction.com", null, 0).execute();
        ProcessXmlTask xmlTask = new ProcessXmlTask();
        xmlTask.execute("https://rss.blog.naver.com/nvr_design.xml?rss=1.0");
    }

    private void splashEnd() {
        Intent intent=new Intent(MainActivity_Splash.this, MainActivity_MainView.class);
        startActivity(intent);
        finish();
    }

    private class ProcessXmlTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... urls) {
            try {

                URL rssUrl = new URL(urls[0]);
                SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
                SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
                XMLReader myXMLReader = mySAXParser.getXMLReader();
                RSSHandler myRSSHandler = new RSSHandler();
                myXMLReader.setContentHandler(myRSSHandler);
                InputSource myInputSource = new InputSource(rssUrl.openStream());
                myXMLReader.parse(myInputSource);

                int n = 1;
                while(n < display-1) {
                    n = n + 1;
                    NetworkTask_Get_NaverBlogThumImg networkTask_getThumImg = new NetworkTask_Get_NaverBlogThumImg(link[n - 0], null, n - 0);
                    networkTask_getThumImg.execute();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (ParserConfigurationException e) {
                e.printStackTrace();

            } catch (SAXException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
        }
    }

    static class RSSHandler extends DefaultHandler {
        ///////////////
        final int stateUnknown = 0;
        final int stateTitle = 1;
        int state = stateUnknown;

        int numberOfTitle = 0;
        String strTitle = "";
        String strElement = "";

        ///////////////
        final int stateUnknown_desc = 0;
        final int stateTitle_desc = 1;
        int state_desc = stateUnknown_desc;

        int numberOfTitle_desc = 0;
        String strTitle_desc = "";
        String strElement_desc = "";

        ///////////////
        final int stateUnknown_link = 0;
        final int stateTitle_link = 1;
        int state_link = stateUnknown_link;

        int numberOfTitle_link = 0;
        String strTitle_link = "";
        String strElement_link = "";

        @Override
        public void startDocument() throws SAXException {
            strTitle = "--- Start Document ---\n";

            strTitle_desc += "--- Start Document ---";

            strTitle_link += "--- Start Document ---";
        }

        @Override
        public void endDocument() throws SAXException {
            strTitle += "--- End Document ---";
            streamTitle = "Number Of Title: " + String.valueOf(numberOfTitle) + "\n" + strTitle;

            strTitle_desc += "--- End Document ---";
            streamTitle_desc = "Number Of Desc: " + String.valueOf(numberOfTitle_desc) + "\n" + strTitle_desc;

            strTitle_link += "--- End Document ---";
            streamTitle_link = "Number Of Link: " + String.valueOf(numberOfTitle_link) + "\n" + strTitle_link;
        }


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (localName.equalsIgnoreCase("title")) {
                state = stateTitle;
                strElement = "";
                numberOfTitle++;
            } else {
                state = stateUnknown;
            }

            if (localName.equalsIgnoreCase("description")) {
                state_desc = stateTitle_desc;
                strElement_desc = "";
                numberOfTitle_desc++;
            } else {
                state_desc = stateUnknown_desc;
            }

            if (localName.equalsIgnoreCase("link")) {
                state_link = stateTitle_link;
                strElement_link = "";
                numberOfTitle_link++;
            } else {
                state_link = stateUnknown_link;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (localName.equalsIgnoreCase("title")) {
                strTitle += strElement + "\n";
            }
            state = stateUnknown;

            if (localName.equalsIgnoreCase("description")) {
                strTitle_desc += strElement_desc + "\n";
            }
            state_desc = stateUnknown_desc;

            if (localName.equalsIgnoreCase("link")) {
                strTitle_link += strElement_link + "\n";
            }
            state_link = stateUnknown_link;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String strCharacters = new String(ch, start, length);
            String strCharacters_link = new String(ch, start, length);
            if (state == stateTitle) {
                strElement += strCharacters;
                if (numberOfTitle < display+1){
                    title[numberOfTitle-1] = strElement;
                }
            }
            if (state_desc == stateTitle_desc) {
                strElement_desc += strCharacters_link;
                if (numberOfTitle_desc < display){
                    desc[numberOfTitle_desc-1] = strElement_desc.replaceAll("&#x27;","");
                }
            }
            if (state_link == stateTitle_link) {
                strElement_link += strCharacters_link;
                if (numberOfTitle_link < display+1){
                    link[numberOfTitle_link-1] = strElement_link;
                }
            }
        }

    }

    private class NetworkTask_Get_NaverBlogThumImg extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        String websiteTitle, websiteDescription, imgurl;
        private String url_mobile;
        private int idx;

        public NetworkTask_Get_NaverBlogThumImg(String url, ContentValues values, int idx) {
            this.url = url;
            this.values = values;
            this.url_mobile = url.replaceAll("https://","https://m.");
            this.idx = idx;

            try {
                Document document = Jsoup.connect(url_mobile).get();
                websiteTitle = document.title();

                Elements metaElems = document.select("meta");
                for (Element metaElem : metaElems) {
                    String property = metaElem.attr("property");
                }
                websiteDescription = metaElems.attr("content");
                Elements metaOgImage = document.select("meta[property=og:image]");

                if (metaOgImage != null) {
                    imgurl = metaOgImage.first().attr("content");
                    thumbImage[idx] = imgurl;
                    if (idx == display-1){
                        splashEnd();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url_mobile, values); // 해당 URL로 부터 결과물을 얻어온다.
            try {
                Document document = Jsoup.connect(url_mobile).get();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

    private class NetworkTask_Get_Ninteraction_Parse extends AsyncTask<Void, Void, Void> {

        private URL Url;
        private String strUrl,strCookie,result,websiteTitle;

        public NetworkTask_Get_Ninteraction_Parse(String url, ContentValues values, int idx) {
            strUrl = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Url = new URL(strUrl);
                HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                conn.setRequestMethod("GET"); // get방식 통신
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.setDefaultUseCaches(false);

                strCookie = conn.getHeaderField("Set-Cookie");
                InputStream is = conn.getInputStream();

                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line+ "\n");
                }

                result = builder.toString();
                /////
                Document document = Jsoup.connect(strUrl).get();
                Elements div = document.select("div");
                Elements menuItemObjectPost = document.select("a");
                Elements date = document.select("div[class=date]");
                Elements category = document.select("div[class=category]");
                /////
                String menuItemObjectPostText = menuItemObjectPost.text();
                String classDateText = date.text();
                String classCategoryText = category.text();

                List<Element> menuItemObjectPost_atag = new ArrayList<>();
                for (Element myStr : menuItemObjectPost) {
                    menuItemObjectPost_atag.add(myStr);
                }

                int n = 0;
                List<Element> menuItemObjectPost_atag_result = new ArrayList<>();
                while(n < menuItemObjectPost_atag.size()-1) {
                    n = n + 1;
                    if (n > 1){
                        if (!menuItemObjectPost_atag_result.contains(menuItemObjectPost_atag.get(n))) {
                            menuItemObjectPost_atag_result.add(menuItemObjectPost_atag.get(n));
                        }
                    }
                }

                mainList_n = menuItemObjectPost_atag_result.size()/2 - 1;

                ///// n-interaction 사이트 -> title/ href 저장
                for (int i = 0; i < mainList_n; i++) {
                    if (i != mainList_n){
                        mainListTitle.add(menuItemObjectPost_atag_result.get(i).text());
                        mainListUrl.add(menuItemObjectPost_atag_result.get(i).attr("href"));

                        NetworkTask_Get_Ninteraction_ThumImg_Parse networkTask_getThumImg = new NetworkTask_Get_Ninteraction_ThumImg_Parse(menuItemObjectPost_atag_result.get(i).attr("href"), null, n - 0);
                        networkTask_getThumImg.execute();
                    }
                }

            }catch(MalformedURLException | ProtocolException exception) {
                exception.printStackTrace();
            }catch(IOException io){
                io.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }

    private class NetworkTask_Get_Ninteraction_ThumImg_Parse extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        String websiteTitle, websiteDescription, imgurl;
        private String url_mobile;
        private int idx;

        public NetworkTask_Get_Ninteraction_ThumImg_Parse(String url, ContentValues values, int idx) {
            this.url = url;
            this.values = values;
            this.url_mobile = url.replaceAll("https://","https://m.");
            this.idx = idx;

            try {
                Document document = Jsoup.connect(url_mobile).get();
                websiteTitle = document.title();

                /////
                Elements div = document.select("div");
                Elements img = document.select("img");
                Elements text = document.select("p");
                Elements category = document.select("div[class=category]");

                String str = category.get(0).text();
                String[] array = str.split(",");
                //출력
                mainListCategoryList.add(category.get(0).text());

                for(int k = 0; k < array.length; k++) {
                    if (array[k].equals(" Interaction") || array[k].equals(" motion") || array[k].equals(" Logo")){
                        mainListCategory.add(array[k]);
                    }
                }
                mainListThumb.add(img.get(1).attr("src"));
                mainListSubTitle.add(text.get(0).text());


                String text1 = text.get(2).toString().replace("<p>", "").replace("</p>", "");
                String text2 = text.get(3).toString().replace("<p>", "").replace("</p>", "");
                String lineSep = System.getProperty("line.separator");
                String ptag_text = text1;
                String ptag_text_F = ptag_text.replaceAll("<br>", lineSep);
                mainListSynopTitle.add(ptag_text_F);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url_mobile, values); // 해당 URL로 부터 결과물을 얻어온다.
            try {
                Document document = Jsoup.connect(url_mobile).get();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }


    private class NetworkTask_Get_About_Parse extends AsyncTask<Void, Void, Void> {

        private URL Url;
        private String strUrl,strCookie,result,websiteTitle;

        public NetworkTask_Get_About_Parse(String url, ContentValues values) {
            strUrl = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Url = new URL(strUrl);
                HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                conn.setRequestMethod("GET"); // get방식 통신
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.setDefaultUseCaches(false);

                strCookie = conn.getHeaderField("Set-Cookie");
                InputStream is = conn.getInputStream();

                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line+ "\n");
                }

                result = builder.toString();
                /////
                Document document = Jsoup.connect(strUrl).get();
                Elements div = document.select("div");
                Elements title = document.select("div[class=front-title]");
                Elements ptag = document.select("p");
                Elements footer = document.select("div[class=iso_footer]");
                /////
                String ptag_kor_data = "";
                String text1 = ptag_kor_data = ptag.get(0).toString().replace("<p>", "").replace("</p>", "");
                String text2 = ptag_kor_data = ptag.get(1).toString().replace("<p>", "").replace("</p>", "");
                ptag_kor_data = text1+"\n" +text2;
                String ptag_eng_data = ptag.get(2).toString().replace("<p>", "").replace("</p>", "");
                String lineSep = System.getProperty("line.separator");

                aboutTitle = title.text();
                aboutPtag_kor = ptag_kor_data.replaceAll("<br>", lineSep);
                aboutPtag_eng = ptag_eng_data.replaceAll("<br>", lineSep);
                aboutFooter = footer.get(0).text();

            }catch(MalformedURLException | ProtocolException exception) {
                exception.printStackTrace();
            }catch(IOException io){
                io.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }


}