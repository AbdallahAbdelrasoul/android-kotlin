package com.udacity.asteroidradar.databinding;
import com.udacity.asteroidradar.R;
import com.udacity.asteroidradar.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentDetailBindingImpl extends FragmentDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.help_button, 7);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private FragmentDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[4]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.TextView) bindings[5]
            );
        this.absoluteMagnitude.setTag(null);
        this.activityMainImageOfTheDay.setTag(null);
        this.closeApproachDate.setTag(null);
        this.distanceFromEarth.setTag(null);
        this.estimatedDiameter.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.relativeVelocity.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.asteroid == variableId) {
            setAsteroid((com.udacity.asteroidradar.domain.Asteroid) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setAsteroid(@Nullable com.udacity.asteroidradar.domain.Asteroid Asteroid) {
        this.mAsteroid = Asteroid;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.asteroid);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        double androidxDatabindingViewDataBindingSafeUnboxAsteroidEstimatedDiameter = 0.0;
        java.lang.Boolean asteroidPotentiallyHazardous = null;
        double androidxDatabindingViewDataBindingSafeUnboxAsteroidDistanceFromEarth = 0.0;
        java.lang.Double asteroidEstimatedDiameter = null;
        double androidxDatabindingViewDataBindingSafeUnboxAsteroidAbsoluteMagnitude = 0.0;
        com.udacity.asteroidradar.domain.Asteroid asteroid = mAsteroid;
        java.lang.Double asteroidAbsoluteMagnitude = null;
        java.lang.String asteroidCloseApproachDate = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxAsteroidPotentiallyHazardous = false;
        java.lang.Double asteroidRelativeVelocity = null;
        java.lang.Double asteroidDistanceFromEarth = null;
        double androidxDatabindingViewDataBindingSafeUnboxAsteroidRelativeVelocity = 0.0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (asteroid != null) {
                    // read asteroid.potentiallyHazardous
                    asteroidPotentiallyHazardous = asteroid.isPotentiallyHazardous();
                    // read asteroid.estimatedDiameter
                    asteroidEstimatedDiameter = asteroid.getEstimatedDiameter();
                    // read asteroid.absoluteMagnitude
                    asteroidAbsoluteMagnitude = asteroid.getAbsoluteMagnitude();
                    // read asteroid.closeApproachDate
                    asteroidCloseApproachDate = asteroid.getCloseApproachDate();
                    // read asteroid.relativeVelocity
                    asteroidRelativeVelocity = asteroid.getRelativeVelocity();
                    // read asteroid.distanceFromEarth
                    asteroidDistanceFromEarth = asteroid.getDistanceFromEarth();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(asteroid.potentiallyHazardous)
                androidxDatabindingViewDataBindingSafeUnboxAsteroidPotentiallyHazardous = androidx.databinding.ViewDataBinding.safeUnbox(asteroidPotentiallyHazardous);
                // read androidx.databinding.ViewDataBinding.safeUnbox(asteroid.estimatedDiameter)
                androidxDatabindingViewDataBindingSafeUnboxAsteroidEstimatedDiameter = androidx.databinding.ViewDataBinding.safeUnbox(asteroidEstimatedDiameter);
                // read androidx.databinding.ViewDataBinding.safeUnbox(asteroid.absoluteMagnitude)
                androidxDatabindingViewDataBindingSafeUnboxAsteroidAbsoluteMagnitude = androidx.databinding.ViewDataBinding.safeUnbox(asteroidAbsoluteMagnitude);
                // read androidx.databinding.ViewDataBinding.safeUnbox(asteroid.relativeVelocity)
                androidxDatabindingViewDataBindingSafeUnboxAsteroidRelativeVelocity = androidx.databinding.ViewDataBinding.safeUnbox(asteroidRelativeVelocity);
                // read androidx.databinding.ViewDataBinding.safeUnbox(asteroid.distanceFromEarth)
                androidxDatabindingViewDataBindingSafeUnboxAsteroidDistanceFromEarth = androidx.databinding.ViewDataBinding.safeUnbox(asteroidDistanceFromEarth);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.udacity.asteroidradar.utils.BindingAdaptersKt.bindTextViewToAstronomicalUnit(this.absoluteMagnitude, androidxDatabindingViewDataBindingSafeUnboxAsteroidAbsoluteMagnitude);
            com.udacity.asteroidradar.utils.BindingAdaptersKt.bindDetailsStatusImage(this.activityMainImageOfTheDay, androidxDatabindingViewDataBindingSafeUnboxAsteroidPotentiallyHazardous);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.closeApproachDate, asteroidCloseApproachDate);
            com.udacity.asteroidradar.utils.BindingAdaptersKt.bindTextViewToAstronomicalUnit(this.distanceFromEarth, androidxDatabindingViewDataBindingSafeUnboxAsteroidDistanceFromEarth);
            com.udacity.asteroidradar.utils.BindingAdaptersKt.bindTextViewToKmUnit(this.estimatedDiameter, androidxDatabindingViewDataBindingSafeUnboxAsteroidEstimatedDiameter);
            com.udacity.asteroidradar.utils.BindingAdaptersKt.bindTextViewToDisplayVelocity(this.relativeVelocity, androidxDatabindingViewDataBindingSafeUnboxAsteroidRelativeVelocity);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): asteroid
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}