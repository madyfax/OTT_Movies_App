package ottplatform.show.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ottplatform.show.com.MovieDataItem;
import ottplatform.show.com.MyApp;
import ottplatform.show.com.R;
import ottplatform.show.com.VideoPlayActivity;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.list>{

    ArrayList<MovieDataItem> categoryItems;
    Context context;

    public WatchlistAdapter(ArrayList<MovieDataItem> categoryItems, Context context) {
        this.categoryItems = categoryItems;
        this.context = context;
    }

    @NonNull
    @Override
    public list onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.watchlist_item,parent,false);
        return new list(view);
    }

    @Override
    public void onBindViewHolder(@NonNull list holder, int position) {

        Glide.with(context).load("http://img.youtube.com/vi/"+categoryItems.get(position).getVideo_path()+"/0.jpg").into(holder.ig);

        holder.ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.setvideo(categoryItems.get(position).getVideo_path());
                MyApp.getmypos();
                Intent intent=new Intent(context, VideoPlayActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public class list extends RecyclerView.ViewHolder{
        ImageView ig;

        public list(@NonNull View itemView) {
            super(itemView);
            ig=itemView.findViewById(R.id.ig);
        }
    }
}
