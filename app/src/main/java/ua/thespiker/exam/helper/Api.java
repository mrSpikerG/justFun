package ua.thespiker.exam.helper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ua.thespiker.exam.model.Data;

public interface Api {
    String BASE_URL = "https://wild-sweater-goat.cyclic.cloud/";
    @GET("data")
    Call<List<Data>> getData();
}
