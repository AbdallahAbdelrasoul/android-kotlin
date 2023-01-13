package com.udacity.asteroidradar.screens.main;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.udacity.asteroidradar.R;
import com.udacity.asteroidradar.domain.Asteroid;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class MainFragmentDirections {
  private MainFragmentDirections() {
  }

  @NonNull
  public static ActionShowDetail actionShowDetail(@NonNull Asteroid selectedAsteroid) {
    return new ActionShowDetail(selectedAsteroid);
  }

  public static class ActionShowDetail implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionShowDetail(@NonNull Asteroid selectedAsteroid) {
      if (selectedAsteroid == null) {
        throw new IllegalArgumentException("Argument \"selectedAsteroid\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("selectedAsteroid", selectedAsteroid);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionShowDetail setSelectedAsteroid(@NonNull Asteroid selectedAsteroid) {
      if (selectedAsteroid == null) {
        throw new IllegalArgumentException("Argument \"selectedAsteroid\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("selectedAsteroid", selectedAsteroid);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_showDetail;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Asteroid getSelectedAsteroid() {
      return (Asteroid) arguments.get("selectedAsteroid");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionShowDetail that = (ActionShowDetail) object;
      if (arguments.containsKey("selectedAsteroid") != that.arguments.containsKey("selectedAsteroid")) {
        return false;
      }
      if (getSelectedAsteroid() != null ? !getSelectedAsteroid().equals(that.getSelectedAsteroid()) : that.getSelectedAsteroid() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getSelectedAsteroid() != null ? getSelectedAsteroid().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionShowDetail(actionId=" + getActionId() + "){"
          + "selectedAsteroid=" + getSelectedAsteroid()
          + "}";
    }
  }
}
