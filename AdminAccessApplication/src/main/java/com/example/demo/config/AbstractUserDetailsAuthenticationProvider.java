package com.example.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

public abstract class AbstractUserDetailsAuthenticationProvider
    implements AuthenticationProvider, InitializingBean,
    MessageSourceAware {

    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        public void check(UserDetails user) {
          if (!user.isAccountNonLocked())
              System.out.println("User account locked");
          
          throw new LockedException("sdfds");
          
              
          }
           //...
        }
    }
