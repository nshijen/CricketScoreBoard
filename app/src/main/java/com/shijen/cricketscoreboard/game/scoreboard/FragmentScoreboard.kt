package com.shijen.cricketscoreboard.game.scoreboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shijen.cricketscoreboard.R
import com.shijen.cricketscoreboard.game.GameViewModel
import com.shijen.cricketscoreboard.game.pitch.PlayersAdapter
import kotlinx.android.synthetic.main.fragment_scoreboard.*
import kotlinx.android.synthetic.main.fragment_scoreboard.view.*


class FragmentScoreboard : Fragment() {
    var adapter = PlayersAdapter()
    lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_scoreboard, container, false)
        initViews(root)
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentFragment?.let{
            viewModel = ViewModelProvider(
                it,
                ViewModelProvider.NewInstanceFactory()
            ).get(GameViewModel::class.java)
        }
        viewModel.playersScoreList.observe(this, Observer {
            adapter.updatePlayers(it)
        })
        viewModel.totalUpdate.observe(this, Observer {
            tv_total_score.setText(it.first)
            tv_overs.setText(it.second)
        })
        viewModel.oversUpdate.observe(this, Observer {
            tv_over_update.setText(it)
        })
    }

    private fun initViews(root: View) {
        root.rv_score_board.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        root.rv_score_board.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun getInstance(): Fragment {
            return FragmentScoreboard()
        }
    }
}
