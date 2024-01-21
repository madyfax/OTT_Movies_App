package ottplatform.show.com;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ottplatform.show.com.Adapter.ShowmoreAdapter;

public class ShowmoreActivity extends AppCompatActivity {
    RecyclerView more;

    ArrayList<MovieDataItem> categoryItems=new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    ShowmoreAdapter showmoreAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmore);
        more=findViewById(R.id.more);
        gridLayoutManager=new GridLayoutManager(this,2);
        more.setLayoutManager(gridLayoutManager);
       showmoreAdapter=new ShowmoreAdapter(MyApp.getMyList(),ShowmoreActivity.this);
        more.setAdapter(showmoreAdapter);

    }

}
