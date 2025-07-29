# Hands On With - Spring Security
Learning New Security Module in Spring 6 And Spring Boot 3, implementation of JWT Based Auth, RBAC, OIDC, OAuth2.0, KeyCloak and more.

Currently, the repo contains two main submodules:

1. **hands-on-with-spring-security-jwt**: Focuses on JWT-based security concepts and implementation.
2. **hands-on-with-spring-security-rbac**: Demonstrates RBAC security, typically integrating with In-memory Basic Auth.


## 1. JWT-Based Security (hands-on-with-spring-security-jwt)

### Core Concepts Illustrated

- **Stateless Authentication**: User authentication is performed using JWTs instead of session-based login.
- **Token Generation & Validation**: Upon successful authentication, a JWT is generated and returned to the client. Each subsequent client request must include this token, which the backend validates before granting access.
- **Custom Authentication Filter**: A filter intercepts HTTP requests, extracts the JWT, validates it, and sets user authentication context if valid.
- **Exception Handling**: Unauthorized or invalid token access is consistently handled, sending appropriate HTTP error responses.
- **User Details Service**: Loads user-specific data, typically from a database, to verify credentials and roles.
- **Security Configuration**: Configures endpoints as public or protected, sets up the authentication manager, and integrates custom filters.

### Typical Operations & Flow

- Users authenticate via a login endpoint, supplying credentials.
- On successful authentication, a JWT is issued.
- The client must add the JWT as a header on protected endpoint requests.
- The backend’s filter extracts and validates the token, rejecting requests with missing/invalid tokens.
- Upon valid token, user roles and permissions are restored from the token data.

### Key Classes & Responsibilities

- **Authentication Controller**: Exposes login endpoint and issues JWTs.
- **JWT Utility/Provider**: Handles JWT creation, parsing, and validation logic.
- **Security Config**: Configures the security filter chain, authentication providers, and endpoint access rules.
- **User Details & Service**: Loads user credentials and roles for authentication and token claims.

---

## 2. RBAC Security with In-memory Basic Auth (hands-on-with-spring-security-rbac)

### Core Concepts Illustrated

- **Role-Based Access Control**: Access to endpoints is governed by user roles rather than individual permissions.
- **External Identity Provider**: Integrates with Keycloak to delegate authentication and authorization.
- **OAuth2/OpenID Connect**: Uses standards-based authentication flows, typically leveraging bearer tokens from Keycloak.
- **Role Mapping & Enforcement**: Maps Keycloak roles to application roles and restricts endpoint access accordingly.
- **Custom Access Rules**: Configures method or URL-level security based on roles.
- **Session & Token Management**: Handles access tokens and refresh tokens, maintaining statelessness or using session as per requirements.

### Typical Operations & Flow

- Users are redirected to Keycloak for login.
- Upon successful login, Keycloak issues tokens the application uses to verify user identity and roles.
- Access to various endpoints is checked against user’s assigned roles.
- Admin and user-specific endpoints are protected with role-based rules.

### Key Classes & Responsibilities

- **Security Config**: Sets up OAuth2 resource server or client, configures role mappings and protected routes.
- **Controller Classes**: Annotated with security constraints (e.g., `@PreAuthorize` or `@Secured`) to enforce RBAC.
- **Keycloak Adapter/Integration Classes**: Manage token validation and role extraction from Keycloak-issued tokens.

---

## General Revision Points

- Both modules demonstrate real-world configurations and best practices for securing Spring Boot APIs.
- JWT module is ideal for stateless token-based security, while RBAC module shows enterprise-level integration with external identity providers.
- Consistent error handling, clear separation of concerns (controllers, services, security config), and extensibility are demonstrated throughout.
- Study the flow of authentication, token management, and role checks to understand how security is enforced at each step.