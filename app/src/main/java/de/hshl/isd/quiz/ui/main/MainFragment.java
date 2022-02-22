package de.hshl.isd.quiz.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import de.hshl.isd.quiz.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private Button mFalseButton;
    private Button mTrueButton;
    private Button mSkipButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mQuestionTextView = (TextView) requireActivity().findViewById(R.id.questionTextView);
        mAnswerTextView = (TextView) requireActivity().findViewById(R.id.answerTextView);
        mFalseButton = (Button) requireActivity().findViewById(R.id.falseButton);
        mTrueButton = (Button) requireActivity().findViewById(R.id.trueButton);
        mSkipButton = (Button) requireActivity().findViewById(R.id.skipButton);
        mViewModel = new ViewModelProvider(requireActivity())
                .get(MainViewModel.class);
        mViewModel.getQuestion().observe(getViewLifecycleOwner(),
                v -> mQuestionTextView.setText(v));
        mViewModel.getAnswer().observe(getViewLifecycleOwner(),
                v -> mAnswerTextView.setText(v));
        mTrueButton.setOnClickListener(v -> {
            mViewModel.evaluateAnswer(true);
            showAnswer();
        });
        mFalseButton.setOnClickListener(v -> {
            mViewModel.evaluateAnswer(false);
            showAnswer();
        });
        mSkipButton.setOnClickListener(v -> {
            mViewModel.skipQuestion();
        });
    }

    private void showAnswer() {
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
        mSkipButton.setEnabled(false);

        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(2000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewModel.increaseIndex();
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);
                mSkipButton.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mAnswerTextView.startAnimation(animation);
    }

}