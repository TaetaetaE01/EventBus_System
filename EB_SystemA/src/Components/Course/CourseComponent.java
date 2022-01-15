/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University
 */
package Components.Course;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CourseComponent {
	protected ArrayList<Course> vCourse;
	private String sCourseFileName;

	public CourseComponent(String sCourseFileName) throws FileNotFoundException, IOException {

		this.sCourseFileName = sCourseFileName;

		BufferedReader bufferedReader = new BufferedReader(new FileReader(sCourseFileName));
		this.vCourse = new ArrayList<Course>();

		while (bufferedReader.ready()) {
			String courseInfo = bufferedReader.readLine();
			if (!courseInfo.equals(""))
				this.vCourse.add(new Course(courseInfo));
		}
		bufferedReader.close();
	}

	public ArrayList<Course> getCourseList() {
		return this.vCourse;
	}

	public boolean isRegisteredCourse(String courseId) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			if (((Course) this.vCourse.get(i)).match(courseId))
				return true;
		}
		return false;
	}

	public void updateCourseList() {
		try {
			new FileOutputStream(this.sCourseFileName).close();

			File courseFile = new File(this.sCourseFileName);
			FileWriter fwCourse = new FileWriter(courseFile, true);

			for (Course temp : vCourse) {
				fwCourse.write(temp.courseId + ' ');
				fwCourse.write(temp.instructor + ' ');
				fwCourse.write(temp.name + ' ');
				for (int i = 0; i < temp.prerequisiteCoursesList.size(); i++) {
					fwCourse.write(temp.prerequisiteCoursesList.get(i) + ' ');
				}
				fwCourse.write(0x0a);
				fwCourse.write(0x0a);
				fwCourse.write(0x0a);
				fwCourse.write(0x0a);
			}
			fwCourse.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
