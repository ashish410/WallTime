package ay3524.com.wallpapertime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import ay3524.com.wallpapertime.R;
import ay3524.com.wallpapertime.model.WallpaperUnsplash;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ashish on 29-12-2016.
 */

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    private ArrayList<WallpaperUnsplash> wallpaper;
    private Context context;
    private ListItemClickListener clickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public WallpaperAdapter(ArrayList<WallpaperUnsplash> wallpaperses, ListItemClickListener listener) {
        wallpaper = wallpaperses;
        clickListener = listener;
    }

    @Override
    public WallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.single_wallpaper, parent, shouldAttachToParentImmediately);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WallpaperViewHolder holder, final int position) {
        /*ImageLoader imageLoader = MyApplication.getInstance().getImageLoader();

        holder.wallpaperPoster.setImageUrl(wallpaper.get(position).getWebformatURL(), imageLoader);

        imageLoader.get(wallpaper.get(position).getWebformatURL(), ImageLoader.getImageListener(
                holder.wallpaperPoster, R.mipmap.ic_launcher, R.mipmap.ic_launcher));

        //Cache cache = MyApplication.getInstance().getRequestQueue().getCache();
        //Cache.Entry entry = cache.get(wallpaper.get(position).getWebformatURL());*/

        holder.likes.setText(wallpaper.get(position).getLikes());

        String buildSingleListImageUrl = wallpaper.get(position).getUrls_small();

        /*Glide.with(context)
                .load(wallpaper.get(position).getUrls_thumb())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        // Do something with bitmap here.
                        holder.wallpaperPoster.setImageBitmap(bitmap);

                        Glide.with(context)
                                .load(wallpaper.get(position).getUrls_small())
                                .asBitmap()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                        // Do something with bitmap here.
                                        holder.wallpaperPoster.setImageBitmap(bitmap);
                                    }
                                });
                    }
                });*/

        Glide.with(context)
                .load(buildSingleListImageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.wallpaperPoster);
    }

    @Override
    public int getItemCount() {
        return wallpaper.size();
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.wallpaperPoster)
        ImageView wallpaperPoster;
        @BindView(R.id.likes)
        TextView likes;

        WallpaperViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            clickListener.onListItemClick(clickedPosition);
        }
    }
}
