package com.multipledb.service;

import com.multipledb.dto.User;
import com.multipledb.executor.DataSourceExecutor;
import com.multipledb.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final DataSourceExecutor dataSourceExecutor;

    public List<User> getAllUsers() {
        return dataSourceExecutor.execute(
            repository -> repository.findAll().stream()
                .map(user -> modelMapper.map(user, User.class))
                .toList(), userRepository);
    }
}
