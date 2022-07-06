package com.cosmos.userservice.service.impl;

import com.cosmos.userservice.dto.UserStackDto;
import com.cosmos.userservice.jpa.UserRepository;
import com.cosmos.userservice.jpa.UserStackRepository;
import com.cosmos.userservice.jpa.entity.UserEntity;
import com.cosmos.userservice.jpa.entity.UserStackEntity;
import com.cosmos.userservice.mapper.UserMapper;
import com.cosmos.userservice.mapper.UserStackMapper;
import com.cosmos.userservice.service.UserStackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserStackServiceImpl implements UserStackService {
    private final UserStackRepository userStackRepository;
    private final UserRepository userRepository;


    @Override
    public List<UserStackDto> getStackInfoByUserId(String userId) throws Exception {

        // Token 에서 ID 값을 가져오기 때문에 Null 발생 (X) --> 인증되지 않았다면 Status(401)
        log.debug(this.getClass().getName() + ".getStackInfoByUserId Start!");
        List<UserStackDto> userStackDtoList = new ArrayList<>();

        // 사용자 정보 조회 By Id
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);

        // UserStackEntityList 조회 By userEntity
        Optional<List<UserStackEntity>> userStackEntityList = userStackRepository.findAllByUser(userEntityOptional.get());

        if (userStackEntityList.isPresent()) {

            log.debug("list size : " + userStackEntityList.get().size());

            userStackEntityList.get().forEach(userStackEntity -> {

                userStackDtoList.add(UserStackMapper.INSTANCE.userStackEntityToUserStackDto(userStackEntity));
            });
        }

       log.debug(this.getClass().getName() + ".getStackInfoByUserId End!");


        return userStackDtoList;
    }


    @Override
    public int updateStackInfoByUserId(List<UserStackDto> userStackDtoList, String userId) throws Exception {
        log.debug(this.getClass().getName() + ".updateStackInfoByUserId Start!");

        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);

        // EntityList 초기화
        List<UserStackEntity> userStackEntityList = new ArrayList<>();

        // 기존에 Stack 정보 deleteAll 실행
        userStackRepository.deleteAllByUser(userEntityOptional.get());

        if (! userStackDtoList.isEmpty()) {

            // DtoToEntity 변환 작업
            userStackDtoList.forEach(userStackDto -> {

                // TODO: 2022-07-06 front에서 사용자 정보가 함께 넘어온다면 없어도 가능
                userStackDto.setUser(userEntityOptional.get());
                //
                userStackEntityList.add(UserStackMapper.INSTANCE.userStackDtoToUserStackEntity(userStackDto));

            });

            userStackRepository.saveAll(userStackEntityList);

            log.debug(this.getClass().getName() + ".updateStackInfoByUserId End!");

            return 1;
        }
        return 0;
    }
}
