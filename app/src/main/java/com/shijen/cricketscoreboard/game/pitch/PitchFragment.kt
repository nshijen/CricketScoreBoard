package com.shijen.cricketscoreboard.game.pitch

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shijen.cricketscoreboard.R
import com.shijen.cricketscoreboard.entities.BallOutput
import com.shijen.cricketscoreboard.game.GameViewModel
import kotlinx.android.synthetic.main.fragment_pitch.*


/**
 * A simple [Fragment] subclass.
 */
class PitchFragment : Fragment() {
    lateinit var viewModel: GameViewModel
    var player1: View? = null
    var player2: View? = null
    var totalScore: TextView? = null
    var totalOvers: TextView? = null
    lateinit var listener: AnimatorListenerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pitch, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        player1 = view?.findViewById(R.id.v_player1)
        player2 = view?.findViewById(R.id.v_player2)
        totalScore = view?.findViewById(R.id.tv_pitch_score)
        totalOvers = view?.findViewById(R.id.tv_pitch_overs)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let {
            viewModel = ViewModelProvider(
                it, ViewModelProvider.NewInstanceFactory()
            ).get(GameViewModel::class.java)
        }
        viewModel.ballOutput.observe(this, Observer { ballOutput ->
            when (ballOutput) {
                BallOutput.SINGLE, BallOutput.DOUBLE, BallOutput.TRIPLE -> animatePlayers(
                    ballOutput.runs
                )
            }
        })
        viewModel.totalUpdate.observe(this, Observer {
            totalScore?.setText(it.first)
            totalOvers?.setText(it.second)
        })
    }

    private fun animatePlayers(runs: Int) {
        var count = runs
        val p1_y = player1?.y
        val p2_y = player2?.y

        if (p1_y != null && p2_y != null) {
            listener = object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    val p1_y = player1?.y
                    val p2_y = player2?.y
                    count--
                    if (p1_y != null && p2_y != null) {
                        if (count != 0) {
                            runAnimation(p1_y, p2_y, this)
                        }
                    }
                }
            }
            runAnimation(p1_y, p2_y, listener)
        }


    }

    fun runAnimation(
        p1_y: Float,
        p2_y: Float,
        listener: AnimatorListenerAdapter
    ) {
        lateinit var animator1: ObjectAnimator
        lateinit var animator2: ObjectAnimator
        val animatorSet = AnimatorSet()
        animatorSet.addListener(listener)
        animator1 = ObjectAnimator.ofFloat(v_player1, "y", p1_y, p2_y)
        animator2 = ObjectAnimator.ofFloat(v_player2, "y", p2_y, p1_y)
        animatorSet.playTogether(animator1, animator2)
        animatorSet.duration = 1000
        animatorSet.start()
    }


    companion object {
        @JvmStatic
        fun getInstance(): Fragment {
            return PitchFragment()
        }
    }

}
