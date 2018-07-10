package com.weightwatchers.ww_exercise_01.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.weightwatchers.ww_exercise_01.data.model.FoodRecipe;
import com.weightwatchers.ww_exercise_01.data.service.APIService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRecipePresenter extends ViewModel implements RecipeViewPresenter.RecipePresenter {
    private MutableLiveData<List<FoodRecipe>> listOfRecipes;
    private RecipeViewPresenter.RecipeView recipeView;

    public FoodRecipePresenter(RecipeViewPresenter.RecipeView recipeView) {
        this.recipeView = recipeView;
    }

    public LiveData<List<FoodRecipe>> getRecipeList() {
        if (listOfRecipes == null) {
            listOfRecipes = new MutableLiveData<>();
            getFoodRecipe();
        }
        return listOfRecipes;
    }

    @Override public void getFoodRecipe() {
        recipeView.startLoading();
        Call<List<FoodRecipe>> response = APIService.getCollectionService().getCollections();
        response.enqueue(new Callback<List<FoodRecipe>>() {
            @Override public void onResponse(
                Call<List<FoodRecipe>> call, Response<List<FoodRecipe>> response) {
                recipeView.finishLoading();
                if (response != null && response.body() != null) {
                    if (listOfRecipes != null) listOfRecipes.setValue(response.body());
                }
            }

            @Override public void onFailure(
                Call<List<FoodRecipe>> call, Throwable t) {
                recipeView.finishLoading();
                recipeView.showErrorLoading();
            }
        });
    }

    @Override public void onClickView(FoodRecipe foodRecipe) {
        recipeView.showSnackBar(foodRecipe.getTitle());
    }
}
