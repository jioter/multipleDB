package com.multipledb.service;

import static com.multipledb.configs.datasource.DataSourceEnum.DATASOURCE_ONE;

import com.multipledb.configs.datasource.DataSourceProps;
import com.multipledb.configs.datasource.DataSourceContextHolder;
import com.multipledb.dto.UserDto;
import com.multipledb.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final DataSourceProps dataSources;
    private final DataSourceContextHolder dataSourceContextHolder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDto> getAllUsers() {
        dataSourceContextHolder.setBranchContext(DATASOURCE_ONE);

        List<UserDto> users = new ArrayList<>();
        try {
            return userRepository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .toList();
        } catch (RuntimeException ex) {
            throw new RuntimeException("Could not get all users" + ex.getMessage()); // Handle errors appropriately
        }
/*        for (int i = 0; i < dataSources.getDataSources().size(); i++) {
            DataSourceVariables props = dataSources.getDataSources().get(i);

            String query = String.format("SELECT %s AS id, %s AS username, %s AS name, %s AS surname FROM %s",
                props.mapping().id(),
                props.mapping().username(),
                props.mapping().name(),
                props.mapping().surname(),
                props.table());

            try (Connection conn = ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    UserDto user = new UserDto(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("surname")
                    );
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle errors appropriately
            }
        }*/

    }
}
