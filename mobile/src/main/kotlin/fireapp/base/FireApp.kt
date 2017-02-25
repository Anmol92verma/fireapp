package fireapp.base

import android.app.Application
import com.firely.fireapp.BuildConfig
import com.firely.fireapp.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import fireapp.consts.Constants


/**
 * Created by anmolverma on 2/25/17.
 */

class FireApp : Application(), OnCompleteListener<Void> {


    private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null
    private var popularElement: String? = null
    private var mercuryImage: String? = null

    override fun onCreate() {
        super.onCreate()

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()
        mFirebaseRemoteConfig?.setConfigSettings(configSettings)
        mFirebaseRemoteConfig?.setDefaults(R.xml.remote_config_defaults)

        fetchFireAppInfo()
    }


    private fun fetchFireAppInfo() {
        popularElement = mFirebaseRemoteConfig?.getString(Constants.POPULAR_ELEMENT)
        mercuryImage = mFirebaseRemoteConfig?.getString(Constants.MERCURY_URL)

        var cacheExpiration: Long = 0

        mFirebaseRemoteConfig?.fetch(cacheExpiration)?.addOnCompleteListener(this)

    }

    override fun onComplete(p0: Task<Void>) {
        mFirebaseRemoteConfig?.activateFetched()
        popularElement = mFirebaseRemoteConfig?.getString(Constants.POPULAR_ELEMENT)
        mercuryImage = mFirebaseRemoteConfig?.getString(Constants.MERCURY_URL)
    }

     fun getMercuryImageUrl(): String {
        return mercuryImage!!
    }
}

