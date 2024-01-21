package ottplatform.show.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ottplatform.show.com.CategoryItems;
import ottplatform.show.com.MyApp;
import ottplatform.show.com.R;
import ottplatform.show.com.ShowmoreActivity;
import ottplatform.show.com.VideoPlayActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.set> {

    ArrayList<CategoryItems> categoryItems;
    Context context;

    public CategoryAdapter(ArrayList<CategoryItems> categoryItems, Context context) {
        this.categoryItems = categoryItems;
        this.context = context;

    }

    @NonNull
    @Override
    public set onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_items, parent, false);
        return new set(view);
    }


    @Override
    public void onBindViewHolder(@NonNull set holder, int position) {
        holder.tv.setText(categoryItems.get(position).getCategory());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rv.setLayoutManager(linearLayoutManager);
        MovieAdapter movieAdapter = new MovieAdapter(categoryItems.get(position).getMovieDataItems(),context);
        holder.rv.setAdapter(movieAdapter);
        holder.rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.setMyList(categoryItems.get(position).getMovieDataItems());
                MyApp.setmypos(position);
                Intent intent=new Intent(context, VideoPlayActivity.class);
                context.startActivity(intent);
            }
        });

        holder.show_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MyApp.setvideo(categoryItems.get(position).getMovieDataItems().get(position).getTitle());
//                MyApp.setMyList(categoryItems.get(position).getMovieDataItems());
//                MyApp.setmypos(position);
                MyApp.setMyList(categoryItems.get(position).getMovieDataItems());
                Intent intent=new Intent(context, ShowmoreActivity.class);
                context.startActivity(intent);
            }
        });

//        Glide.with(context).load(categoryItems.get(position).getThumail()).into(holder.iv);


    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public class set extends RecyclerView.ViewHolder {


        TextView tv;
        TextView show_more;
        RecyclerView rv;



        public set(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            rv = itemView.findViewById(R.id.rv);
            show_more = itemView.findViewById(R.id.show_more);
        }
    }


}
