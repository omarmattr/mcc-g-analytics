package com.omarmattr.mcc_g_analytics

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.omarmattr.mcc_g_analytics.HomeFragment.Companion.user
import com.omarmattr.mcc_g_analytics.adapter.ProductAdapter
import com.omarmattr.mcc_g_analytics.databinding.FragmentCategoryBinding
import com.omarmattr.mcc_g_analytics.model.Product
import com.omarmattr.mcc_g_analytics.model.Session
import com.omarmattr.mcc_g_analytics.uitls.MyResult


class ProductFragment : Fragment(), ProductAdapter.OnClick {

    private val mAdapter = ProductAdapter(arrayListOf(), this)
    private val viewModel: ViewModel by activityViewModels()
    private lateinit var mBinding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentCategoryBinding.inflate(inflater, container, false)
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
                userId = user.id,
                timer.toString(),
                "ProductFragment"
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().apply {
            val title = this.getString("type")
            mBinding.toolbar.title = title
        }
        viewModel.getFoodLiveData().observe(viewLifecycleOwner) {
            when (it.status) {
                MyResult.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    Log.e("ooo", it.message.toString())
                }
                MyResult.Status.SUCCESS -> {
                    val array = it.data as ArrayList<Product>
                    Log.e("ooo", array.toString())
                    mAdapter.arrayList.clear()
                    mAdapter.arrayList.addAll(array)
                    mAdapter.notifyDataSetChanged()

                }
                MyResult.Status.LOADING -> {
                }
                MyResult.Status.EMPTY -> {
                }

            }
        }
        mBinding.rcProduct.apply {
            adapter = mAdapter
        }
    }

    override fun onClick(product: Product) {
        viewModel.onClickEvent(
            uId = user.id,
            uName = user.name,
            name = product.name,
            "open_details"
        )
        val bundle = Bundle()
        bundle.putParcelable("product", product)
        val fragment = DetailsFragment()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.navHostFragment, fragment
        ).addToBackStack(null).commit()
    }

}