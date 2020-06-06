package com.pattern.ch15_observer2;

import java.util.ArrayList;
import java.util.List;

public class ScoreRecord {
    private List<Integer> scores = new ArrayList<Integer>(); // 점수를 저장함
    private DataSheetView dataSheetView; // 목록 형태로 점수를 출력

    public void setDataSheetView(DataSheetView dataSheetView) {
        this.dataSheetView = dataSheetView;
    }

    // 새로운 점수를 추가하면 출력하는 것에 변화를 통보(update())하여 출력하는 부분 갱신
    public void addScore(int score) {
        scores.add(score); // scores에 점수를 추가함
        dataSheetView.update(); // scores가 변경됨을 통보함
    }

    public List<Integer> getScoreRecord() {
        return scores;
    }
}
