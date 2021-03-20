package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.util.EmailUtil;
import ru.itis.javalab.util.MailGenerator;

import java.util.UUID;

@Profile("dev")
@Service
public class SignUpServiceFakeImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailGenerator mailGenerator;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${mail.username}")
    private String from;

    @Value("${mail.message.subject}")
    private String subject;


    @Override
    public void signUp(UserForm form) {

        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .password(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .confirmCode(UUID.randomUUID().toString())
                .state(User.State.NOT_CONFIRMED)
                .build();

        usersRepository.save(user);

        String confirmMail = mailGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        System.out.println(confirmMail);

    }
}
