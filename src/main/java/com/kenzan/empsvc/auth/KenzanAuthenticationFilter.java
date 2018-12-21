package com.kenzan.empsvc.auth;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.util.StringUtils;

/**
 * I am choosing not to use BASIC and DIGEST authentication capabilities from Spring in this sample.
 * BASIC and DIGEST authentication is antiquated (MD5 is not secure, and both BASIC and DIGEST
 * require the use of a shared secret from a client that is almost always insecure.
 * <p>
 * Instead, I am using a simple filter that requires a token from the header 'Authorization'. In a
 * full implementation, this token would be a signed JWT given to the user by an Authorization Server.
 * <p>
 * This sample is not a complete AAA implementation. I have provided a skeleton of how a
 * JWT could be used to authenticate and authorize a user. For purposes of this sample, this skeleton
 * simply checks that a token is provided and it has the value 'kenzan'.
 */
public class KenzanAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if( request.getMethod().equalsIgnoreCase("DELETE" )) {
            String jwt = request.getHeader("Authorization");
            if (!StringUtils.hasText(jwt) || !validateToken(jwt)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean validateToken(String token) {
        /**
         * In a full fledged solution, a JWT would have been provided to the user from an
         * Authorization server (OAuth RFC 6749, 1.1 Roles). The JWT provided to the user
         * would have been digitally signed with the users' private key.
         *
         * This method would retrieve the user's public key and validate that the JWT's signature
         * is valid. The code would looks something like:
         *
         * //(Using io.jsonwebtoken.*;) A nice, fluent JWT library
         *
         * // Get public key of user...
         *  PubKey pubKey = getPublicKey(user_id);
         *
         * // Create SigningKeyResolver that will validate the token
         *  SigningKeyResolverAdapter customSigningKeyResolver = getResolver(pubKey);
         *
         * // Validate the JWT
         * Jwts.parser()
         *   .setSigningKeyResolver(customSigningKeyResolver)
         *   .parseClaimsJws(token);
         *
         *    return true;
         */

        //Since we are not implementing an Authorization service, we will simply validate that the user
        //used an agreed upon value for our token
        return token.equals("kenzan") ? true : false;

    }

}
