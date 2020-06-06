package com.pattern.ch15_observer;

public class Button {
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick() {
        //이벤트 처리
        if(onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public interface OnClickListener {
        public void onClick(Button button);
    }
}
