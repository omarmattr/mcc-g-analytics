package com.omarmattr.mcc_g_analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.omarmattr.mcc_g_analytics.databinding.FragmentHomeBinding
import com.omarmattr.mcc_g_analytics.model.Session
import com.omarmattr.mcc_g_analytics.model.User
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    companion object {
        val user = User(name = "omar", id = "2896865")
    }


    private val viewModel: ViewModel by activityViewModels()
    private lateinit var mBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }
    private var timer = 0
    var run = true
    override fun onResume() {
        super.onResume()
        Thread{

            while (run) {
                Thread.sleep(1000)
                timer++
            }
        }.start()

    }

    override fun onPause() {
        super.onPause()
        run = false
        viewModel.addSessionUser(Session(userId = user.id, timer.toString(), "HomeFragment"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btnFood.setOnClickListener {
            viewModel.onClickEvent(
                uName = user.name,
                uId = user.id,
                name = "Food",
                event = "open fragment"
            )
            viewModel.getAllFood()
            val bundle = Bundle()
            bundle.putString("type", "Food")
            val fragment = ProductFragment()
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.navHostFragment, fragment
            ).addToBackStack(null).commit()
        }

        mBinding.btnHp.setOnClickListener {
            viewModel.onClickEvent(
                uName = user.name,
                uId = user.id,
                name = "HP",
                event = "open fragment"
            )
            viewModel.getAllHP()
            val bundle = Bundle()
            bundle.putString("type", "HP")
            val fragment = ProductFragment()
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.navHostFragment, fragment
            ).addToBackStack(null).commit()
        }

        mBinding.btnPhones.setOnClickListener {
            viewModel.onClickEvent(
                uName = user.name,
                uId = user.id,
                name = "Phones",
                event = "open fragment"
            )
            viewModel.getAllPhones()
            val bundle = Bundle()
            bundle.putString("type", "Phones")
            val fragment = ProductFragment()
            fragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.navHostFragment, fragment
            ).addToBackStack(null).commit()
        }


    }


}