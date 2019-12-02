package com.dsm.data.mapper

import com.dsm.data.remote.entity.TokenEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Token

class TokenMapper : Mapper<TokenEntity, Token> {
    override fun mapFrom(from: TokenEntity): Token = Token(
        accessToken = from.accessToken,
        nick = from.nick,
        refreshToken = from.refreshToken
    )

}