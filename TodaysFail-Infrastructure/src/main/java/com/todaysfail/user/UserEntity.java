package com.todaysfail.user;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
import com.todaysfail.common.type.user.OauthProvider;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    private String oid;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.NORMAL;

    @Enumerated(EnumType.STRING)
    private AccountRole accountRole = AccountRole.USER;
}
