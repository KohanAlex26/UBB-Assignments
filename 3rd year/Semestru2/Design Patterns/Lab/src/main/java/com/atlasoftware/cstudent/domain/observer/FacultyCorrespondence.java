package com.atlasoftware.cstudent.domain.observer;

import com.atlasoftware.cstudent.domain.StudentDao;

import java.util.ArrayList;
import java.util.List;

public class FacultyCorrespondence {
    private List<StudentDao> subs = new ArrayList<>();
    private String title;

    public void subscribe(StudentDao studentDao)
    {
        subs.add(studentDao);
    }

    public void unSubscribe(StudentDao studentDao)
    {
        subs.remove(studentDao);
    }

    public void notifySubscribers()
    {
        for(StudentDao sub:subs)
        {
            sub.update();
        }
    }

    public void upload(String title)
    {
        this.title=title;
        notifySubscribers();
    }
}
