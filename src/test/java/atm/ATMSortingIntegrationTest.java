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
 * Sorting atm test.
 *
 * @author Dominik_Janiga
 */
@SpringBootTest
@AutoConfigureMockMvc
class ATMSortingIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnACollectionOfSortedATMs_whenSentListOfTasks() throws Exception {
        String atmsToSort = readResourceAsString("tasks_request.json");
        String expectedResponse = readResourceAsString("atms_response.json");

        this.mockMvc.perform(post("/atms/calculate-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(atmsToSort))
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }

    @Test
    void shouldReturnACollectionOfSortedATMs_whenSentListOf1000Regions() throws Exception {
        String atmsToSort = readResourceAsString("1000_regions_request.json");
        String expectedResponse = readResourceAsString("1000_regions_response.json");

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
