package com.example.project.follow.model.service;

import com.example.project.follow.model.dto.UserIdDto;
import com.example.project.follow.model.entity.Follow;
import com.example.project.follow.model.entity.FollowWait;
import com.example.project.follow.model.repository.FollowRepository;
import com.example.project.follow.model.repository.FollowWaitRepository;
import com.example.project.user.model.dto.UserDto;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final FollowWaitRepository followWaitRepository;
    private final UserRepository userRepository;

    // 팔로우 대기 신청
    @Override
    public void insertFollowWait(UserIdDto following) {
        User currentUser = null;
        Optional<User> followingUser = userRepository.findById(following.getUserId());

        followWaitRepository.save(FollowWait.builder()
                .userId(currentUser)
                .following(followingUser.get())
                .build());
    }

    // 팔로우 수락
    @Override
    public void insertFollower(UserIdDto follower) {
        User currentUser = null;
        Optional<User> followerUser = userRepository.findById(follower.getUserId());

        // 대기 상태에서 삭제
        followWaitRepository.deleteByFollowingAndUserId(currentUser.getUserId(), followerUser.get().getUserId());

//        User following = userRepository.getOne(userDto.getUserId());

        // follow table에 insert (팔로우 수락)
        followRepository.save(Follow.builder()
                .following(currentUser)
                .follower(followerUser.get())
                .build());

    }

    // 팔로우 대기 목록
    @Override
    public List<UserDto> selectAllFollowWait() {

        Long currentUserId = (long)0;
        List<FollowWait> followWaitList = followWaitRepository.findByFollowing(currentUserId);
        List<UserDto> followWaitUserDtoList = new ArrayList<>();
        for(FollowWait follow : followWaitList){
            User user = follow.getUserId();
            followWaitUserDtoList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .profileImg(user.getProfileImg())
                    .nickname(user.getNickname())
                    .build());
        }

        return followWaitUserDtoList;
    }

    //내 팔로잉 목록
    @Override
    public List<UserDto> selectAllFollowing() {
        //내가 팔로워인 사람들 리스트 가져오기
        Long currentUserId = (long)0;
        List<Follow> followingList = followRepository.findByFollower(currentUserId);
        List<UserDto> followingUserDtoList = new ArrayList<>();

        for(Follow follow : followingList){
            User user = follow.getFollowing();
            followingUserDtoList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }

        return followingUserDtoList;
    }

    //내 팔로워 목록
    @Override
    public List<UserDto> selectAllFollower() {
        //내가 팔로잉에 있는 리스트
        Long currentUserId = (long)0;
        List<Follow> followingList = followRepository.findByFollowing(currentUserId);
        List<UserDto> followerUserDtoList = new ArrayList<>();
        for(Follow follow : followingList){
            User user = follow.getFollowing();
            followerUserDtoList.add(UserDto.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .profileImg(user.getProfileImg())
                    .build());
        }


        return followerUserDtoList;
    }

    //팔로우 거절(대기 상태 삭제)
    @Override
    public void deleteFollowWait(UserDto wait) {
        long currentUserId = (long)0; //나
        long followingWaitUser = wait.getUserId(); //나에게 신청을 한사람
        followWaitRepository.deleteByFollowingAndUserId(currentUserId, followingWaitUser);
    }

    @Override
    public void deleteFollowing(UserDto follow) {
        //언팔로우 하기
        //내가 follower 지우려는 상대가 following
        long currentUserId = (long)0; //나
        long following = follow.getUserId();
        followRepository.deleteByFollowingAndFollower(following, currentUserId);
    }

    @Override
    public void deleteFollower(UserDto follow) {
        //팔로워 삭제하기
        //내가 following 지우려는 상대가 follower
        long currentUserId = (long)0; //나
        long follower = follow.getUserId();
        followRepository.deleteByFollowingAndFollower(follower, currentUserId);

    }
}

