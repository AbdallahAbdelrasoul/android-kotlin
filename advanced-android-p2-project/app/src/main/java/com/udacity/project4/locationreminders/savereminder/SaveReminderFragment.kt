package com.udacity.project4.locationreminders.savereminder

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.treasureHunt.GeofencingConstants.ACTION_GEOFENCE_EVENT
import com.example.android.treasureHunt.addReminderGeofence
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.material.snackbar.Snackbar
import com.udacity.project4.R
import com.udacity.project4.base.BaseFragment
import com.udacity.project4.base.NavigationCommand
import com.udacity.project4.databinding.FragmentSaveReminderBinding
import com.udacity.project4.locationreminders.geofence.GeofenceBroadcastReceiver
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import com.udacity.project4.locationreminders.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject

// For API < 29
private const val REQUEST_LOCATION_PERMISSION = 0
private const val FINE_PERMISSION_INDEX = 0

// For API >= 29
private const val REQUEST_LOCATION_PERMISSION_Q_OR_LATER = 1
private const val BACKGROUND_PERMISSION_INDEX = 1

private const val REQUEST_TURN_DEVICE_LOCATION_ON = 9

// Check if the device running Q (API 29) or later
private val runningQOrLater = android.os.Build.VERSION.SDK_INT >=
        android.os.Build.VERSION_CODES.Q


class SaveReminderFragment : BaseFragment() {
    private val TAG = SaveReminderFragment::class.java.simpleName

    //Get the view model this time as a single to be shared with the another fragment
    override val _viewModel: SaveReminderViewModel by inject()
    private lateinit var binding: FragmentSaveReminderBinding
    private lateinit var reminderDataItem: ReminderDataItem

    // A PendingIntent for the Broadcast Receiver that handles geofence transitions.
    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(requireContext(), GeofenceBroadcastReceiver::class.java)
        intent.action = ACTION_GEOFENCE_EVENT
        // Use FLAG_UPDATE_CURRENT so that you get the same pending intent back when calling
        // addGeofences() and removeGeofences().
        PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_save_reminder, container, false)

        setDisplayHomeAsUpEnabled(true)

        binding.viewModel = _viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.selectLocation.setOnClickListener {
            // Navigate to another fragment to get the user location
            _viewModel.navigationCommand.value =
                NavigationCommand.To(SaveReminderFragmentDirections.actionSaveReminderFragmentToSelectLocationFragment())
        }

        binding.saveReminder.setOnClickListener {
            if(locationPermissionsApproved()){
                checkDeviceLocationSettingsAndAddGeofence(false)
            }else{
                requestLocationPermission()
            }
        }
    }

    private fun saveReminderAddGeofence() {
        reminderDataItem = ReminderDataItem(
            title = _viewModel.reminderTitle.value,
            description = _viewModel.reminderDescription.value,
            location = _viewModel.reminderSelectedLocationStr.value,
            latitude = _viewModel.latitude.value,
            longitude = _viewModel.longitude.value
        )
        // 1) save Reminder to the DB
        _viewModel.validateAndSaveReminder(reminderDataItem)

        // 2) add a geofencing request after confirmation
        _viewModel.reminderSaved.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it != -1L)
                    addReminderGeofence(reminderDataItem, geofencePendingIntent,requireContext())
            }
        })
    }

    // check if location permission is Approved
    @TargetApi(29)
    private fun locationPermissionsApproved(): Boolean {
        val foregroundLocationApproved =
            ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val backgroundLocationApproved =
            if (runningQOrLater) {
                ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

            } else {
                true
            }
        return foregroundLocationApproved && backgroundLocationApproved
    }

    @TargetApi(29)
    private fun requestLocationPermission() {
        if (locationPermissionsApproved()) return

        var permissionsArray = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        var requestCode = REQUEST_LOCATION_PERMISSION

        if (runningQOrLater) {
            permissionsArray += Manifest.permission.ACCESS_BACKGROUND_LOCATION
            requestCode = REQUEST_LOCATION_PERMISSION_Q_OR_LATER
        }

        ActivityCompat.requestPermissions(
            requireActivity(),
            permissionsArray,
            requestCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION_PERMISSION &&
            grantResults[FINE_PERMISSION_INDEX] == PackageManager.PERMISSION_GRANTED
            ||
            requestCode == REQUEST_LOCATION_PERMISSION_Q_OR_LATER &&
            grantResults[BACKGROUND_PERMISSION_INDEX] == PackageManager.PERMISSION_GRANTED
        ) {
            checkDeviceLocationSettingsAndAddGeofence(true)
        }
    }

    private fun checkDeviceLocationSettingsAndAddGeofence(resolve: Boolean = true) {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_LOW_POWER
        }
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val settingsClient = activity?.let { LocationServices.getSettingsClient(it) }
        val locationSettingsResponseTask =
            settingsClient?.checkLocationSettings(builder.build())

        locationSettingsResponseTask?.addOnFailureListener { exception ->
            if (exception is ResolvableApiException && resolve) {
                try {
                    exception.startResolutionForResult(
                        requireActivity(),
                        REQUEST_TURN_DEVICE_LOCATION_ON
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    Log.d(TAG, "Error getting location settings resolution: " + sendEx.message)
                }
            } else {
                Snackbar.make(
                    binding.saveReminderConstraint,
                    R.string.location_required_error,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(android.R.string.ok) {
                    checkDeviceLocationSettingsAndAddGeofence()
                }.show()
            }
        }
        locationSettingsResponseTask?.addOnCompleteListener {
            if (it.isSuccessful) {
                saveReminderAddGeofence()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Device Location is turn on
         if(requestCode == REQUEST_TURN_DEVICE_LOCATION_ON &&
                resultCode == Activity.RESULT_OK){
            saveReminderAddGeofence()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //make sure to clear the view model after destroy, as it's a single view model.
        _viewModel.onClear()
    }
}
