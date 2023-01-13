package com.udacity.asteroidradar.databinding;
import com.udacity.asteroidradar.R;
import com.udacity.asteroidradar.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMainBindingImpl extends FragmentMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.activity_main_image_of_the_day_layout, 3);
        sViewsWithIds.put(R.id.textView, 4);
        sViewsWithIds.put(R.id.status_loading_wheel, 5);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private FragmentMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.ImageView) bindings[1]
            , (android.widget.FrameLayout) bindings[3]
            , (androidx.recyclerview.widget.RecyclerView) bindings[2]
            , (android.widget.ProgressBar) bindings[5]
            , (android.widget.TextView) bindings[4]
            );
        this.activityMainImageOfTheDay.setTag(null);
        this.asteroidRecycler.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.udacity.asteroidradar.screens.main.MainViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.udacity.asteroidradar.screens.main.MainViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelAsteroids((androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.domain.Asteroid>>) object, fieldId);
            case 1 :
                return onChangeViewModelPicOfDay((androidx.lifecycle.LiveData<com.udacity.asteroidradar.domain.PictureOfDay>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelAsteroids(androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.domain.Asteroid>> ViewModelAsteroids, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelPicOfDay(androidx.lifecycle.LiveData<com.udacity.asteroidradar.domain.PictureOfDay> ViewModelPicOfDay, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.domain.Asteroid>> viewModelAsteroids = null;
        java.util.List<com.udacity.asteroidradar.domain.Asteroid> viewModelAsteroidsGetValue = null;
        com.udacity.asteroidradar.domain.PictureOfDay viewModelPicOfDayGetValue = null;
        com.udacity.asteroidradar.screens.main.MainViewModel viewModel = mViewModel;
        androidx.lifecycle.LiveData<com.udacity.asteroidradar.domain.PictureOfDay> viewModelPicOfDay = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.asteroids
                        viewModelAsteroids = viewModel.getAsteroids();
                    }
                    updateLiveDataRegistration(0, viewModelAsteroids);


                    if (viewModelAsteroids != null) {
                        // read viewModel.asteroids.getValue()
                        viewModelAsteroidsGetValue = viewModelAsteroids.getValue();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.picOfDay
                        viewModelPicOfDay = viewModel.getPicOfDay();
                    }
                    updateLiveDataRegistration(1, viewModelPicOfDay);


                    if (viewModelPicOfDay != null) {
                        // read viewModel.picOfDay.getValue()
                        viewModelPicOfDayGetValue = viewModelPicOfDay.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            com.udacity.asteroidradar.utils.BindingAdaptersKt.bindImg(this.activityMainImageOfTheDay, viewModelPicOfDayGetValue);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            com.udacity.asteroidradar.utils.BindingAdaptersKt.bindRecyclerView(this.asteroidRecycler, viewModelAsteroidsGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.asteroids
        flag 1 (0x2L): viewModel.picOfDay
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}