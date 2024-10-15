package id.android.skeleton.ui.components.details

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.android.skeleton.common.Utils.setVisibleNotNull
import id.android.skeleton.databinding.ComponentDetailsBinding

class ComponentDetails(
    context: Context,
    attributeSet: AttributeSet,
) : ConstraintLayout(context, attributeSet) {

    private var binding: ComponentDetailsBinding? = null
    private var componentDetailsAdapter: ComponentDetailsAdapter? = null

    init {
        binding = ComponentDetailsBinding.inflate(
            LayoutInflater.from(context), this
        )
        componentDetailsAdapter = ComponentDetailsAdapter()
        binding?.rvDetails?.apply {
            layoutManager = object : LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            ) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = componentDetailsAdapter
        }
        if (isInEditMode) {
            setLabel("Payment Details")
            setList(
                listOf(
                    Detail(
                        id = 1,
                        label = "Payment Ref Number",
                        value1 = "ESB123456783",
                        value2 = "GOMOB9877776",
                    ),
                    Detail(
                        id = 2,
                        label = "Total Amount",
                        value1 = "USD 100,000.00",
                        altValue1 = "USD 1.00 = IDR 15,000.00",
                        altValue2 = "IDR 300.000,00 from Poin Xtra"
                    ),
                    Detail(
                        id = 3,
                        label = "Payment Type",
                        value1 = "QRIS Payment",
                    ),
                    Detail(
                        id = 4,
                        label = "TID",
                        value1 = "MPAN 9866123712",
                        value2 = "CPAN 1231271231",
                    ),
                )
            )
            setShimmer(isShimmer = false)
        }
    }

    fun setShimmer(isShimmer: Boolean) {
        binding?.sflShimmer?.isVisible = isShimmer
        binding?.mcvDetail?.isVisible = !isShimmer
    }

    fun setLabel(label: String? = null) {
        binding?.tvLabel?.setVisibleNotNull(label)
    }

    fun setList(list: List<Detail>) {
        componentDetailsAdapter?.submitList(list)
    }
}