package com.github.gogiffy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.gogiffy.R;
import com.github.gogiffy.activities.FullImageViewActivity;
import com.github.gogiffy.models.Gif;
import com.github.gogiffy.util.Constants;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;
import static  com.github.gogiffy.util.Constants.*;

/**
 * Created by Rutvijkumar Shah on 7/9/16.
 */
public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {


    public Gif getGifByPosition(int pos) {
        Gif gif=null;
        if(pos >=0 && pos < mGifs.size()){
            gif=mGifs.get(pos);
        }
        return gif;
    }

    interface GifProvider{
        Gif getGifByPosition(int pos);
    }
    private List<Gif> mGifs;
    private WeakReference<Context> mWkContext;

    public GifAdapter(List<Gif> gifs){
        mGifs=gifs;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_gif,parent,false);
        mWkContext=new WeakReference<Context>(parent.getContext());
        return  new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context=mWkContext.get();
        if(context!=null) {
            final Gif gif = mGifs.get(position);
            Picasso.with(context)
                    .load(gif.images.fixed_height_still.url)
                    .placeholder(R.drawable.optimized_image_loading)
                    .error(R.drawable.image_not_available)
                    .into(holder.mImageView);



        }
    }

    @Override
    public int getItemCount() {
        return mGifs.size();
    }

    public  void addNewResults(List<Gif> gifs){
        mGifs.clear();
        mGifs.addAll(gifs);
        notifyDataSetChanged();
    }

    void showFullImage(String url){
        final Context context = mWkContext.get();
        if(context !=null) {
            Log.d(TAG,  " URL ="+url);
            Intent intent = new Intent(context, FullImageViewActivity.class);
            intent.putExtra(Constants.EXTRA_FULL_IMG_URL,url);
            context.startActivity(intent);
        }
    }
    public void addAll(List<Gif> gifs){
        int curSize=getItemCount();
        mGifs.addAll(gifs);
        notifyItemRangeInserted(curSize, gifs.size());
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public GifAdapter mAdapter;
        public ViewHolder(View itemView,GifAdapter adapter) {
            super(itemView);
            mAdapter=adapter;
            mImageView=(ImageView) itemView.findViewById(R.id.imgGif);
            mImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getLayoutPosition(); // gets item position
            Log.d(TAG, ">>>>>    onclick of View Holder");
            Gif gif = mAdapter.getGifByPosition(position);
            if(gif !=null){
                Log.d(TAG, ">>>>>    onclick of View Holder : "+gif.url);
                Log.d(TAG, ">>>>>    onclick of View Holder : "+gif.images.original.url);
                mAdapter.showFullImage(gif.images.original.url);
            }

        }
    }

}
