package co.uk.random.view.home

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerTabStrip
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import co.uk.random.R

@ViewPager.DecorView
class DecorPagerStrip : PagerTabStrip {
    constructor(context: Context) : super(context) {
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTabIndicatorColor(ContextCompat.getColor(context, R.color.white));
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setTextColor(ContextCompat.getColor(context, R.color.white))
        setTabIndicatorColor(ContextCompat.getColor(context, R.color.white));
    }
}