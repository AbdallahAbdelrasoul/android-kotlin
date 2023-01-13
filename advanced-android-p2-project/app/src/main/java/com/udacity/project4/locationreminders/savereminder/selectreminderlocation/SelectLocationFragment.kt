package com.udacity.project4.locationreminders.savereminder.selectreminderlocation


import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.snackbar.Snackbar
import com.udacity.project4.BuildConfig
import com.udacity.project4.R
import com.udacity.project4.base.BaseFragment
import com.udacity.project4.base.NavigationCommand
import com.udacity.project4.databinding.FragmentSelectLocationBinding
import com.udacity.project4.locationreminders.savereminder.SaveReminderViewModel
import com.udacity.project4.utils.setDisplayHomeAsUpEnabled
import kotlinx.android.synthetic.main.fragment_save_reminder.*
import org.koin.android.ext.android.inject
import java.util.*
// For API < 29
private const val REQUEST_LOCATION_PERMISSION = 0
private const val FINE_PERMISSION_INDEX = 0
// For API >= 29
private const val REQUEST_LOCATION_PERMISSION_Q_OR_LATER = 1
private const val BACKGROUND_PERMISSION_INDEX = 1

private const val REQUEST_TURN_DEVICE_LOCATION_ON = 9

class SelectLocationFragment : BaseFragment(), OnMapReadyCallback {

    // google map instance
    private lateinit var map: GoogleMap

    private lateinit var selectedLocationLatlng: LatLng
    private lateinit var selectedLocationName: String

    // Setting up a Fused Location Client
//    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Camera Zoom
    private val zoomLevel = 15f

    // Tag for log
    private val TAG = SelectLocationFragment::class.java.simpleName

    // Check if the device running Q (API 29) or later
    private val runningQOrLater = android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.Q

    //Use Koin to get the view model of the SaveReminder
    override val _viewModel: SaveReminderViewModel by inject()
    private lateinit var binding: FragmentSelectLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentSelectLocationBinding.inflate(inflater, container, false)

        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        setDisplayHomeAsUpEnabled(true)

        // add the map setup implementation
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        binding.saveBtn.setOnClickListener {
            onLocationSelected()
        }

        return binding.root
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setMapStyle(map)
//        getUserLocation()
        setPoiClick(map)
        setMapLongClick(map)
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            poi.latLng?.let {
                addMarkerAndCameraZoom(it, poi.name)
            }
        }
    }

    private fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            latLng?.let {
                val selectedLocationName = String.format(
                    Locale.getDefault(),
                    "Lat: %1$.5f, Long: %2$.5f",
                    latLng.latitude,
                    latLng.longitude
                )
                addMarkerAndCameraZoom(it, selectedLocationName)
            }
        }
    }

    private fun addMarkerAndCameraZoom(
        latlng: LatLng,
        name: String
    ) {
        try {
            map.clear()
            val poiMarker = map.addMarker(
                MarkerOptions().position(latlng).title(name)
            )
            poiMarker.showInfoWindow()

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoomLevel))
            selectedLocationLatlng = latlng
            selectedLocationName = name
            binding.saveBtn.visibility = View.VISIBLE

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    // Allows map styling and theming to be customized.
    private fun setMapStyle(map: GoogleMap) {
        try {
            // Customize the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(), R.raw.map_style
                )
            )

            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }
    }

    // get User Location
    private fun getUserLocation() {
        try{
            if (locationPermissionsApproved())
                checkDeviceLocationSettingsAndStartMyLocation(false)
            else requestLocationPermission()
        }catch(e:Exception){
            Toast.makeText(requireContext(),e.message,Toast.LENGTH_SHORT).show()
        }
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
        // Check if location permissions are granted and if so enable the
        // location data layer.

        // in case granted
        if (requestCode == REQUEST_LOCATION_PERMISSION &&
            grantResults[FINE_PERMISSION_INDEX] == PackageManager.PERMISSION_GRANTED
            ||
            requestCode == REQUEST_LOCATION_PERMISSION_Q_OR_LATER &&
            grantResults[BACKGROUND_PERMISSION_INDEX] == PackageManager.PERMISSION_GRANTED
            ){
            checkDeviceLocationSettingsAndStartMyLocation()
        }
        // in case deny
        else{
            Snackbar.make(
                binding.mapConstraintLayout,
                R.string.permission_denied_explanation,
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction(R.string.settings) {
                    startActivity(Intent().apply {
                        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }.show()
        }

    }

    // request device location
    @SuppressLint("MissingPermission")
    private fun checkDeviceLocationSettingsAndStartMyLocation(resolve: Boolean = true) {
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
                    binding.mapConstraintLayout,
                    R.string.location_required_error,
                    Snackbar.LENGTH_INDEFINITE
                ).setAction(android.R.string.ok) {
                    checkDeviceLocationSettingsAndStartMyLocation()
                }.show()
            }
        }
        locationSettingsResponseTask?.addOnCompleteListener {
            if (it.isSuccessful) {
                map.isMyLocationEnabled = true
            }
        }
    }


    override fun onStart() {
        super.onStart()
        getUserLocation()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TURN_DEVICE_LOCATION_ON)
            checkDeviceLocationSettingsAndStartMyLocation()
    }


    private fun onLocationSelected() {
        _viewModel.apply {
            latitude.value = selectedLocationLatlng.latitude
            longitude.value = selectedLocationLatlng.longitude
            reminderSelectedLocationStr.value = selectedLocationName
        }

        _viewModel.navigationCommand.value = NavigationCommand.Back
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.map_options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        //Change the map type based on the user's selection.
        R.id.normal_map -> {
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map -> {
            map.mapType = GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map -> {
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map -> {
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}

