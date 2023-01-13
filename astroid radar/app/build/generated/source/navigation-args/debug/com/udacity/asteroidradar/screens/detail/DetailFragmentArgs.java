package com.udacity.asteroidradar.screens.detail;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.udacity.asteroidradar.domain.Asteroid;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DetailFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DetailFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private DetailFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static DetailFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DetailFragmentArgs __result = new DetailFragmentArgs();
    bundle.setClassLoader(DetailFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("selectedAsteroid")) {
      Asteroid selectedAsteroid;
      if (Parcelable.class.isAssignableFrom(Asteroid.class) || Serializable.class.isAssignableFrom(Asteroid.class)) {
        selectedAsteroid = (Asteroid) bundle.get("selectedAsteroid");
      } else {
        throw new UnsupportedOperationException(Asteroid.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (selectedAsteroid == null) {
        throw new IllegalArgumentException("Argument \"selectedAsteroid\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("selectedAsteroid", selectedAsteroid);
    } else {
      throw new IllegalArgumentException("Required argument \"selectedAsteroid\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DetailFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    DetailFragmentArgs __result = new DetailFragmentArgs();
    if (savedStateHandle.contains("selectedAsteroid")) {
      Asteroid selectedAsteroid;
      selectedAsteroid = savedStateHandle.get("selectedAsteroid");
      if (selectedAsteroid == null) {
        throw new IllegalArgumentException("Argument \"selectedAsteroid\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("selectedAsteroid", selectedAsteroid);
    } else {
      throw new IllegalArgumentException("Required argument \"selectedAsteroid\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Asteroid getSelectedAsteroid() {
    return (Asteroid) arguments.get("selectedAsteroid");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("selectedAsteroid")) {
      Asteroid selectedAsteroid = (Asteroid) arguments.get("selectedAsteroid");
      if (Parcelable.class.isAssignableFrom(Asteroid.class) || selectedAsteroid == null) {
        __result.putParcelable("selectedAsteroid", Parcelable.class.cast(selectedAsteroid));
      } else if (Serializable.class.isAssignableFrom(Asteroid.class)) {
        __result.putSerializable("selectedAsteroid", Serializable.class.cast(selectedAsteroid));
      } else {
        throw new UnsupportedOperationException(Asteroid.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("selectedAsteroid")) {
      Asteroid selectedAsteroid = (Asteroid) arguments.get("selectedAsteroid");
      if (Parcelable.class.isAssignableFrom(Asteroid.class) || selectedAsteroid == null) {
        __result.set("selectedAsteroid", Parcelable.class.cast(selectedAsteroid));
      } else if (Serializable.class.isAssignableFrom(Asteroid.class)) {
        __result.set("selectedAsteroid", Serializable.class.cast(selectedAsteroid));
      } else {
        throw new UnsupportedOperationException(Asteroid.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    DetailFragmentArgs that = (DetailFragmentArgs) object;
    if (arguments.containsKey("selectedAsteroid") != that.arguments.containsKey("selectedAsteroid")) {
      return false;
    }
    if (getSelectedAsteroid() != null ? !getSelectedAsteroid().equals(that.getSelectedAsteroid()) : that.getSelectedAsteroid() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getSelectedAsteroid() != null ? getSelectedAsteroid().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DetailFragmentArgs{"
        + "selectedAsteroid=" + getSelectedAsteroid()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull DetailFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull Asteroid selectedAsteroid) {
      if (selectedAsteroid == null) {
        throw new IllegalArgumentException("Argument \"selectedAsteroid\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("selectedAsteroid", selectedAsteroid);
    }

    @NonNull
    public DetailFragmentArgs build() {
      DetailFragmentArgs result = new DetailFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setSelectedAsteroid(@NonNull Asteroid selectedAsteroid) {
      if (selectedAsteroid == null) {
        throw new IllegalArgumentException("Argument \"selectedAsteroid\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("selectedAsteroid", selectedAsteroid);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public Asteroid getSelectedAsteroid() {
      return (Asteroid) arguments.get("selectedAsteroid");
    }
  }
}
