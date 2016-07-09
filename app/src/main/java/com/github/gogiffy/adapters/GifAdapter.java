package com.github.gogiffy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.gogiffy.R;
import com.github.gogiffy.models.Gif;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Rutvijkumar Shah on 7/9/16.
 */
public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {

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
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context=mWkContext.get();
        if(context!=null) {
            final Gif gif = mGifs.get(position);
            Picasso.with(context)
                    .load(gif.images.downsized.url)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.image_not_available)
                    .into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mGifs.size();
    }

    public void addAll(List<Gif> gifs){
        int curSize=getItemCount();
        mGifs.addAll(gifs);
        notifyItemRangeInserted(curSize, gifs.size());
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView) itemView.findViewById(R.id.imgGif);
        }
    }

}
