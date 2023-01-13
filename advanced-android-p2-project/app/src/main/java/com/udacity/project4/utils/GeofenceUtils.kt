/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.treasureHunt

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.*
import com.udacity.project4.R
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import java.util.concurrent.TimeUnit

/**
 * Returns the error string for a geofencing error code.
 */
fun errorMessage(context: Context, errorCode: Int): String {
    val resources = context.resources
    return when (errorCode) {
        GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> resources.getString(
            R.string.geofence_not_available
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> resources.getString(
            R.string.geofence_too_many_geofences
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> resources.getString(
            R.string.geofence_too_many_pending_intents
        )
        else -> resources.getString(R.string.geofence_unknown_error)
    }
}

@SuppressLint("MissingPermission")
fun addReminderGeofence(
    reminderDataItem: ReminderDataItem,
    geofencePendingIntent: PendingIntent,
    context: Context
) {

    val geofencingClient = LocationServices.getGeofencingClient(context)

    val geofence = Geofence.Builder()
        .setRequestId(reminderDataItem.id)
        .setCircularRegion(
            reminderDataItem.latitude!!,
            reminderDataItem.longitude!!,
            GeofencingConstants.GEOFENCE_RADIUS_IN_METERS
        )
        .setExpirationDuration(GeofencingConstants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
        .build()

    val geofencingRequest = GeofencingRequest.Builder()
        .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
        .addGeofence(geofence)
        .build()


    geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent).run {
        addOnSuccessListener {
            // Geofences added.
            Toast.makeText(context, R.string.geofences_added, Toast.LENGTH_SHORT).show()
            Log.e("Add Geofence : ", geofence.requestId)
        }
        addOnFailureListener {
            Toast.makeText(
                context, R.string.geofences_not_added,
                Toast.LENGTH_SHORT
            ).show()
            it.message?.let {
                Log.w("Add Geofence Error : ", it)
            }
        }
    }

}

internal object GeofencingConstants {

    /**
     * Used to set an expiration time for a geofence. After this amount of time, Location services
     * stops tracking the geofence. For this sample, geofences expire after one hour.
     */
    val GEOFENCE_EXPIRATION_IN_MILLISECONDS: Long = TimeUnit.HOURS.toMillis(1)
    const val GEOFENCE_RADIUS_IN_METERS = 100f
    const val EXTRA_GEOFENCE_INDEX = "GEOFENCE_INDEX"
    const val ACTION_GEOFENCE_EVENT =
        "HuntMainActivity.treasureHunt.action.ACTION_GEOFENCE_EVENT"
}


