package com.example.whatscooking.main;

import com.example.whatscooking.data.RecipeDao;
import com.example.whatscooking.di.ActivityScope;
import com.example.whatscooking.main.recipelist.MainFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface MainComponent {

    RecipeDao recipeDao();

    @Subcomponent.Factory
    interface Factory {
        MainComponent create();
    }

    /*With this Dagger knows MainFragment requests injection and
      that it has to provide its dependencies
     */
    void inject(MainFragment fragment);
}
