import domain.User;
import org.junit.jupiter.api.Test;
import repository.UserRepo;
import service.UserService;
import service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void testChangeSubscription() {
        UserRepo userRepo = mock(UserRepo.class);

        UserService userService = new UserServiceImpl(userRepo);

        User channel = new User();
        User subscriber = new User();

        when(userRepo.save(channel)).thenReturn(channel);

        User updatedChannel = userService.changeSubscription(channel, subscriber);

        verify(userRepo, times(1)).save(channel);

        assertNotNull(updatedChannel);
    }
}