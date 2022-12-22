package francois.pauletienne.challengeandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import francois.pauletienne.challengeandroid.R
import francois.pauletienne.challengeandroid.model.Category

class CategoryAdapter(private val listener: CategoryItemListener? = null) : RecyclerView.Adapter<CategoryViewHolder>() {

    interface CategoryItemListener {
        fun onCategoryClicked(category: Category)
    }

    private val items = ArrayList<Category>()

    fun setItems(items: ArrayList<Category>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = items[position]
        holder.categoryName.text = category.name
        if (listener != null) {
            holder.itemLayout.setOnClickListener { listener.onCategoryClicked(category) }
        } else {
            holder.chevronRight.visibility = View.GONE
        }
    }
}

class CategoryViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    val itemLayout: ConstraintLayout = itemView.findViewById(R.id.category_item)
    val categoryName: TextView = itemView.findViewById(R.id.category_name_textview)
    val chevronRight: ImageView = itemView.findViewById(R.id.chevron_right)

}