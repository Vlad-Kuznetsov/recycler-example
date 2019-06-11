package ru.vlad.k;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ViewModelProviderUtils {

    <T> T registrateModel(FragmentActivity fragmentActivity, Class modelClass, T model) {
        ViewModelProvider of = ViewModelProviders.of(fragmentActivity, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) model;
            }
        });

        return (T) of.get(modelClass);
    }
}
