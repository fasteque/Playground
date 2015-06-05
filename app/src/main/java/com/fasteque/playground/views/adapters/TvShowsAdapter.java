package com.fasteque.playground.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasteque.playground.R;
import com.fasteque.playground.model.entities.TvShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by danielealtomare on 05/06/15.
 * Project: Playground
 */
public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder> {

    private List<TvShow> tvShows = new ArrayList<>();

    @Override
    public TvShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_tv_show, parent, false);

        TvShowViewHolder tvShowViewHolder = new TvShowViewHolder(view);

        // TODO: RxAndroid bindview to manage item clicks.

        return tvShowViewHolder;
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    @Override
    public void onBindViewHolder(TvShowViewHolder holder, int position) {
        TvShow tvShow = tvShows.get(position);

        holder.tvShowTitle.setText(tvShow.getName());

        // TODO: set cover image
    }

    public void appendTvShows(List<TvShow> tvShows) {
        this.tvShows.addAll(tvShows);
        notifyDataSetChanged();
    }


    public void insertTvShows(List<TvShow> tvShows) {
        this.tvShows.clear();
        appendTvShows(tvShows);
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_tv_show_cover)
        ImageView tvShowCover;

        @InjectView(R.id.item_tv_show_title)
        TextView tvShowTitle;

        public TvShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            tvShowCover.setDrawingCacheEnabled(true);
        }
    }
}
