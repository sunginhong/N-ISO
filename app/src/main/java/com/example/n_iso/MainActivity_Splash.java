package com.example.n_iso;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
                    NetworkTask_GetThumImg networkTask_getThumImg = new NetworkTask_GetThumImg(link[n - 0], null, n - 0);
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
                    desc[numberOfTitle_desc-1] =strElement_desc.replaceAll("&#x27;","");
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

    private class NetworkTask_GetThumImg extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        String websiteTitle, websiteDescription, imgurl;
        private String url_mobile;
        private int idx;

        public NetworkTask_GetThumImg(String url, ContentValues values, int idx) {
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
//                websiteTitle = document.title();

//                Elements metaElems = document.select("meta");
//                for (Element metaElem : metaElems) {
//                    String property = metaElem.attr("property");
////                    Log.e("Property", "Property =" + property + " \n Value =" + metaElem.attr("content"));
//                }
//                websiteDescription = metaElems.attr("content");
//                Elements metaOgImage = document.select("meta[property=og:image]");

//                if (metaOgImage != null) {
//                    imgurl = metaOgImage.first().attr("content");
//                    thumbImage[idx] = imgurl;
//                    Log.d("ssssssssss", "sssss"+imgurl);
//                }

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

}