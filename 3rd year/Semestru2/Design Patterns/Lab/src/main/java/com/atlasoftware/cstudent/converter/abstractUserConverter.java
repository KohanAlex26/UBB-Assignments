package com.atlasoftware.cstudent.converter;

import com.atlasoftware.cstudent.domain.AbstractUserDao;
import com.atlasoftware.cstudent.domain.ProfessorDao;
import com.atlasoftware.cstudent.domain.StudentDao;
import com.atlasoftware.cstudent.dto.AbstractUserDto;

public class abstractUserConverter {
    public static AbstractUserDto convertModelToDto(AbstractUserDao user) {
        if (user instanceof ProfessorDao) {
            return ProfessorConverter.convertModelToDto((ProfessorDao) user);
        }
        if (user instanceof StudentDao) {
            return StudentConverter.convertModelToDto((StudentDao) user);
        }
        return null;
    }
}
