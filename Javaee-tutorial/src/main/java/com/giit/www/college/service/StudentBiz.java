package com.giit.www.college.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.giit.www.entity.Student;

/**
 * Created by c0de8ug on 16-2-13.
 */
public interface StudentBiz {
	public List<Student> studentView();

	public void add(Student student, MultipartFile pic) throws IOException;

	public void update(Student student);

	public void delete(int studentId);
}
