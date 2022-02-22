package de.hshl.isd.quiz.ui.main;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import de.hshl.isd.quiz.R;

public class MainViewModel extends ViewModel {
    private List<Pair<String, Boolean>> questions = new ArrayList<>();
    private MutableLiveData<Integer> index = new MutableLiveData<>(0);
    public LiveData<Integer> getIndex() { return index; }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public int getSkippedQuestions() {
        return skippedQuestions;
    }

    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private int skippedQuestions = 0;

    public int getAnsweredQuestions() { return correctAnswers + wrongAnswers + skippedQuestions; }

    public LiveData<String> getQuestion() {
        return Transformations.map(index, (index) -> questions.get(index).first);
    }

    private MutableLiveData<String> answer = new MutableLiveData<>();
    public LiveData<String> getAnswer() {return answer;}

    public MainViewModel() {
        questions.add(new Pair<>("Das Videospiel Donkey Kong sollte ursprünglich Popeye als Hauptfigur haben.", true));
        questions.add(new Pair<>("Die Farbe Orange wurde nach der Frucht benannt.", true));
        questions.add(new Pair<>("In der griechischen Mythologie ist Hera die Göttin der Ernte.", false));
        questions.add(new Pair<>("Liechtenstein hat keinen eigenen Flughafen.", true));
        questions.add(new Pair<>("Die meisten Subarus werden in China hergestellt.", false));
    }

    public void increaseIndex() {
        index.setValue((index.getValue() + 1) % questions.size());
        answer.setValue("");
    }

    public void evaluateAnswer(boolean givenAnswer) {
        if (givenAnswer == questions.get(index.getValue()).second) {
            answer.setValue("Richtig!");
            correctAnswers++;
        } else {
            answer.setValue("Falsch!");
            wrongAnswers++;
        }
    }


    public void skipQuestion() {
        skippedQuestions++;
        increaseIndex();
    }
}