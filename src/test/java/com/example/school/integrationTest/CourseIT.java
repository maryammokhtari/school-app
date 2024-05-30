package com.example.school.integrationTest;

import com.example.school.service.dto.CourseRequest;
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
    CourseRequest courseRequest;
    @BeforeEach
    void setUp() {
        courseRequest = new CourseRequest( "math", 10);
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
                .body(Mono.just(courseRequest), CourseRequest.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.id").isNotEmpty();
    }
    @Test
    void createTest_throwsException(){
        courseRequest.setCapacity(1);
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(courseRequest), CourseRequest.class)
                .exchange()
                .expectStatus().isBadRequest();

    }
    @Test
    void updateTest(){
        webTestClient.put()
                .uri(path +"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(courseRequest), CourseRequest.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void updateTest_InvalidId_throwsExceptions(){
        webTestClient.put()
                .uri(path+"/100")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(courseRequest), CourseRequest.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }
    @Test
    void updateTest_InvalidValue_throwsExceptions(){
        courseRequest.setCapacity(1);
        webTestClient.put()
                .uri(path+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(courseRequest), CourseRequest.class)
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
