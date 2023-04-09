package atm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Test sorting 4000 ATMs from 1000 regions.
 *
 * @author Dominik_Janiga
 */
@SpringBootTest
@AutoConfigureMockMvc
class Sorting1000RegionsIntegrationTest {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnACollectionOfSortedATMs_whenSentListOf1000Regions() throws Exception {
        //given
        int numberOfRegions = 1000;
        List<Task> tasks = TasksGenerator.generateTasks(numberOfRegions);
        List<ATM> atms = TasksGenerator.generateTheResponse(tasks);
        String requestContent = MAPPER.writeValueAsString(tasks);
        String expectedResponse = MAPPER.writeValueAsString(atms);

        this.mockMvc.perform(post("/atms/calculate-order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }

    private static class TasksGenerator {
        static List<Task> generateTasks(int numberOfRegions) {
            RequestType[] requestTypes = RequestType.values();
            List<Task> tasks = new ArrayList<>();
            for (int region = 1; region <= numberOfRegions; ++region) {
                for (int atmId = 0; atmId < requestTypes.length;) {
                    tasks.add(new Task(region, requestTypes[atmId], ++atmId));
                }
            }
            return tasks;
        }

        static List<ATM> generateTheResponse(List<Task> tasks) {
            return tasks.stream().map(Task::toATM).toList();
        }
    }
}
