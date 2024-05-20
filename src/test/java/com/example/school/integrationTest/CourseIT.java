package com.example.school.integrationTest;

import com.example.school.repository.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseIT {
    @Autowired
    private WebTestClient webTestClient;
    String path = "/api/v1/courses";
    Course course;
    @BeforeEach
    void setUp() {
        course = new Course(1L, "math", 10);
    }

    @Test
    void getAllCourseTest() {
        webTestClient.get()
                .uri(path)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void getByIdTest(){
        webTestClient.get()
                .uri(path+"/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("math");
    }
    @Test
    void createTest(){
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(course), Course.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.id").isNotEmpty();
    }
    @Test
    void createTest_throwsException(){
        course.setCapacity(1);
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(course), Course.class)
                .exchange()
                .expectStatus().isBadRequest();

    }
    @Test
    void updateTest(){
        webTestClient.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(course), Course.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void updateTest_throwsExceptions(){
        course.setId(100L);
        webTestClient.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(course), Course.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }
    @Test
    void updateTest_throwsExceptions2(){
        course.setCapacity(1);
        webTestClient.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(course), Course.class)
                .exchange()
                .expectStatus().isBadRequest();
    }
    @Test
    void deleteTest(){
        webTestClient.delete()
                .uri(path+"/1")
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void deleteTest_ThrowsException(){
        webTestClient.delete()
                .uri(path+"/100")
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
