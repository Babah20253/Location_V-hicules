package mr.iscae.location.service;

import mr.iscae.location.model.User;
import mr.iscae.location.model.Role;
import mr.iscae.location.util.DatabaseMemory;
import java.util.concurrent.atomic.AtomicLong;

public class UserService {
    private static AtomicLong idCounter = new AtomicLong(1);

    public UserService() {
        // Add default admin if not present
        if (DatabaseMemory.users.isEmpty()) {
            User admin = new User();
            admin.setId(idCounter.getAndIncrement());
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(Role.ADMIN);
            DatabaseMemory.users.put(admin.getId(), admin);
        }
    }

    public User login(String username, String password) {
        return DatabaseMemory.users.values().stream()
            .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
            .findFirst().orElse(null);
    }

    public User registerClient(User user) {
        user.setId(idCounter.getAndIncrement());
        user.setRole(Role.CLIENT);
        DatabaseMemory.users.put(user.getId(), user);
        return user;
    }

    public User createManager(User user) {
        user.setId(idCounter.getAndIncrement());
        user.setRole(Role.MANAGER);
        DatabaseMemory.users.put(user.getId(), user);
        return user;
    }
}
