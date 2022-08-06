package com.atlasoftware.cstudent.domain.memento;

import com.atlasoftware.cstudent.domain.ExamDao;
import com.atlasoftware.cstudent.domain.ExamType;
import com.atlasoftware.cstudent.domain.memento.SnapshotExam;

import java.util.Deque;
import java.util.LinkedList;
import java.util.UUID;

public class EditorExam {
    private Deque<SnapshotExam> stateHistory;
    private ExamDao examDao;

    public EditorExam(){
        stateHistory=new LinkedList<>();
        examDao=new ExamDao();
    }

    public void write(UUID id, String date, String courseCode, ExamType examType, String formula){
        examDao.setId(id);
        examDao.setDate(date);
        examDao.setCourseCode(courseCode);
        examDao.setExamType(examType);
        examDao.setFormula(formula);
        stateHistory.add(examDao.createSnapshot());
    }

    public void undo()
    {
        examDao.restore(stateHistory.pop());
    }
}
