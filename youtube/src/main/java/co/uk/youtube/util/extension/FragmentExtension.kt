package co.uk.youtube.util.extension
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

fun androidx.fragment.app.Fragment.toast(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }

fun androidx.fragment.app.Fragment.longToast(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_LONG).show() }

fun androidx.fragment.app.Fragment.createLayoutManager(orientation: Int = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                                                       , reverseLayout: Boolean = false): androidx.recyclerview.widget.RecyclerView.LayoutManager =
        androidx.recyclerview.widget.LinearLayoutManager(context, orientation, reverseLayout)

fun androidx.fragment.app.Fragment.createGridLayoutManager(columns: Int = 2): androidx.recyclerview.widget.GridLayoutManager =
        androidx.recyclerview.widget.GridLayoutManager(context, columns)