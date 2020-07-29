package study.spring.taskalways.apiserver.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import study.spring.taskalways.apiserver.user.domain.dto.SignUpRequest;
import study.spring.taskalways.apiserver.user.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SignUpControllerTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  ObjectMapper mapper;

  @MockBean
  UserService service;


  @Test
  void shouldSignUp() throws Exception {
    final SignUpRequest req = new SignUpRequest();
    req.setNickname("tester");
    req.setEmail("tester@test.com");
    req.setPin("Test123!");

    mvc.perform(
      post("/api/user/sign-up")
        .content(mapper.writeValueAsString(req))
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isCreated())
      .andDo(print());
  }

}
