package co.uk.random.util.extension
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

fun Fragment.toast(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }

fun Fragment.longToast(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_LONG).show() }

fun Fragment.createLayoutManager(orientation: Int = LinearLayoutManager.VERTICAL
                                 , reverseLayout: Boolean = false): RecyclerView.LayoutManager =
        LinearLayoutManager(context, orientation, reverseLayout)

fun Fragment.createGridLayoutManager(columns: Int = 2): GridLayoutManager =
        GridLayoutManager(context, columns)