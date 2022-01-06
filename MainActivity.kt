package com.salam94.locationmap

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat   .app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.RuntimeExecutionException
import com.google.android.gms.tasks.Task
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback, ClusterManager.OnClusterClickListener<MyClusterItem>, ClusterManager.OnClusterItemClickListener<MyClusterItem> {
    lateinit var currentLocation: Location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val REQUEST_LOCATION = 1
    private lateinit var mGoogleMap: GoogleMap
    private val tokyoCoordinate = LatLng(35.702, 139.774)

    val resto1 = LatLng(35.778, 139.718)
    val resto2 = LatLng(35.696, 139.811)
    val resto3 = LatLng(35.643500, 139.711754)
    val resto4 = LatLng(35.704809, 139.781079)
    val resto5 = LatLng(35.726026, 139.778523)
    val resto6 = LatLng(35.711834, 139.797562)
    val resto7 = LatLng(35.688391, 139.735264)
    val resto8 = LatLng(35.658897, 139.705900)
    val resto9 = LatLng(35.737973, 139.710307)
    val resto10 = LatLng(35.715036, 139.795270)
    val resto11 = LatLng(35.690928, 139.699580)
    val resto12 = LatLng(35.709580, 139.788479)
    val resto13 = LatLng(35.670822, 139.764790)
    val resto14 = LatLng(35.692852, 139.831395)
    val resto15 = LatLng(35.666080, 139.730114)

    val mall1 = LatLng(35.671, 139.765)
    val mall2 = LatLng(35.687, 139.702)
    val mall3 = LatLng(35.729834, 139.710426)
    val mall4 = LatLng(35.692204, 139.704440)
    val mall5 = LatLng(35.681726, 139.768907)
    val mall6 = LatLng(35.690224, 139.699068)
    val mall7 = LatLng(35.680651, 139.765140)
    val mall8 = LatLng(35.659330, 139.703784)
    val mall9 = LatLng(35.710600, 139.812563)
    val mall10 = LatLng(35.729196, 139.719588)

    val tourism1 = LatLng(35.715, 139.796)
    val tourism2 = LatLng(35.709, 139.774)
    val tourism3 = LatLng(35.659665, 139.706607)
    val tourism4 = LatLng(35.710358, 139.810764)
    val tourism5 = LatLng(35.669616, 139.767725)
    val tourism6 = LatLng(35.618766, 139.777963)
    val tourism7 = LatLng(35.696438, 139.570463)
    val tourism8 = LatLng(35.660456, 139.729980)
    val tourism9 = LatLng(35.676641, 139.699315)
    val tourism10 = LatLng(35.665189, 139.769827)

    val mosque1 = LatLng(35.722, 139.801)
    val mosque2 = LatLng(36.305, 139.788)
    val mosque3 = LatLng(33.620931, 130.427326)
    val mosque4 = LatLng(35.394581, 136.838948)
    val mosque5 = LatLng(35.681645, 139.918486)
    val mosque6 = LatLng(35.186441, 136.773083)
    val mosque7 = LatLng(35.564606, 139.717171)
    val mosque8 = LatLng(34.697092, 135.187592)
    val mosque9 = LatLng(35.177662, 136.871145)
    val mosque10 = LatLng(35.668544, 139.676662)
    val mosque11 = LatLng(35.544390, 139.601440)
    val mosque12 = LatLng(36.242258, 139.533693)
    val mosque13 = LatLng(36.333067, 140.471109)


    private var mSpotMarkerList = ArrayList<Marker>()

    // Declare a variable for the cluster manager.
    private var mClusterManager: ClusterManager<MyClusterItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            } else {
                Toast.makeText(
                    this,
                    "Location permission is required to locate you!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun restaurantLocation(){
        mGoogleMap.clear()
        mGoogleMap.addMarker(MarkerOptions().position(resto1).title("Restaurant Sarap Bussan"))
        mGoogleMap.addMarker(MarkerOptions().position(resto2).title("Restaurant Tokyo Chinese"))
        mGoogleMap.addMarker(MarkerOptions().position(resto3).title("Menya Honolu Ebisu"))
        mGoogleMap.addMarker(MarkerOptions().position(resto4).title("Halal Wagyu Yakiniku PANGA"))
        mGoogleMap.addMarker(MarkerOptions().position(resto5).title("Halal Sakura"))
        mGoogleMap.addMarker(MarkerOptions().position(resto6).title("The Kebab Factory Halal Restaurant"))
        mGoogleMap.addMarker(MarkerOptions().position(resto7).title("Tokyo Halal Deli & Cafe"))
        mGoogleMap.addMarker(MarkerOptions().position(resto8).title("Gyumon"))
        mGoogleMap.addMarker(MarkerOptions().position(resto9).title("Marhaba Halal Restaurant"))
        mGoogleMap.addMarker(MarkerOptions().position(resto10).title("Halal Ramen"))
        mGoogleMap.addMarker(MarkerOptions().position(resto11).title("Turkish Restaurant Cankaya"))
        mGoogleMap.addMarker(MarkerOptions().position(resto12).title("Ayam-Ya Shin-Okachimachi Tokyo"))
        mGoogleMap.addMarker(MarkerOptions().position(resto13).title("Annam Indian Restaurant (Veg & Non Veg)"))
        mGoogleMap.addMarker(MarkerOptions().position(resto14).title("Habibi Halal Restaurant"))
        mGoogleMap.addMarker(MarkerOptions().position(resto15).title("Asian Kebab"))
    }

    private fun mallLocation(){
        mGoogleMap.clear()
        mGoogleMap.addMarker(MarkerOptions().position(mall1).title("Mall Ginza Mitsukoshi"))
        mGoogleMap.addMarker(MarkerOptions().position(mall2).title("Mall Shinjuku Takashimaya"))
        mGoogleMap.addMarker(MarkerOptions().position(mall3).title("Tobu Hyakkaten Ikebukuro"))
        mGoogleMap.addMarker(MarkerOptions().position(mall4).title("Isetan Shinjuku"))
        mGoogleMap.addMarker(MarkerOptions().position(mall5).title("Daimaru Tokyo"))
        mGoogleMap.addMarker(MarkerOptions().position(mall6).title("Keiyo Hyakkaten Shinjuku"))
        mGoogleMap.addMarker(MarkerOptions().position(mall7).title("KITTE Marunouchi"))
        mGoogleMap.addMarker(MarkerOptions().position(mall8).title("Shibuya Hikarie"))
        mGoogleMap.addMarker(MarkerOptions().position(mall9).title("Tokyo Solamachi"))
        mGoogleMap.addMarker(MarkerOptions().position(mall10).title("Sunshine City"))

    }
    private fun tourismLocation(){
        mGoogleMap.clear()
        mGoogleMap.addMarker(MarkerOptions().position(tourism1).title("Tourism Sensjo-ji Asakusa"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism2).title("Tourism Ameya Yokocha"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism3).title("Scramble Crossing Shibuya"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism4).title("Tokyo Skytree Oshiage"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism5).title("Theater Kabukiza Ginza"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism6).title("Oedo Onsen Monogatari Odaiba"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism7).title("Museum Ghibli Mitaka no Mori"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism8).title("Roponggi Hills"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism9).title("Kuil Meiji Jingu Harajuku"))
        mGoogleMap.addMarker(MarkerOptions().position(tourism10).title("Tsukiji Outter Market"))
    }
    private fun mosqueLocation(){
        mGoogleMap.clear()
        mGoogleMap.addMarker(MarkerOptions().position(mosque1).title("Asakusa Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque2).title("Babul Islam Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque3).title("Fukuoka Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque4).title("Gifu Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque5).title("Hira Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque6).title("Baitul Ahad/The Japan Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque7).title("Kamata Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque8).title("Kobe Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque9).title("Nagoya Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque10).title("Tokyo Mosque/ Tokyo Camii"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque10).title("Yokohama Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque10).title("Quba Mosque"))
        mGoogleMap.addMarker(MarkerOptions().position(mosque10).title("Abu Bakar Siddique Mosque"))
    }
    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )
        } else {
            val task: Task<Location> = fusedLocationProviderClient.lastLocation

            task.addOnSuccessListener {

                if (it != null) {
                    currentLocation = it
                    animateZoomInCamera(
                        LatLng(
                            currentLocation.latitude,
                            currentLocation.longitude
                        )
                    )
                } else {

                    val REQUEST_CHECK_STATE = 12300 // any suitable ID
                    val builder = LocationSettingsRequest.Builder()
                        .addLocationRequest(reqSetting)

                    val client = LocationServices.getSettingsClient(this)
                    client.checkLocationSettings(builder.build()).addOnCompleteListener { task ->
                        try {
                            val state: LocationSettingsStates = task.result!!.locationSettingsStates
                            Log.d("salam", task.result!!.toString())
                            Log.e(
                                "LOG", "LocationSettings: \n" +
                                        " GPS present: ${state.isGpsPresent} \n" +
                                        " GPS usable: ${state.isGpsUsable} \n" +
                                        " Location present: " +
                                        "${state.isLocationPresent} \n" +
                                        " Location usable: " +
                                        "${state.isLocationUsable} \n" +
                                        " Network Location present: " +
                                        "${state.isNetworkLocationPresent} \n" +
                                        " Network Location usable: " +
                                        "${state.isNetworkLocationUsable} \n"
                            )
                        } catch (e: RuntimeExecutionException) {
                            Log.d("salam", "hei")
                            if (e.cause is ResolvableApiException)
                                (e.cause as ResolvableApiException).startResolutionForResult(
                                    this,
                                    REQUEST_CHECK_STATE
                                )
                        }
                    }

                    val locationUpdates = object : LocationCallback() {
                        override fun onLocationResult(lr: LocationResult) {
                            Log.e("salam", lr.toString())
                            Log.e("salam", "Newest Location: " + lr.locations.last())
                            // do something with the new location...
                            animateZoomInCamera(
                                LatLng(
                                    lr.locations.last().latitude,
                                    lr.locations.last().longitude
                                )
                            )
                        }
                    }

                    fusedLocationProviderClient.requestLocationUpdates(
                        reqSetting,
                        locationUpdates,
                        null /* Looper */
                    )

                    fusedLocationProviderClient.removeLocationUpdates(locationUpdates)
                }
            }

        }
    }


    fun animateZoomInCamera(latLng: LatLng) {
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(tokyoCoordinate))
        mGoogleMap.addMarker(MarkerOptions().position(tokyoCoordinate).title("Lokasi ibu Jannah"))
    }

    private val reqSetting = LocationRequest.create().apply {
        fastestInterval = 10000
        interval = 10000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        smallestDisplacement = 1.0f
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap!!
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(tokyoCoordinate))
        animateZoomInCamera(tokyoCoordinate)
        mGoogleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mGoogleMap.uiSettings.isZoomControlsEnabled = true

        setUpClusterer()

        btnLocate.setOnClickListener {
            fetchLocation()
        }
        btnRestaurant.setOnClickListener {
            restaurantLocation()
        }
        btnMall.setOnClickListener {
            mallLocation()
        }
        btnTourism.setOnClickListener {
            tourismLocation()
        }
        btnMosque.setOnClickListener {
            mosqueLocation()
        }
    }

    private fun setUpClusterer() {
        // Position the map.
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng (51.503186, -0.126446), 10f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = ClusterManager(this, mGoogleMap)
        mClusterManager!!.setOnClusterClickListener(this)
        mClusterManager!!.setOnClusterItemClickListener(this)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mGoogleMap.setOnCameraIdleListener(mClusterManager)
        mGoogleMap.setOnMarkerClickListener(mClusterManager)

        // Add cluster items (markers) to the cluster manager.
        addItems()
    }

    private fun addItems() {

        // Set some lat/lng coordinates to start with.
        var lat = 51.5145160
        var lng = -0.1270060

        val sydney = LatLng(-34.0, 151.0)



        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Add 30 cluster items in close proximity, for purposes of this example.
        for (i in 0..30) {
            val offset = i / 60.0
            lat += offset
            lng += offset
            // Set the title and snippet strings.
            val title = "This is the title"
            val snippet = "and this is the snippet."

            val offsetItem = MyClusterItem(lat, lng, title, snippet)
            mClusterManager!!.addItem(offsetItem)
        }
    }

    override fun onClusterClick(cluster: Cluster<MyClusterItem>?): Boolean {
        animateZoomInCamera(cluster!!.position)
        return true
    }

    override fun onClusterItemClick(item: MyClusterItem?): Boolean {
        animateZoomInCamera(item!!.position)
        return true
    }
}