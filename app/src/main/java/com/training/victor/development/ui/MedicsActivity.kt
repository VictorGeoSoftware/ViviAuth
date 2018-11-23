package com.training.victor.development.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.training.victor.development.MainApplication
import com.training.victor.development.R
import com.training.victor.development.data.Constants.Companion.LOCATION_PERMISION_REQUEST
import com.training.victor.development.data.models.MedicItem
import com.training.victor.development.presenter.MedicsPresenter
import com.training.victor.development.ui.adapters.MedicsAdapter
import com.training.victor.development.ui.decorators.SpaceDecorator
import com.training.victor.development.utils.getDpFromValue
import com.training.victor.development.utils.hideKeyboard
import com.training.victor.development.utils.showRequestErrorMessage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_medics_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MedicsActivity: AppCompatActivity(), MedicsPresenter.MedicsView {
    @Inject lateinit var medicsPresenter: MedicsPresenter
    private val mMedicList = ArrayList<MedicItem>()
    private lateinit var mMedicsAdapter: MedicsAdapter
    private val disposable: CompositeDisposable = CompositeDisposable()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // Default values in case of not accepting Location Permission
    var defaultLat = 52.534709
    var defaultLong = 13.3976972


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medics_search)
        (application as MainApplication).createPresenterComponent().inject(this)

        medicsPresenter.view = this

        lstDoctors.layoutManager = LinearLayoutManager(this)
        lstDoctors.addItemDecoration(SpaceDecorator(getDpFromValue(10)))
        mMedicsAdapter = MedicsAdapter(mMedicList)
        lstDoctors.adapter = mMedicsAdapter


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISION_REQUEST)
            } else {
                getCurrentLocation()
            }
        } else {
            getCurrentLocation()
        }


        disposable.add(createTextChangeObservable(edtSearchValue)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                medicsPresenter.getMedicList(it, defaultLat, defaultLong)
                edtSearchValue.hideKeyboard()
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        (application as MainApplication).releasePresenterComponent()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode ==  LOCATION_PERMISION_REQUEST
            && !grantResults.isEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
            getCurrentLocation()
        }
    }


    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- PRESENTER VIEW ---------------------------------------------
    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun onMedicListError(message: String) {
        edtSearchValue.text.clear()
        mMedicList.clear()
        mMedicsAdapter.notifyDataSetChanged()
        showRequestErrorMessage(message)
    }

    override fun onMedicListReceived(medicList: List<MedicItem>) {
        edtSearchValue.text.clear()
        mMedicList.clear()
        mMedicList.addAll(medicList)
        mMedicsAdapter.notifyDataSetChanged()
    }

    override fun onUserNotFound() {
        showRequestErrorMessage(getString(R.string.user_not_available))
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onAccessTokenExpired() {
        showRequestErrorMessage(getString(R.string.access_token_expired))
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- METHODS ----------------------------------------------------
    private fun createTextChangeObservable(editText: EditText): Observable<String> {
        return Observable.create<String>{ emitter ->
            val watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = Unit

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.toString()?.let { emitter.onNext(it) }
                }
            }

            editText.addTextChangedListener(watcher)

            emitter.setCancellable { editText.removeTextChangedListener(watcher) }
        }.filter { it.length > 2 }.debounce(1, TimeUnit.SECONDS)
    }

    private fun getCurrentLocation() {
        showProgressBar(true)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                defaultLat = it.latitude
                defaultLong = it.longitude
                showProgressBar(false)
            }
        }
    }
}