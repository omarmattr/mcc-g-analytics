package com.omarmattr.mcc_g_analytics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.omarmattr.mcc_g_analytics.R
import com.omarmattr.mcc_g_analytics.databinding.RecycleProductBinding
import com.omarmattr.mcc_g_analytics.model.Product

class ProductAdapter (val arrayList: ArrayList<Product>,val click:OnClick) :
    RecyclerView.Adapter<ProductAdapter.ProductAdapterVH>() {
    interface OnClick{
        fun onClick(product: Product)
    }
    inner class ProductAdapterVH(private val mItemView: RecycleProductBinding) :
        RecyclerView.ViewHolder(mItemView.root) {
        fun bing(item: Product) {
            mItemView.product = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterVH {
        return ProductAdapterVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycle_product, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductAdapterVH, position: Int) {
        holder.bing(arrayList[position])
        holder.itemView.setOnClickListener {
            click.onClick(arrayList[position])
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

   }