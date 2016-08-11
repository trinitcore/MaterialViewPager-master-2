package com.github.florent37.materialviewpager.trendster.Items;

import android.graphics.Color;
import android.media.Image;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;

import java.util.HashMap;

/**
 * Created by cormac on 23/07/2016.
 */
public class Article {
    public String id;
    public String title;
    public String content;
    public String link;
    public String imageloc;
    public String catagory;
    public Color catagory_color;
    public String date;
    public String author;
    //public HashMap<String,Integer> image_urls;
    public SpannableStringBuilder formattedString;

    public Article(String id, String title, String content, String link, String imageloc, String catagory, String date, String author, SpannableStringBuilder formatted){
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.imageloc = imageloc;
        this.catagory = catagory;
        this.date = date;
        this.author = author;
        //this.image_urls = urls;
        this.formattedString = formatted;
        //this.formattedString.toString() = this.formattedString.replaceAll("\\<.*?>","");
    }
}
