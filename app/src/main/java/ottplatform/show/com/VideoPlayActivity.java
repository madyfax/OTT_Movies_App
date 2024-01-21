package ottplatform.show.com;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import ottplatform.show.com.Adapter.RelatedVideoAdapter;

public class VideoPlayActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    ImageView watchlist;
    TextView tvv;
    RecyclerView related;
    ArrayList<MovieDataItem> categoryItems=new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    RelatedVideoAdapter relatedVideoAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getSupportActionBar().hide();
        tvv=findViewById(R.id.tvv);
        tvv.setText(MyApp.getTitle());
        youTubePlayerView=findViewById(R.id.youtube_player_view);
        watchlist=findViewById(R.id.watchlist);
        related=findViewById(R.id.related);
        watchload();

        Log.d("TAG", "onCreate: "+MyApp.getvideo());
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = MyApp.getvideo();
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
        watchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=MyApp.database.rawQuery("select * from Watchlist where video_path='"+MyApp.getvideo()+"'",null);
                if (cursor!=null) {
                    if (cursor.moveToNext()) {
                        MyApp.database.execSQL("delete from Watchlist where video_path='"+MyApp.getvideo()+"'");
                        watchlist.setImageDrawable(getResources().getDrawable(R.drawable.baseline_library_add_24,getApplicationContext().getTheme()));
                    }else {
                        watchlist.setImageDrawable(getResources().getDrawable(R.drawable.tick,getApplicationContext().getTheme()));
                     MyApp.database.execSQL("insert into Watchlist(video_path) values ('"+MyApp.getvideo()+"')");
                        Toast.makeText(VideoPlayActivity.this, "Added", Toast.LENGTH_SHORT).show();

                    }
                }
            }

        });
       gridLayoutManager=new GridLayoutManager(VideoPlayActivity.this,2);
        related.setLayoutManager(gridLayoutManager);
         relatedVideoAdapter=new RelatedVideoAdapter(MyApp.getMyList(),VideoPlayActivity.this);
        related.setAdapter(relatedVideoAdapter);

    }
    public void watchload(){
        Cursor cursor=MyApp.database.rawQuery("select * from Watchlist where video_path='"+MyApp.getvideo()+"'",null);
        if (cursor!=null) {
            if (cursor.moveToNext()) {
                watchlist.setImageDrawable(getResources().getDrawable(R.drawable.tick, getApplicationContext().getTheme()));
            }else{
               watchlist.setImageDrawable(getResources().getDrawable(R.drawable.baseline_library_add_24, getApplicationContext().getTheme()));

            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
