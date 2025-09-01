package th.mfu.boot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {

    private UserController controller = new UserController();

    @BeforeEach
    public void resetUsers() {
        UserController.users.clear(); // reset state ก่อนแต่ละ test
    }

    @Test
    public void testRegisterUser() {
        // Arrange
        User request = new User("john", "John Doe", "john@example.com");

        // Act
        ResponseEntity<String> response = controller.registerUser(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // ใช้ 201 CREATED
        assertEquals("User registered successfully", response.getBody());

        ResponseEntity<User> user = controller.getUser("john");
        assertEquals("john", user.getBody().getUsername());
    }

    @Test
    public void testRegisterDuplicateUser() {
        // Arrange
        User request1 = new User("brian", "Brian Smith", "brian@example.com");
        User request2 = new User("brian", "Brian Smith", "brian@example.com");

        // Act
        ResponseEntity<String> response1 = controller.registerUser(request1);
        ResponseEntity<String> response2 = controller.registerUser(request2);

        // Assert
        assertEquals(HttpStatus.CREATED, response1.getStatusCode()); // 201 CREATED
        assertEquals("User registered successfully", response1.getBody());

        assertEquals(HttpStatus.CONFLICT, response2.getStatusCode()); // 409 CONFLICT
        assertEquals("Username already exists", response2.getBody());
    }

    @Test
    public void testGetUser() {
        User request = new User("alice", "Alice Walker", "alice@example.com");
        controller.registerUser(request);

        ResponseEntity<User> user = controller.getUser("alice");
        assertEquals(HttpStatus.OK, user.getStatusCode());
        assertEquals("alice", user.getBody().getUsername());

        ResponseEntity<User> unknownUser = controller.getUser("unknown");
        assertEquals(HttpStatus.NOT_FOUND, unknownUser.getStatusCode());
    }
}
