package org.tweetyproject.web.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Oleksandr Dzhychko
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(RequestController.class)
class RequestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void causalReasonerWithInvalidKnowledgeBaseReturnsStatus400() throws Exception {
        var post = post("/causal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "aId",
                          "cmd": "get_conclusions",
                          "kb": "a <=> (b\\nc <=> d\\n{ d, !b }",
                          "observations": "!a, !b",
                          "timeout": 10,
                          "unit_timeout": "s"
                        }
                        """);

        mvc.perform(post)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void causalReasonerWithInvalidObservationsReturnsStatus400() throws Exception {
        var post = post("/causal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "aId",
                          "cmd": "get_conclusions",
                          "kb": "a <=> b\\nc <=> d\\n{ d, !b }",
                          "observations": "!(a, !b",
                          "timeout": 10,
                          "unit_timeout": "s"
                        }
                        """);

        mvc.perform(post)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void causalReasonerIsCalledSuccessfully() throws Exception {
        var post = post("/causal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "email": "aId",
                          "cmd": "get_conclusions",
                          "kb": "a <=> b\\nc <=> d\\n{ d, !b }",
                          "observations": "!a, !b",
                          "timeout": 10,
                          "unit_timeout": "s"
                        }
                        """);

        mvc.perform(post)
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "reply": "[!a, !b, c, d]",
                          "email": "aId",
                          "time": 0,
                          "unit_timeout": "s",
                          "status": "SUCCESS"
                        }
                        """));
    }
}