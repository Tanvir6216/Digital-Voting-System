package edu.university.voting.service;

import edu.university.voting.model.User;
import edu.university.voting.model.Voter;
import edu.university.voting.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link UserService}.
 * <p>
 * Contains business logic for registering and retrieving voters.
 * </p>
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * Construct the service with its repository dependency.
     *
     * @param userRepository repository for users
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Voter registerVoter(String username, String password, String fullName) {
        Voter v = new Voter(username, password, fullName);
        return userRepository.save(v);
    }

    @Override
    public Voter findVoterById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!(u instanceof Voter)) {
            throw new IllegalArgumentException("User is not a voter");
        }
        return (Voter) u;
    }
}
