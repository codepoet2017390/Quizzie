package com.rohan.quizzie;

public class TrueFalse {

    int mQuestionID;
    boolean Answer;


    public TrueFalse(int QID,boolean Answer)
    {
        mQuestionID=QID;
        this.Answer=Answer;


    }


    public int getQuestionId()
    {
        return mQuestionID;
    }
    public boolean getAnswer()
    {
        return Answer;
    }
}
