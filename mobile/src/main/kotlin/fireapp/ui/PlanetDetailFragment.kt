package fireapp.ui

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.firely.fireapp.R
import com.squareup.picasso.Picasso
import fireapp.base.FireApp
import fireapp.consts.Planet

/**
 * A fragment representing a single Planet detail screen.
 * This fragment is either contained in a [PlanetListActivity]
 * in two-pane mode (on tablets) or a [PlanetDetailActivity]
 * on handsets.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class PlanetDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var mItem: Planet? = null

    private var mercuryUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Planet.values().get(Integer.parseInt(arguments.getString(ARG_ITEM_ID)))

            val activity = this.activity
            val appBarLayout = activity.findViewById(R.id.toolbar_layout) as? CollapsingToolbarLayout
            if (appBarLayout != null) {
                appBarLayout.title = mItem!!.name
            } else {
                val toolbar = activity.findViewById(R.id.toolbar) as Toolbar
                toolbar.title = mItem!!.name
            }

            if (mItem!!.ordinal == 0) {
                // load image
                var fireApp = activity.application as? FireApp
                mercuryUrl = fireApp?.getMercuryImageUrl()

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.planet_detail, container, false)

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            (rootView.findViewById(R.id.planet_detail) as TextView).text = mItem!!.planetInfo(mItem!!)
            var imgView = rootView.findViewById(R.id.imgView) as ImageView
            Picasso.with(activity).load(mercuryUrl).into(imgView)
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }
}
