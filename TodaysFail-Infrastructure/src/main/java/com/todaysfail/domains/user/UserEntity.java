package com.todaysfail.domains.user;

import com.todaysfail.common.BaseTimeEntity;
import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.domain.OauthInfoVo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    private String oid;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.NORMAL;

    @Enumerated(EnumType.STRING)
    private AccountRole accountRole = AccountRole.USER;

    @Builder
    private UserEntity(
            String name,
            OauthProvider provider,
            String oid,
            AccountStatus accountStatus,
            AccountRole accountRole) {
        this.name = name;
        this.provider = provider;
        this.oid = oid;
        this.accountStatus = accountStatus;
        this.accountRole = accountRole;
    }

    public User toDomain() {
        return User.of(
                id,
                Profile.builder().name(name).build(),
                OauthInfoVo.builder().provider(provider).oid(oid).build(),
                accountStatus,
                accountRole);
    }
}
