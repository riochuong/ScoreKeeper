package scorekeeper.jd.com.scorekeeper;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreKeeperMainActivity extends AppCompatActivity implements Button.OnClickListener {

    // instance variables help to keep track of the score
    TextView leftTeamTotalScore = null;
    TextView rightTeamTotalScore = null;

    TextView leftTeamTotalThree = null;
    TextView rightTeamTotalThree = null;

    TextView leftTeamTotalTwo = null;
    TextView rightTeamTotalTwo = null;

    TextView leftTeamTotalOne = null;
    TextView rightTeamTotalOne = null;


    private static final int INCREMENT_ONE = 1;
    private static final int INCREMENT_TWO = 2;
    private static final int INCREMENT_THREE = 3;

    private static final String TAG = "ScoreKeeper";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper_main);

        leftTeamTotalScore = (TextView) this.findViewById(R.id.left_team_total_score_text);
        rightTeamTotalScore = (TextView) this.findViewById(R.id.right_team_total_score_text);

        leftTeamTotalThree = (TextView) this.findViewById(R.id.left_team_total_three_point_text);
        rightTeamTotalThree = (TextView) this.findViewById(R.id.right_team_total_three_point_text);

        leftTeamTotalTwo = (TextView) this.findViewById(R.id.left_team_total_two_point_text);
        rightTeamTotalTwo = (TextView) this.findViewById(R.id.right_team_total_two_point_text);

        leftTeamTotalOne = (TextView) this.findViewById(R.id.left_team_total_one_point_text);
        rightTeamTotalOne = (TextView) this.findViewById(R.id.right_team_total_one_point_text);

    }



    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch(id)
        {
            // INCREASE HERE
            case R.id.letf_team_increase_three_point_btn:
                increasePoint(INCREMENT_THREE,  leftTeamTotalThree,leftTeamTotalScore);
                break;
            case R.id.right_team_increase_three_point_btn:
                increasePoint(INCREMENT_THREE, rightTeamTotalThree, rightTeamTotalScore);
                break;

            case R.id.letf_team_increase_two_point_btn:
                increasePoint(INCREMENT_TWO,  leftTeamTotalTwo,leftTeamTotalScore);
                break;

            case R.id.right_team_increase_two_point_btn:
                increasePoint(INCREMENT_TWO, rightTeamTotalTwo, rightTeamTotalScore);
                break;

            case R.id.letf_team_increase_one_point_btn:
                increasePoint(INCREMENT_ONE,  leftTeamTotalOne,leftTeamTotalScore);
                break;

            case R.id.right_team_increase_one_point_btn:
                increasePoint(INCREMENT_ONE, rightTeamTotalOne, rightTeamTotalScore);
                break;

            // DECREASE HERE
            case R.id.letf_team_decrease_three_point_btn:
                decreasePoint(INCREMENT_THREE,  leftTeamTotalThree,leftTeamTotalScore);
                break;
            case R.id.right_team_decrease_three_point_btn:
                decreasePoint(INCREMENT_THREE, rightTeamTotalThree, rightTeamTotalScore);
                break;

            case R.id.letf_team_decrease_two_point_btn:
                decreasePoint(INCREMENT_TWO,  leftTeamTotalTwo,leftTeamTotalScore);
                break;

            case R.id.right_team_decrease_two_point_btn:
                decreasePoint(INCREMENT_TWO, rightTeamTotalTwo, rightTeamTotalScore);
                break;

            case R.id.letf_team_decrease_one_point_btn:
                decreasePoint(INCREMENT_ONE,  leftTeamTotalOne,leftTeamTotalScore);
                break;

            case R.id.right_team_decrease_one_point_btn:
                decreasePoint(INCREMENT_ONE, rightTeamTotalOne, rightTeamTotalScore);
                break;

            default:
                Log.d(TAG, "Should not get here . Un-handle on click event");

        }

        updateLeadingScore();


    }

    /**
     * compare total score and change the leading team score to red
     */
    private void updateLeadingScore()
    {
        int leftTeamScore = Integer.parseInt(leftTeamTotalScore.getText().toString());
        int rightTeamScore = Integer.parseInt(rightTeamTotalScore.getText().toString());

        if (leftTeamScore > rightTeamScore)
        {
            leftTeamTotalScore.setTextColor(Color.RED);
            rightTeamTotalScore.setTextColor(Color.GRAY);
        }
        else if (rightTeamScore > leftTeamScore)
        {
            rightTeamTotalScore.setTextColor(Color.RED);
            leftTeamTotalScore.setTextColor(Color.GRAY);
        }
        else
        {
            leftTeamTotalScore.setTextColor(ContextCompat.getColor(this,R.color.colorTotalScore));
            rightTeamTotalScore.setTextColor(ContextCompat.getColor(this,R.color.colorTotalScore));
        }

    }

    /**
     *  increase total point as well as the specicific count of the score.
     * @param increment - poin to increase
     * @param scoreCountView - count view to increase
     */
    private void increasePoint(int increment, TextView scoreCountView, TextView totalScoreView)
    {
        if (scoreCountView != null && totalScoreView !=null)
        {
            //let increase the count first
            int currentCount = Integer.parseInt(scoreCountView.getText().toString());
            scoreCountView.setText(currentCount + 1 + "");

            // now increase the total score
            int currentTotalScore = Integer.parseInt(totalScoreView.getText().toString());
            totalScoreView.setText(increment + currentTotalScore + "");
        }


    }

    /**
     *  decrease total point as well as the specicific count of the score.\
     *  avoid negative count and score also
     * @param decrement - poin to decrease
     * @param scoreCountView - count view to decrease
     */
    private void decreasePoint(int decrement, TextView scoreCountView, TextView totalScoreView)
    {
        if (scoreCountView != null && totalScoreView !=null)
        {
            //let decrease count first
            int currentCount = Integer.parseInt(scoreCountView.getText().toString());
            // make sure we dont have negative count and we dont have to do anything if count
            // of a specifc score is zero
            if (currentCount > 0)
            {
                int updateCount = ((currentCount - 1) < 0) ? 0 : currentCount - 1;
                scoreCountView.setText(updateCount + "");

                // now increase the total score
                int currentTotalScore = Integer.parseInt(totalScoreView.getText().toString());
                // make sure we dont have negative point
                int updatetotalScore = ((currentTotalScore - decrement) < 0)
                                                    ? 0 : currentTotalScore - decrement;
                totalScoreView.setText(updatetotalScore + "");

            }
        }


    }
}
