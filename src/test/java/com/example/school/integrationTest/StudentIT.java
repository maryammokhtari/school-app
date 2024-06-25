package com.example.school.integrationTest;

import com.example.school.repository.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIT {
    @Autowired
    private WebTestClient webTestClient;
    String path = "/api/v1/students";
    Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "maryam", "mokhtai", "tehran",null);
    }

    @Test
    void getAllStudentTest(){
        webTestClient.get()
                .uri(path)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void getByIdStudentTest(){
        webTestClient.get()
                .uri(path+"/1")
                .exchange()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("maryam");
    }

    @Test
    void createStudentTest(){
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(student),Student.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.id").isNotEmpty();

    }
    @Test
    void createStudentTest_throwsException(){
        student.setFirstName("");
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(student),Student.class)
                .exchange()
                .expectStatus().isBadRequest();

    }
    @Test
    void updateTest(){
        webTestClient.put()
                .uri(path+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(student),Student.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void updateTest_throwsExceptions(){
        webTestClient.put()
                .uri(path+"/100")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(student),Student.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }
    @Test
    void deleteTest(){
        webTestClient.delete()
                .uri(path+"/1")
                .exchange()
                .expectStatus().isOk();
    }  @Test
    void deleteTest_throwsExceptions(){
        webTestClient.delete()
                .uri(path+"/100")
                .exchange()
                .expectStatus().is5xxServerError();
    }

}













