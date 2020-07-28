package com.it_uatech.repositories.jdbc.ext;

import com.it_uatech.models.jdbc.Avatar;
import com.it_uatech.models.jdbc.EMail;
import com.it_uatech.models.jdbc.MyStudent;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyStudentResultSetExtractor implements
        ResultSetExtractor<Map<Long, MyStudent>> {
    @Override
    public Map<Long, MyStudent> extractData(ResultSet rs) throws SQLException,
            DataAccessException {

        Map<Long, MyStudent> students = new HashMap<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            MyStudent student = students.get(id);
            if (student == null) {
                student = new MyStudent(id, rs.getString("name"),
                        new Avatar(rs.getLong("avatar_id"), rs.getString("photo_url")),
                        new ArrayList<>(), new ArrayList<>());
                students.put(student.getId(), student);
            }

            student.getEmails().add(new EMail(rs.getLong("email_id"),
                    rs.getString("email")));
        }
        return students;
    }
}
