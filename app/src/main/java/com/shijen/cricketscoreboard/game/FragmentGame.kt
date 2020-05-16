package com.shijen.cricketscoreboard.game

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shijen.cricketscoreboard.R
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentGame : Fragment(), View.OnClickListener {
    lateinit var viewModel: GameViewModel
    lateinit var vibrator: Vibrator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator;
            activity?.let {
                viewModel = ViewModelProvider(it,ViewModelProvider.NewInstanceFactory()
                ).get(GameViewModel::class.java)
            }

    }

    private fun initView(view: View) {
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        val tablayout = view.findViewById<TabLayout>(R.id.tab_layout)
        viewPager.adapter = ViewPagerAdapter(this)
        view.btn_bowl.setOnClickListener(this)
        TabLayoutMediator(tablayout,viewPager){ tab,pos ->
            if(pos == 0){
                tab.text = "GUI"
            }else{
                tab.text = "DETAIL"
            }
        }.attach()
    }

    companion object {
        @JvmStatic
        public fun getInstance(): Fragment {
            return FragmentGame()
        }
    }

    private fun animateObject() {
        btn_bowl.animate().rotation(360f).withEndAction {
            btn_bowl.rotation = 0f
        }
    }

    override fun onClick(v: View?) {
        viewModel.bowl()
        animateObject()
        vibrator.vibrate(100);
    }
}
