package com.example.school.integrationTest;

import com.example.school.repository.model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeacherIT {
    @Autowired
    private WebTestClient webTestClient;

    Teacher teacher;
    String path = "/api/v1/teachers";

    @BeforeEach
    void setUp() {
        teacher = new Teacher(1l, "maryam", "mokhtari", 500.0);
    }

    @Test
    void getAllTeacherTest() {
        webTestClient.get()
                .uri(path)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getByIdTeacherTest() {
        webTestClient.get()
                .uri(path + "/1")
                .exchange()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("maryam");
    }

    @Test
    void createTeacherTest() {
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(teacher), Teacher.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.id").isNotEmpty();
    }

    @Test
    void createTeacherTest_ThrowsException() {
        teacher.setSalary(90.00);
        webTestClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(teacher), Teacher.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void updateTeacherTest() {
        webTestClient.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(teacher), Teacher.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void updateTeacherTest_ThrowsExceptin() {
        teacher.setId(100L);
        webTestClient.put()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(teacher), Teacher.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void deleteTest() {
        webTestClient.delete()
                .uri(path + "/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void deleteTest_ThrowsException() {
        webTestClient.delete()
                .uri(path + "/100")
                .exchange()
                .expectStatus().is5xxServerError();
    }

}
