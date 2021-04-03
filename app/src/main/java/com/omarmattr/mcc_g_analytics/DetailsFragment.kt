package com.omarmattr.mcc_g_analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.omarmattr.mcc_g_analytics.databinding.FragmentDetailsBinding
import com.omarmattr.mcc_g_analytics.model.Product
import com.omarmattr.mcc_g_analytics.model.Session

class DetailsFragment : Fragment() {

    lateinit var mBinding: FragmentDetailsBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    private var timer = 0
    var run = true
    override fun onResume() {
        super.onResume()
        Thread {

            while (run) {
                Thread.sleep(1000)
                timer++
            }
        }.start()

    }

    override fun onPause() {
        super.onPause()
        run = false
        viewModel.addSessionUser(
            Session(
                userId = HomeFragment.user.id,
                timer.toString(),
                "DetailsFragment"
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().apply {
            val product: Product? = this.getParcelable("product")
            mBinding.product = product

        }
    }

}