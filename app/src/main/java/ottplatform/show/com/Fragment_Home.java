package ottplatform.show.com;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import ottplatform.show.com.Adapter.CategoryAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Fragment_Home extends Fragment{
    RecyclerView rv;
    ArrayList<CategoryItems> categoryItems = new ArrayList<>();
    ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv = view.findViewById(R.id.rv);
        pb=view.findViewById(R.id.pb);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        getapi();
//
        return view;

    }

    public void getapi() {
        categoryItems.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        RestApi restApi = retrofit.create(RestApi.class);
        Call<ResponseBody> call = restApi.getapi("https://drive.google.com/uc?export=download&id=1KmwkZ-w2bwzVF1-GBRlNuI2DHTTCFch3");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                pb.setVisibility(View.GONE);
                try {
                    String s = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(s);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            ArrayList<MovieDataItem> movieDataItems = new ArrayList<>();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String category = jsonObject.getString("category");
                            JSONArray data = jsonObject.getJSONArray("data");

                            Log.d("TAG", "onResponse: "+data);
                            for (int i1 = 0; i1 < data.length(); i1++) {
                                JSONObject jsonObject1 = data.getJSONObject(i1);
                                String title = jsonObject1.getString("title");
                                String video_path = jsonObject1.getString("video_path");
                                movieDataItems.add(new MovieDataItem(title, video_path));

                                Log.d("TAG", "onResponse: "+video_path);
                            }
                            categoryItems.add(new CategoryItems(category, movieDataItems));
                        }
                        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryItems, getActivity());
                        rv.setAdapter(categoryAdapter);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                pb.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

}
