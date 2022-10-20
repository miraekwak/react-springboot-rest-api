package com.example.backend.repository;

import com.example.backend.model.Email;
import com.example.backend.model.Member;
import com.example.backend.model.OrderStatus;
import com.example.backend.model.Role;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.backend.repository.JdbcUtils.toLocalDateTime;
import static com.example.backend.repository.JdbcUtils.toUUID;

@Repository
public class MemberJdbcRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MemberJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Member save(Member member) {
        var insert = jdbcTemplate.update("INSERT INTO member (member_id, role, name, username, passwd)" +
                "VALUES(UNHEX(REPLACE(:memberId, '-','')),:role, :name, :username, :passwd)", toParamMap(member));
        if(insert != 1) {
            throw new RuntimeException("Nothing was inserted!");
        }
        return member;
    }

    private Map<String, Object> toParamMap(Member member) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("memberId", member.getMemberId().toString().getBytes());
        paramMap.put("role", member.getRole().toString());
        paramMap.put("name", member.getName());
        paramMap.put("username", member.getUsername());
        paramMap.put("passwd", member.getPasswd());
        return paramMap;
    }

    @Override
    public Optional<Member> findByUsernameAndPasswd(String username, String passwd) {
        try{
            var member = jdbcTemplate.queryForObject("SELECT * FROM member WHERE username = :username AND passwd = :passwd",
                    Map.of("username", username, "passwd", passwd), memberDtoRowMapper);
            return Optional.of(
                    memberDtoToMember(member)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private Member memberDtoToMember(MemberDto member) {
        return new Member(member.memberId(), member.name(), member.role(), member.username(), member.passwd());
    }

    private static final RowMapper<MemberDto> memberDtoRowMapper = (resultSet, i) -> {
        var memberId = toUUID(resultSet.getBytes("member_id"));
        var role = Role.valueOf(resultSet.getString("role"));
        var name = resultSet.getString("name");
        var username = resultSet.getString("username");
        var passwd = resultSet.getString("passwd");
        return new MemberDto(memberId, name, role, username, passwd);
    };
}
