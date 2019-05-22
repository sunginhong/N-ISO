package com.example.n_iso;

import android.content.ContentValues;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.NestedScrollView;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.view.Menu;
import android.view.MenuItem;

import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import android.os.AsyncTask;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Main_TabFragment1 extends Fragment {

    LinearLayout layout;
    Main_TabFragment1_RecyclerAdapter Main_TabFragment1_RecyclerAdapter;
    List<Main_TabFragment1_DataRecycle> recyclelist = new ArrayList<>();
    List<Main_TabFragment1_DataVp> vplist = new ArrayList<Main_TabFragment1_DataVp>();

    ViewPager main_tab_fragment_1_vp;
    RecyclerView main_tab_fragment_1_recycler;
    NestedScrollView main_tab_fragment_1_nestedScrollView;
//    Toolbar toolbar;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public Main_TabFragment1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        searchNaver("네이버 설계(디자인)");
//        ProcessXmlTask xmlTask = new ProcessXmlTask();
//        xmlTask.execute("https://rss.blog.naver.com/nvr_design.xml?rss=1.0");
//        new FetchMetadataFromURL().execute();

        data_parse();

        layout = (LinearLayout) inflater.inflate(R.layout.main_tab_fragment_1, container, false);

        main_tab_fragment_1_recycler = layout.findViewById(R.id.main_tab_fragment_1_recycler);
        main_tab_fragment_1_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Main_TabFragment1_RecyclerAdapter = new Main_TabFragment1_RecyclerAdapter(getContext(),recyclelist);
        main_tab_fragment_1_recycler.setAdapter(Main_TabFragment1_RecyclerAdapter);

        main_tab_fragment_1_nestedScrollView = layout.findViewById(R.id.main_tab_fragment_1_nestedScrollView);
        main_tab_fragment_1_nestedScrollView.setSmoothScrollingEnabled(true);

        return layout;
    }

    public void data_parse(){

        /// recycleView data Add
        recyclelist.add(new Main_TabFragment1_DataRecycle("ui","a_윤무영","https://cdn.dribbble.com/users/4859/screenshots/6374407/online_doctor_consultation_4x.png", "스마트에디터 ONE 개편에 맞추어 오픈된 브랜드 사이드에 적용된 브랜드 영상과 로고 모션과"));
        recyclelist.add(new Main_TabFragment1_DataRecycle("motion","c_이상민","https://cdn.dribbble.com/users/4859/screenshots/6381781/summer_is_comming_2x.png", "스마트에디터 ONE 개편에 맞추어 오픈된 브랜드 사이드에 적용된 브랜드 영상과 로고 모션과"));
        recyclelist.add(new Main_TabFragment1_DataRecycle("logo","a_이정익","https://cdn.dribbble.com/users/4859/screenshots/4640959/dsx.png", "스마트에디터 ONE 개편에 맞추어 오픈된 브랜드 사이드에 적용된 브랜드 영상과 로고 모션과"));
        recyclelist.add(new Main_TabFragment1_DataRecycle("motion","b_문경훈","https://cdn.dribbble.com/users/4859/screenshots/6381781/summer_is_comming_2x.png", "스마트에디터 ONE 개편에 맞추어 오픈된 브랜드 사이드에 적용된 브랜드 영상과 로고 모션과"));
        recyclelist.add(new Main_TabFragment1_DataRecycle("ui","a_김학진","https://cdn.dribbble.com/users/4859/screenshots/6374407/online_doctor_consultation_4x.png", "스마트에디터 ONE 개편에 맞추어 오픈된 브랜드 사이드에 적용된 브랜드 영상과 로고 모션과"));
        recyclelist.add(new Main_TabFragment1_DataRecycle("motion","b_이민형","https://cdn.dribbble.com/users/4859/screenshots/6381781/summer_is_comming_2x.png", "스마트에디터 ONE 개편에 맞추어 오픈된 브랜드 사이드에 적용된 브랜드 영상과 로고 모션과"));
        recyclelist.add(new Main_TabFragment1_DataRecycle("logo","c_홍성인","https://cdn.dribbble.com/users/4859/screenshots/4640959/dsx.png", "스마트에디터 ONE 개편에 맞추어 오픈된 브랜드 사이드에 적용된 브랜드 영상과 로고 모션과"));

        new Thread() {
            @Override
            public void run() {
                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            (getActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int n = 1;
                                    while(n < MainActivity_Splash.display-1) {
                                        n = n + 1;
                                        vplist.add(new Main_TabFragment1_DataVp(MainActivity_Splash.title[n-0], MainActivity_Splash.desc[n-1], MainActivity_Splash.link[n-0], MainActivity_Splash.thumbImage[n-0]));
                                    }

                                    main_tab_fragment_1_vp = layout.findViewById(R.id.main_tab_fragment_1_vp);
                                    Main_TabFragment1_VpAdapter mAdapter = new Main_TabFragment1_VpAdapter(getContext(), vplist);
                                    main_tab_fragment_1_vp.setAdapter(mAdapter);
                                    main_tab_fragment_1_vp.setClipToPadding(false);
                                    main_tab_fragment_1_vp.setOffscreenPageLimit(vplist.size()-1);
                                }
                            });
                        }
                    }).start();
                } catch (Exception e) {

                }

            }
        }.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onPrepareOptionsMenu(menu);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setActivated(true);
        searchView.setQueryHint("타이틀을 입력해주세요");
//        searchView.onActionViewExpanded();
//        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (Main_TabFragment1_RecyclerAdapter != null){
                    Main_TabFragment1_RecyclerAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return;
    }

    public void searchNaver(final String searchObject) { // 검색어 = searchObject로 ;
        final String clientId = "sr8uCRwBgajxP4fEdHvQ";//애플리케이션 클라이언트 아이디값";
        final String clientSecret = "gTGTVRABeZ";//애플리케이션 클라이언트 시크릿값";
         // 보여지는 검색결과의 수
        final int searchResult_N = 5;
        final int display = 5+1;
        final String[] title = new String[display];
        final String[] link = new String[display];
        final String[] description = new String[display];
        final String[] bloggername = new String[display];
        final String[] postdate = new String[display];
        final String[] bloggerlink = new String[display];

        // 네트워크 연결은 Thread 생성 필요
        new Thread() {

            @Override
            public void run() {
                final StringBuilder sb;//
                try {
                    String text = URLEncoder.encode(searchObject, "UTF-8");
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
//                    System.out.println(sb);

                    String data = sb.toString();
                    String[] array;
                    array = data.split("\"");

                    int k = 0;
                    for (int i = 0; i < array.length; i++) {
                        if (array[i].equals("title"))
//                            title[k] = array[i + 2].replaceAll("<br />", "\n");
                            title[k] = array[i + 2].replaceAll("\\<.*?>","");
                        if (array[i].equals("link"))
                            link[k] = array[i + 2];
                        if (array[i].equals("description"))
                            description[k] = array[i + 2].replaceAll("\\<.*?>","");
                        if (array[i].equals("bloggername"))
                            bloggername[k] = array[i + 2];
                        if (array[i].equals("bloggerlink"))
                            bloggerlink[k] = array[i + 2];
                        if (array[i].equals("postdate")) {
                            postdate[k] = array[i + 2];
                            k++;
                        }
                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            (getActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int n = 0;
                                    while(n < searchResult_N) {
                                        n = n + 1;
//                                        vplist.add(new Main_TabFragment1_DataVp(postdate[n], title[n],"https://cdn.dribbble.com/users/4859/screenshots/6381781/summer_is_comming_2x.png"));
                                    }
//                                    main_tab_fragment_1_vp = layout.findViewById(R.id.main_tab_fragment_1_vp);
//                                    Main_TabFragment1_VpAdapter mAdapter = new Main_TabFragment1_VpAdapter(getContext(), vplist);
//                                    main_tab_fragment_1_vp.setAdapter(mAdapter);
//                                    main_tab_fragment_1_vp.setClipToPadding(false);
//                                    main_tab_fragment_1_vp.setOffscreenPageLimit(vplist.size()-1);
                                }
                            });
                        }
                    }).start();
//                    Log.d("ㄴㄴㄴㄴㄴ", "title잘나오니: " + title[0] + title[1] + title[2]);
            } catch (Exception e) {

            }

            }
        }.start();
    }

//    String urlAddress = "http://www.kma.go.kr/weather/main.jsp#1159068000";
//    Handler handler = new Handler();

    public void JsoupAsyncTask(final String searchObject) { // 검색어 = searchObject로 ;
        final String this_url;

        new Thread() {

            @Override
            public void run() {
                final StringBuilder sb;//
                try {

                } catch (Exception e) {

                }

            }
        }.start();
    }

    private class FetchMetadataFromURL extends AsyncTask<Void, Void, Void> {
        String websiteTitle, websiteDescription, imgurl;
        String URL;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(URL).get();
                websiteTitle = document.title();

                Elements metaElems = document.select("meta");
                for (Element metaElem : metaElems) {
                    String property = metaElem.attr("property");
//                    Log.e("Property", "Property =" + property + " \n Value =" + metaElem.attr("content"));
                }


                websiteDescription = metaElems.attr("content");
                Elements metaOgImage = document.select("meta[property=og:image]");

                if (metaOgImage != null) {
//                    System.out.println("aaaaaaaa"+document.outerHtml());
//                    imgurl = metaOgImage.first().attr("content");
//                    System.out.println("src :<<<------>>> " + ogImage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//            System.out.println("sssssss"+imgurl);
//            System.out.println("websiteTitle"+websiteTitle);
//            text.setText("Title : " + websiteTitle + "\n\nImage Url :: " + imgurl);
//
//            //t2.setText(websiteDescription);
//            Picasso.with(getApplicationContext()).load(imgurl).into(imgOgImage);

        }

    }

}