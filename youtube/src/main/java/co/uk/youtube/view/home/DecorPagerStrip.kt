package co.uk.youtube.view.home

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerTabStrip
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import co.uk.youtube.R

@androidx.viewpager.widget.ViewPager.DecorView
class DecorPagerStrip : androidx.viewpager.widget.PagerTabStrip {
    constructor(context: Context) : super(context) {
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTabIndicatorColor(ContextCompat.getColor(context, R.color.white));
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTabIndicatorColor(ContextCompat.getColor(context, R.color.white));
    }
}
