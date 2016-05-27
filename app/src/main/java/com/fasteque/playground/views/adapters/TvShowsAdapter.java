package com.fasteque.playground.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasteque.playground.R;
import com.fasteque.playground.model.entities.TvShow;
import com.fasteque.playground.utils.MovieDbConstants;
import com.jakewharton.rxbinding.view.RxView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by danielealtomare on 05/06/15.
 * Project: Playground
 */
public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder> {

    private final PublishSubject<View> tvShowView = PublishSubject.create();
    private final List<TvShow> tvShows = new ArrayList<>();
    private final Context context;

    public TvShowsAdapter(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public TvShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_tv_show, parent, false);

        TvShowViewHolder tvShowViewHolder = new TvShowViewHolder(view);

        RxView.clicks(view).takeUntil(RxView.detaches(parent))
                .map(new Func1<Void, View>() {
                    @Override
                    public View call(Void aVoid) {
                        return view;
                    }
                })
                .subscribe(tvShowView);

        return tvShowViewHolder;
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    @Override
    public void onBindViewHolder(TvShowViewHolder holder, int position) {
        holder.bindTvShow(tvShows.get(position));
    }

    public void appendTvShows(List<TvShow> tvShows) {
        this.tvShows.addAll(tvShows);
        notifyDataSetChanged();
    }

    public void insertTvShows(List<TvShow> tvShows) {
        this.tvShows.clear();
        appendTvShows(tvShows);
    }

    public Observable<View> onClickTvShow() {
        return tvShowView;
    }

    public TvShow getTvShowAtPosition(int position) {
        if(tvShows.size() > 0 && position < tvShows.size()) {
            return tvShows.get(position);
        } else {
            return null;
        }
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_tv_show_cover)
        ImageView tvShowCover;

        @BindView(R.id.item_tv_show_title)
        TextView tvShowTitle;

        public TvShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTvShow(TvShow tvShow) {
            tvShowTitle.setText(tvShow.getName());
            Picasso.with(context)
                    .load(MovieDbConstants.getBasicStaticUrl()
                            + MovieDbConstants.getPosterPreferredSize()
                            + tvShow.getPoster_path())
                    .fit().centerCrop()
                    .into(tvShowCover);
        }
    }
}
