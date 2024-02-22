package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicationExampleTest {

    private static int count=0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext applicationContext;

    @BeforeEach
    public void beforeEach(){
        count+=1;
        System.out.println("Testing: "+appInfo+" which is "+appDescription+" version: "+appVersion+" .Execution of test method "+count);
        student.setFirstname("Eric");
        student.setLastname("Robi");
        student.setEmailAddress("ericrobi123@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(101.3,198.6,100.0,50.34)));
        student.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade result for student grades")
    @Test
    public void addGradeResultForStudentGrades(){
        assertEquals(450.24,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }


    @DisplayName("Add grade result for student grades not equals")
    @Test
    public void addGradeResultForStudentGradesNotEquals(){
        assertNotEquals(302.56,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Is Grade Greater")
    @Test
    public void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90,75),"Failure should be true");
    }

    @DisplayName("Is Grade Greater False")
    @Test
    public void isGradeGreaterStudentGradesFalse(){
        assertFalse(studentGrades.isGradeGreater(90,750),"Failure should be false");
    }

    @DisplayName("Check Null for student grades")
    @Test
    public void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),"Object should not be null");
    }

    @DisplayName("create student without grade init")
    @Test
    public void createStudentWithoutGradeInit(){

        CollegeStudent student2=applicationContext.getBean("collegeStudent",CollegeStudent.class);
        student2.setFirstname("Tushar");
        student2.setLastname("Agrawal");
        student2.setEmailAddress("tusharagarwal123@gmail.com");
        assertNotNull(student2.getFirstname());
        assertNotNull(student2.getLastname());
        assertNotNull(student2.getEmailAddress());
        assertNull(studentGrades.checkNull(student2.getStudentGrades()));

    }

    @DisplayName("verify student are prototype")
    @Test
    public void verifyStudentArePrototype(){

        CollegeStudent student2=applicationContext.getBean("collegeStudent",CollegeStudent.class);

        assertNotSame(student,student2);

    }


}
