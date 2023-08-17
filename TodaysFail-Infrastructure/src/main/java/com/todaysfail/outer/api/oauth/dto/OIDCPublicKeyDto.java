package com.todaysfail.outer.api.oauth.dto;

public record OIDCPublicKeyDto(String kid, String alg, String use, String n, String e) {}
