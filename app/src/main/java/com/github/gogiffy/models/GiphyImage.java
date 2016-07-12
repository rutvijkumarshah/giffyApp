package com.github.gogiffy.models;

/**
 * Created by Rutvijkumar Shah on 7/8/16.
 */
public class GiphyImage {

    public ImgInfo original;
    public ImgInfo fixed_height_still;
    public ImgInfo fixed_width_still;

    public class ImgInfo {
        public String url;
        String width;
        String height;
        String size;
    }
}