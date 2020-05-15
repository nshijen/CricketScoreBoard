package com.shijen.cricketscoreboard.ScoreBoard

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shijen.cricketscoreboard.R
import kotlinx.android.synthetic.main.fragment_scoreboard.*
import kotlinx.android.synthetic.main.fragment_scoreboard.view.*


class FragmentScoreboard : Fragment(), View.OnClickListener {
    var adapter = PlayersAdapter()
    lateinit var viewModel: ScoreboardViewmodel
    lateinit var vibrator: Vibrator

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
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(ScoreboardViewmodel::class.java)
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
        root.btn_bowl.setOnClickListener(this)
        root.rv_score_board.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        root.rv_score_board.adapter = adapter
        vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
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
