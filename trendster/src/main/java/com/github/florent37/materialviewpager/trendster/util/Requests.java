package com.github.florent37.materialviewpager.trendster.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;

import com.github.florent37.materialviewpager.trendster.Items.Article;

import com.github.florent37.materialviewpager.trendster.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by cormac on 24/07/2016.
 */
public class Requests{
    static String charset = "UTF-8";

    static String mother_url = "http://trendsterpress.com/feed.php";

    public static Activity activity;

    public static Article[] getFeaturedArticles(String catagory){
        try {
          JSONArray articles = doGet(mother_url,"type=wheel&query="+catagory).getJSONArray("articles");
            Article[] allarticles = new Article[articles.length()];
            for (int i=0; i<articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                String id = article.getString("id");
                String title = article.getString("title");
                String content = article.getString("content");
                String image = article.getString("image");
                String article_catagory = article.getString("catagory");
                String date = article.getString("date");
                String author = article.getString("author");
                String link = article.getString("link");

                Article art = new Article(id,title,content,link,image,article_catagory,date,author, null);
                allarticles[i] = art;
            }
            return allarticles;
        }catch (JSONException e){

        }
        return null;
        //
    }

    private static SpannableStringBuilder parseImages(String content) {
        Log.d("Requests","---BEGIN---");
        boolean continu = false;
        boolean afterpassingimage = false;
        int image_position = 0;
        HashMap<String,Integer> urls = new HashMap<>();
        SpannableStringBuilder builder = new SpannableStringBuilder();

        StringBuilder str = new StringBuilder();
        for (int ii = 0; ii < content.length(); ii++){
            char c = content.charAt(ii);
            str.append(c);
            if (c == '>'){
                if (continu){
                    Log.d("Requests","Got an image");
                    Document doc = Jsoup.parse(content);
                    Element img = doc.getElementsByTag("img").get(image_position);
                    String img_url = img.attr("src");
                    Log.d("Requests",img_url);

                    String readystring = str.toString().replaceAll("\\<.*?>","")
                                                        .replaceAll("&nbsp;","")
                                                        .replaceAll("&#39;","'")
                                                        .replaceAll("&rdquo;","'");
                    builder.append(readystring);

                    Bitmap bmp = getBitmapFromURL(img_url);
                    Drawable draw = new BitmapDrawable(bmp);
                    DisplayMetrics displaymetrics = new DisplayMetrics();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                    int width = displaymetrics.widthPixels;
                    int calcheight = width / bmp.getWidth() * bmp.getHeight();

                    draw.setBounds(0,0,width-90,calcheight);
                    //bmp = Bitmap.createScaledBitmap(bmp,3020,bmp.getHeight(),false);
                    ImageSpan span = new ImageSpan(draw);
                    builder.append("Image: ", span, 0);

                    str = new StringBuilder();
                    urls.put(img_url,ii);
                    image_position++;
                    continu = false;
                    afterpassingimage = true;
                }
            }
            if (ii == content.length()-1){
                String readystring = str.toString() .replaceAll("\\<.*?>","")
                                                    .replaceAll("&nbsp;","")
                                                    .replaceAll("&#39;","'")
                                                    .replaceAll("&rdquo;","'");
                builder.append(readystring);
            }
            if (continu){

                continue;
            }

            if (c == '<'){
                char c1 = content.charAt(ii+1);
                char c2 = content.charAt(ii+2);
                char c3 = content.charAt(ii+3);

                if (c1 == 'i' && c2 == 'm' && c3 == 'g'){
                    continu = true;

                }

            }

        }
        Log.d("Requests","---END---");
        return builder;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            long time = System.currentTimeMillis();
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            long time_end = System.currentTimeMillis();

            long length =time_end - time;

            Log.d("Download time", "Time "+String.valueOf(length));

            return myBitmap;
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
            return null;
        }
    }
    public static Article[] getCatagoryArticles(String catagory){
        try {
            JSONArray articles = doGet(mother_url,"type=catagory&query="+catagory+"&limit=1").getJSONArray("articles");
            Article[] allarticles = new Article[articles.length()];
            for (int i=0; i<articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);
                String id = article.getString("id");
                String title = article.getString("title");
                String content = article.getString("content");
                String image = article.getString("image");
                String article_catagory = article.getString("catagory");
                String date = article.getString("date");
                String author = article.getString("author");
                String link = article.getString("link");

                //content = content.replaceAll("\\<.*?>","");

                Article art = new Article(id,title,content,link,image,article_catagory,date,author,parseImages(content));
                allarticles[i] = art;
            }
            return allarticles;
        }catch (JSONException e){

        }
        return null;
    }

    private static JSONObject doGet(String url,String params){
        try {

            URLConnection connection = new URL(url + "?" + params).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            Timer timer = new Timer();
            InputStream response = connection.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(response));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
            String result = total.toString();



            Log.d("Url log", result);

            return new JSONObject(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
