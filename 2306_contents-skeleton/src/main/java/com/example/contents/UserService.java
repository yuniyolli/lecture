package com.example.contents;

import com.example.contents.dto.UserDto;
import com.example.contents.entity.UserEntity;
import com.example.contents.exceptions.UserNotFoundException;
import com.example.contents.exceptions.UsernameExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    // createUser
    public UserDto createUser(UserDto dto) {
        //1.  이미 사용 중입니다.
        if (repository.existsByUsername(dto.getUsername()))
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        throw new UsernameExistException();
        //1. 회원가입 -> 프로필 이미지가 아직 필요없다..
        //생성과정
        UserEntity newUser= new UserEntity();
        newUser.setUsername(dto.getUsername());
        newUser.setPhone(dto.getPhone());
        newUser.setId(dto.getId());
        newUser.setEmail(dto.getEmail());

        return UserDto.fromEntity(repository.save(newUser));


        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // readUserByUsername
    public UserDto readUserByUsername(String username) {
        Optional<UserEntity> optionalUser
                = repository.findByUsername(username);

        if(optionalUser.isEmpty())
           //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            throw new UserNotFoundException();

        return UserDto.fromEntity(optionalUser.get());
        //
        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // updateUser
    public UserDto updateUser(Long id, UserDto dto) {
        //update user로 사용자 username은 변경할 수 없도록
        Optional<UserEntity> optionalUser
                = repository.findById(id);
        if (optionalUser.isEmpty())
           // throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            throw new UserNotFoundException();
        //기존의 user에 대한 정보를 가지고 오기
        UserEntity userEntity = optionalUser.get();
        //입력받은 값으로 정보 변경하기
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhone(dto.getPhone());
        userEntity.setBio(dto.getBio());

        return UserDto.fromEntity(repository.save(userEntity));


        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // updateUserAvatar
    public UserDto updateUserAvatar(Long id, MultipartFile avatarImage) {
        // 2. 사용자가 프로필 이미지를 업로드 한다. -> 수정, 풋메핑

        //1) 유저 존재 확인
        Optional<UserEntity> optionalUser
                =repository.findById(id);
        if (optionalUser.isEmpty())
        //    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            throw new UserNotFoundException();


        //media/filename.png
        //media/<업로드 시각>.png
        //2) 파일을 어디에 업로드 할건지
        //-> media경로에 저장 -> userID경로가 없으면 만들기 -> 거기에 profile.{파일확장자}
        //media/{userId}/profile.{파일 확장자}
        String profileDir = String.format("media/%d/", id);
        log.info(profileDir);
        try {
            //폴더만 만드는 과정
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //확장자를 포함한 이미지 이름 만들기 (profile.{확장자})
        String originalFilename = avatarImage.getOriginalFilename();
        //사용자가 보내준 파일 이름이 queue.png 일때 => fileNameSplit = {"queue", "png"}
        String[] fileNameSplit = originalFilename.split("\\."); //정규표현식
        //파일이름이 뭐든 확장자는 맨 마지막에 들어가겠지
        String extension = fileNameSplit[fileNameSplit.length -1];
        String profileFilename = "profile." + extension;
        log.info(profileFilename);

        // 2-3. 폴더와 파일 경로를 포함한 이름 만들기
        String profilePath = profileDir + profileFilename;
        log.info(profilePath);

        // 3. MultipartFile 을 저장하기
        try {
            avatarImage.transferTo(Path.of(profilePath));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //4. UserEntity 업데이트 (정적 프로필 이미지를 회수할 수 있는 URL)
        // http://localhost:8080/static/1/profile.png
        log.info(String.format("/static/%d/%s", id, profileFilename));

        //String path1 = "/static";
       // String path2 = "static";
        UserEntity userEntity = optionalUser.get();
        userEntity.setAvatar(String.format("/static/%d/%s", id, profileFilename));
        return UserDto.fromEntity(repository.save(userEntity));
        /*
        주의 path 1, 2 는 다른 주소!
        "String path1 = /static"
        "String path2 = static"

        github.com/edujeeho
        1. static
        2. /static
        각각 a링크에 들어가있다고 하면 그냥 github.com/ 에 있을때랑 /edujeeho랑 다름
        / 가 여기서부터 시작한다는 의미 그래서 /staticgkaus anwhrjs wO qnxj tlwkrgksmsrj!
         */
        //log.info("static/" + profileFilename);
        //log.info(String.format("/media/%d/" + profilePath, id));
       // log.info("/static" + profilePath); // http://localhost:8080/


        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
