import com.example.handlers.WorkerHandler;
import com.example.models.Worker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerHandlerTest {

    @Test
    public void testAddWorker() {
        WorkerHandler workerHandler = new WorkerHandler();
        int initialSize = workerHandler.getWorkers().size();

        Worker newWorker = new Worker("2", "Karol", "Perechowski", "123456789", "john.doe@example.com", "12345678901", true);
        workerHandler.addWorker(newWorker);

        int finalSize = workerHandler.getWorkers().size();

        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    public void testDeleteWorker() {
        WorkerHandler workerHandler = new WorkerHandler();
        int initialSize = workerHandler.getWorkers().size();

        workerHandler.deleteWorker("2");

        int finalSize = workerHandler.getWorkers().size();

        assertEquals(initialSize - 1, finalSize);
    }

    @Test
    public void testChangeWorkerData() {
        WorkerHandler workerHandler = new WorkerHandler();
        Worker oldWorker = workerHandler.findWorkerById("2");

        Worker newWorkerData = new Worker("2", "Karol", "Perechowski", "987654321", "karol.perechowski@gmail.com", "12345678901", true);
        workerHandler.changeWorkerData("2", newWorkerData);

        Worker updatedWorker = workerHandler.findWorkerById("2");

        assertNotEquals(oldWorker.getTelephone(), updatedWorker.getTelephone());
        assertEquals(newWorkerData.getTelephone(), updatedWorker.getTelephone());
    }

    @Test
    public void testFindWorkersByAttributes() {
        WorkerHandler workerHandler = new WorkerHandler();
        List<Worker> foundWorkers = workerHandler.findWorkersByAttributes("firstName", "Karol");

        assertFalse(foundWorkers.isEmpty());
        for (Worker worker : foundWorkers) {
            assertEquals("Karol", worker.getFirstName());
        }
    }
}