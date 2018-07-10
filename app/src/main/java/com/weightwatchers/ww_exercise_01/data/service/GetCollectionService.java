package com.weightwatchers.ww_exercise_01.data.service;

import com.weightwatchers.ww_exercise_01.data.model.FoodRecipe;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCollectionService {
    @GET("/assets/cmx/us/messages/collections.json") Call<List<FoodRecipe>> getCollections();
}
