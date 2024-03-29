package com.cosmos.userservice.service.impl;

import com.cosmos.userservice.dto.MailDto;
import com.cosmos.userservice.dto.UserDto;
import com.cosmos.userservice.exception.exceptions.BadRequestException;
import com.cosmos.userservice.exception.exceptions.NotFoundException;
import com.cosmos.userservice.jpa.UserRepository;
import com.cosmos.userservice.jpa.entity.UserEntity;
import com.cosmos.userservice.mapper.UserMapper;
import com.cosmos.userservice.service.UserCheckService;
import com.cosmos.userservice.service.UserService;
import com.cosmos.userservice.util.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailUtil mailUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserCheckService userCheckService;


    @Override
    public void findId(String userEmail) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail);

        if (userEntity == null) {
            throw new NotFoundException("회원 정보가 없습니다. 이메일을 다시 확인해주세요.");
        }

        UserDto userDto = UserMapper.INSTANCE.userEntityToUserDto(userEntity);

        MailDto mailDto = new MailDto();
        mailDto.setTo(userDto.getUserEmail());
        mailDto.setSubject("[COSMOS] : 아이디 찾기");
        mailDto.setText("회원님의 아이디는 [ " + userDto.getUserId() + " ] 입니다.");

        mailUtil.sendMail(mailDto);
    }

    @Override
    public void findPassword(String userId, String userEmail) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail);

        if (userEntity == null) {
            throw new NotFoundException("회원 정보가 없습니다. 이메일을 다시 확인해주세요.");
        }

        if (!userEntity.getUserId().equals(userId)) {
            throw new NotFoundException("아이디와 이메일의 정보가 맞지 않습니다. 다시 확인해주세요.");
        }

        UserDto userDto = UserMapper.INSTANCE.userEntityToUserDto(userEntity);

        String result = UUID.randomUUID().toString().substring(0,8);

        userDto.setUserPassword(passwordEncoder.encode(result));

        userEntity = UserMapper.INSTANCE.userDtoToUserEntity(userDto);

        userRepository.save(userEntity);

        MailDto mailDto = new MailDto();
        mailDto.setTo(userDto.getUserEmail());
        mailDto.setSubject("[COSMOS] : 임시 비밀번호");
        mailDto.setText("회원님의 임시 비밀번호는 [ " + result + " ] 입니다. 로그인 후 비밀번호를 변경해주세요.");

        mailUtil.sendMail(mailDto);
    }


    @Override
    public UserDto getUserInfoByUserId(String userId) throws Exception {
        log.info(this.getClass().getName() + ".getUserInfoByUserId Start!");
        Optional<UserEntity> userEntityOptional = userRepository.findByUserId(userId);

        if (userEntityOptional.isPresent()) { // 사용자 정보가 있는 지 확인
            // 사용자 정보가 있을 경우 Entity --> Dto 로 변환 후 return
            UserDto userDto = UserMapper.INSTANCE.userEntityToUserDto(userEntityOptional.get());
            log.info(this.getClass().getName() + ".getUserInfoByUserId End!");
            return userDto;
        }

        throw new NotFoundException("사용자 정보를 찾을 수 없습니다"); // 정보가 없을 경우 404 Not Found

    }

    @Override
    public void createUser(UserDto userDto) {

        log.info("### UserServiceImpl createUser Start!!");

        if (!userCheckService.existsByUserId(userDto.getUserId())) {
            log.info("### UserServiceImpl createUser 아이디 중복");
            throw new BadRequestException("아이디가 중복되었습니다.");
        }
        if (!userCheckService.existsByUserEmail(userDto.getUserEmail())) {
            log.info("### UserServiceImpl createUser 이메일 중복");
            throw new BadRequestException("이메일이 중복되었습니다");
        }

        log.info("### UserServiceImpl 저장 start!!");
        userDto.setUserStatus(1);
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));

        UserEntity userEntity = UserMapper.INSTANCE.userDtoToUserEntity(userDto);


        userRepository.save(userEntity);

        log.info("### Success!! UserServiceImpl createUser!!");
    }

}