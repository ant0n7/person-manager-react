package com.example.demo.domain.security;

import com.example.demo.domain.appuser.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Set;
import java.util.UUID;

/**
 * SecurityService - Performs custom authentication validation
 *
 * <ul>
 *     <li>To check if User is accessing own group by groupID</li>
 *     <li>To check if User is accessing own group by groupName</li>
 * </ul>
 *
 * @author Remo Aeberli
 */
@Component
public class SecurityService {
}
