package ottplatform.show.com;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestApi {

    @GET
    Call<ResponseBody> getapi(@Url String url);
}
