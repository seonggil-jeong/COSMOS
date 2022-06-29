package com.cosmos.userservice.service.impl;

import com.cosmos.userservice.dto.MailDto;
import com.cosmos.userservice.dto.UserDto;
import com.cosmos.userservice.exception.exceptions.NotFoundException;
import com.cosmos.userservice.jpa.UserRepository;
import com.cosmos.userservice.jpa.entity.UserEntity;
import com.cosmos.userservice.mapper.UserMapper;
import com.cosmos.userservice.service.UserService;
import com.cosmos.userservice.util.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailUtil mailUtil;
    private final BCryptPasswordEncoder passwordEncoder;


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

        String result = changePassword();

        userDto.setUserPassword(passwordEncoder.encode(result));

        userEntity = UserMapper.INSTANCE.userDtoToUserEntity(userDto);

        userRepository.save(userEntity);

        MailDto mailDto = new MailDto();
        mailDto.setTo(userDto.getUserEmail());
        mailDto.setSubject("[COSMOS] : 임시 비밀번호");
        mailDto.setText("회원님의 임시 비밀번호는 [ " + result + " ] 입니다. 로그인 후 비밀번호를 변경해주세요.");

        mailUtil.sendMail(mailDto);
    }

    public static String changePassword() {
        String result = "";
        int idx = 0;

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            result += charSet[idx];
        }

        return result;
    }

}
