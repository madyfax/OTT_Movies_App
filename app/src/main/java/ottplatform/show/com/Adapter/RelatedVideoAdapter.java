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

public class RelatedVideoAdapter extends RecyclerView.Adapter<RelatedVideoAdapter.related> {

    ArrayList<MovieDataItem> categoryItems;
    Context context;

    public RelatedVideoAdapter(ArrayList<MovieDataItem> categoryItems, Context context) {
        this.categoryItems = categoryItems;
        this.context = context;
    }

    @NonNull
    @Override
    public related onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.show_more_item,parent,false);
        return new related(view);
    }

    @Override
    public void onBindViewHolder(@NonNull related holder, int position) {
        Glide.with(context).load("http://img.youtube.com/vi/"+categoryItems.get(position).getVideo_path()+"/0.jpg").into(holder.igg);
        holder.igg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.setvideo(categoryItems.get(position).getVideo_path());
                MyApp.setTitle(categoryItems.get(position).getTitle());
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

    public class related extends RecyclerView.ViewHolder{
        ImageView igg;
        public related(@NonNull View itemView) {
            super(itemView);
            igg=itemView.findViewById(R.id.igg);
        }
    }
}
