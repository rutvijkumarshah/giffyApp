package com.github.gogiffy.models;

/**
 *
 * Created by Rutvijkumar Shah on 7/8/16.
 *
 */
public class GiphyImage{

	public class ImgInfo{
		public String url;
		String width;
		String height;
		String size;
	}
	public ImgInfo original;
	public ImgInfo downsized;
}