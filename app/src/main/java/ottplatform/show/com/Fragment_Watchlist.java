package ottplatform.show.com;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ottplatform.show.com.Adapter.WatchlistAdapter;


public class Fragment_Watchlist extends Fragment {

    RecyclerView watchlist;
    ArrayList<MovieDataItem>categoryItems=new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    WatchlistAdapter watchlistAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_watchlist,container,false);
        watchlist=view.findViewById(R.id.watchlist);
        dataload();
        return view;
    }

    private void dataload(){
        categoryItems.clear();
        Cursor cursor=MyApp.database.rawQuery("select * from Watchlist",null);
        if (cursor!=null) {
            while (cursor.moveToNext()){
                String id=cursor.getString(0);
                String video_path=cursor.getString(1);
                categoryItems.add(new MovieDataItem(id,video_path));
                Log.d("TAG", "dataload: "+categoryItems.size());
            }
            gridLayoutManager =new GridLayoutManager(getActivity(),2);
            watchlist.setLayoutManager(gridLayoutManager);
            watchlistAdapter=new WatchlistAdapter(categoryItems,getActivity());
            watchlist.setAdapter(watchlistAdapter);
        }
    }
}
