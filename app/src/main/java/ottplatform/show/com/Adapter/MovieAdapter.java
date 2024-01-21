package ottplatform.show.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

import ottplatform.show.com.MovieDataItem;
import ottplatform.show.com.MyApp;
import ottplatform.show.com.R;
import ottplatform.show.com.VideoPlayActivity;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.set>{

    ArrayList<MovieDataItem>categoryItems;
    Context context;

    public MovieAdapter(ArrayList<MovieDataItem> categoryItems, Context context) {
        Collections.shuffle(categoryItems);
        this.categoryItems = categoryItems;
        this.context = context;


    }

    @NonNull
    @Override
    public set onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new set(view);
    }

    @Override
    public void onBindViewHolder(@NonNull set holder, int position) {
//        Glide.with(context).load(categoryItems.get(position).getThumail()).into(holder.iv);
        Log.d("TAG", "onBindViewHolder: "+categoryItems.get(position).getVideo_path());
        Glide.with(context).load("http://img.youtube.com/vi/"+categoryItems.get(position).getVideo_path()+"/0.jpg").into(holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
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

    public class set extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tvv;
        public set(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tvv=itemView.findViewById(R.id.tvv);

        }
    }

}
