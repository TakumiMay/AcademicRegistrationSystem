package com.university.academicRegistrationSystem;

import com.university.academicRegistrationSystem.controller.CourseController;
import com.university.academicRegistrationSystem.controller.EnrollmentController;
import com.university.academicRegistrationSystem.controller.StudentController;
import com.university.academicRegistrationSystem.controller.SubjectController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AcademicRegistrationSystemApplicationTests {

	@Autowired
	private CourseController courseController;
	@Autowired
	private StudentController studentController;
	@Autowired
	private SubjectController subjectController;
	@Autowired
	private EnrollmentController enrollmentController;

	@Test
	void contextLoads() {
		assertThat(courseController)
				.isInstanceOf(CourseController.class)
				.isNotNull();
		assertThat(studentController)
				.isInstanceOf(StudentController.class)
				.isNotNull();
		assertThat(subjectController)
				.isInstanceOf(SubjectController.class)
				.isNotNull();
		assertThat(enrollmentController)
				.isInstanceOf(EnrollmentController.class)
				.isNotNull();
	}

}
