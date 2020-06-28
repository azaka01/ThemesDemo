package com.intsoftdev.themesdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachViews(findViewById(R.id.rootView), R.style.ThemeOverlay_ThemesDemo_Dark)
    }

    fun attachViews(parent: ViewGroup, @StyleRes themeResId: Int) {
        parent.findViewById<ViewGroup>(R.id.themePanel)?.let { parent.removeView(it) }
        parent.findViewById<ViewGroup>(R.id.themeSelector)?.let { parent.removeView(it) }
        parent.findViewById<ViewGroup>(R.id.settingsSelection)?.let { parent.removeView(it) }
        parent.findViewById<ViewGroup>(R.id.viewPagerContainer)?.let { parent.removeView(it) }
        parent.addView(createThemePanel(parent, themeResId))
        parent.addView(createSelectorPanel(parent, themeResId))
        parent.addView(createSettingsPanel(parent, themeResId))
        parent.addView(createViewPager(parent, themeResId))
        setupListeners(parent)
    }

    private fun createThemePanel(parent: ViewGroup, @StyleRes themeResId: Int) : View {
        val themeContext = ContextThemeWrapper(parent.context, themeResId)
        return LayoutInflater.from(themeContext)
            .inflate(R.layout.themed_panel, parent, false)
            .apply { id = R.id.themePanel }
    }

    private fun createSelectorPanel(parent: ViewGroup, @StyleRes themeResId: Int) : View {
        val themeContext = ContextThemeWrapper(parent.context, themeResId)
        return LayoutInflater.from(themeContext)
            .inflate(R.layout.theme_selector, parent, false)
            .apply { id = R.id.themeSelector }
    }

    private fun createSettingsPanel(parent: ViewGroup, @StyleRes themeResId: Int) : View {
        val themeContext = ContextThemeWrapper(parent.context, themeResId)
        return LayoutInflater.from(themeContext)
            .inflate(R.layout.settings_selection, parent, false)
            .apply { id = R.id.settingsSelection }
    }

    private fun createViewPager(parent: ViewGroup, @StyleRes themeResId: Int) : View {
        val themeContext = ContextThemeWrapper(parent.context, themeResId)
        return LayoutInflater.from(themeContext)
            .inflate(R.layout.layout_sample_viewpager2, parent, false)
            .apply { id = R.id.viewPagerContainer }
    }


    private fun setupListeners(parent: ViewGroup) {
        parent.findViewById<TextView>(R.id.lightTheme).setOnClickListener {
            setLightTheme()
        }
        parent.findViewById<TextView>(R.id.darkTheme).setOnClickListener {
            setDarkTheme()
        }

        parent.findViewById<TextView>(R.id.setting1).isSelected = true

        val adapter = SampleRecyclerAdapter(3)

        val viewpager: ViewPager2 = parent.findViewById<ViewPager2>(R.id.viewPager)
        viewpager.adapter = adapter

        // CircleIndicator3 for RecyclerView
        val indicator: CircleIndicator3 = parent.findViewById<CircleIndicator3>(R.id.circleIndicator)//view.findViewById(R.id.indicator)
        indicator.setViewPager(viewpager)

        // CurrentItem
        viewpager.setCurrentItem(2, false)

        // Observe Data Change
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
    }

    private fun setLightTheme() {
        attachViews(findViewById(R.id.rootView), R.style.ThemeOverlay_ThemesDemo_Light)
    }

    private fun setDarkTheme() {
        attachViews(findViewById(R.id.rootView), R.style.ThemeOverlay_ThemesDemo_Dark)
    }
}
