package de.hshl.isd.quiz.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import de.hshl.isd.quiz.R;
import de.hshl.isd.quiz.ui.main.MainViewModel;

public class StatisticsFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView mQuestionsTextView;
    private TextView mCorrectQuestionsTextView;
    private TextView mWrongQuestionsTextView;
    private TextView mSkippedQuestionsTextView;

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity())
                .get(MainViewModel.class);
        mQuestionsTextView = (TextView) requireActivity().findViewById(R.id.questionsTextView);
        mCorrectQuestionsTextView =
                (TextView) requireActivity().findViewById(R.id.correctQuestionsTextView);
        mWrongQuestionsTextView =
                (TextView) requireActivity().findViewById(R.id.wrongQuestionsTextView);
        mSkippedQuestionsTextView =
                (TextView) requireActivity().findViewById(R.id.skippedQuestionsTextView);
        mQuestionsTextView.setText(Integer.toString(mViewModel.getAnsweredQuestions()));
        mCorrectQuestionsTextView.setText(Integer.toString(mViewModel.getCorrectAnswers()));
        mWrongQuestionsTextView.setText(Integer.toString(mViewModel.getWrongAnswers()));
        mSkippedQuestionsTextView.setText(Integer.toString(mViewModel.getSkippedQuestions()));
    }


}