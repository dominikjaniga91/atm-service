package atm;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Sorts and removes duplicated ATMs that has lower priority status.
 *
 * @author Dominik_Janiga
 */
@SpringBootTest
@AutoConfigureMockMvc
class SortingAndRemovingATMsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnACollectionOfSortedATMs_whenSentListOf1000Regions() throws Exception {
        String atmsToSort = readResourceAsString("duplicated_atms_request.json");
        String expectedResponse = readResourceAsString("duplicated_atms_response.json");

        this.mockMvc.perform(post("/atms/calculate-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atmsToSort))
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }

    private InputStream readResourceAsStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    private String readResourceAsString(String fileName) throws IOException {
        try (InputStream resourceAsStream = readResourceAsStream(fileName)) {
            byte[] response = IOUtils.toByteArray(resourceAsStream);
            return new String(response);
        }
    }
}
