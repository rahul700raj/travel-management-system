# 🤝 Contributing to Travel Management System

Thank you for considering contributing to the Travel Management System! We welcome contributions from everyone.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Development Setup](#development-setup)
- [Coding Standards](#coding-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)

---

## 📜 Code of Conduct

- Be respectful and inclusive
- Welcome newcomers and help them learn
- Focus on constructive feedback
- Respect differing viewpoints and experiences

---

## 🎯 How Can I Contribute?

### 1. Reporting Bugs

Before creating bug reports, please check existing issues. When creating a bug report, include:

- **Clear title and description**
- **Steps to reproduce**
- **Expected vs actual behavior**
- **Screenshots** (if applicable)
- **Environment details** (OS, Java version, MySQL version)

**Example:**
```
Title: Booking creation fails with null pointer exception

Description:
When creating a booking with 0 people, the application throws NullPointerException.

Steps to Reproduce:
1. POST /api/bookings with numberOfPeople = 0
2. Observe error response

Expected: Validation error message
Actual: 500 Internal Server Error

Environment:
- OS: Windows 11
- Java: 17.0.2
- MySQL: 8.0.32
```

### 2. Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. Include:

- **Clear title and description**
- **Use case** - Why is this enhancement needed?
- **Proposed solution**
- **Alternative solutions** considered

**Example:**
```
Title: Add email notification for booking confirmation

Description:
Users should receive email confirmation when booking is confirmed.

Use Case:
Customers need immediate confirmation of their bookings via email.

Proposed Solution:
- Integrate Spring Mail
- Send email on booking status change to CONFIRMED
- Include booking details and payment information
```

### 3. Code Contributions

- Fix bugs
- Add new features
- Improve documentation
- Optimize performance
- Add tests

---

## 🛠️ Development Setup

### 1. Fork and Clone

```bash
# Fork the repository on GitHub
# Then clone your fork
git clone https://github.com/YOUR_USERNAME/travel-management-system.git
cd travel-management-system
```

### 2. Create a Branch

```bash
git checkout -b feature/your-feature-name
# or
git checkout -b fix/bug-description
```

### 3. Setup Development Environment

```bash
# Install dependencies
mvn clean install

# Run tests
mvn test

# Run application
mvn spring-boot:run
```

---

## 📝 Coding Standards

### Java Code Style

- Follow **Java naming conventions**
- Use **meaningful variable names**
- Add **JavaDoc** for public methods
- Keep methods **small and focused**
- Use **Lombok** annotations where appropriate

**Example:**

```java
/**
 * Creates a new booking for a travel package.
 * 
 * @param bookingDTO the booking details
 * @return created booking with calculated total amount
 * @throws RuntimeException if user or package not found
 */
@Transactional
public BookingDTO createBooking(BookingDTO bookingDTO) {
    // Implementation
}
```

### Code Organization

- **Controllers**: Handle HTTP requests/responses only
- **Services**: Contain business logic
- **Repositories**: Database operations
- **DTOs**: Data transfer between layers
- **Entities**: Database models

### Validation

- Use **Bean Validation** annotations
- Validate in DTOs, not entities
- Provide clear error messages

```java
@NotBlank(message = "Name is required")
private String name;

@Email(message = "Invalid email format")
private String email;
```

### Exception Handling

- Use **meaningful exception messages**
- Handle exceptions in **GlobalExceptionHandler**
- Return appropriate **HTTP status codes**

---

## 📦 Commit Guidelines

### Commit Message Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- **feat**: New feature
- **fix**: Bug fix
- **docs**: Documentation changes
- **style**: Code style changes (formatting)
- **refactor**: Code refactoring
- **test**: Adding tests
- **chore**: Maintenance tasks

### Examples

```bash
feat(booking): add email notification on confirmation

- Integrate Spring Mail
- Send email when booking status changes to CONFIRMED
- Include booking details in email template

Closes #123
```

```bash
fix(user): resolve null pointer exception in user creation

- Add null check for email field
- Add validation for required fields
- Update error messages

Fixes #456
```

---

## 🔄 Pull Request Process

### 1. Before Submitting

- [ ] Code follows project style guidelines
- [ ] Self-review of code completed
- [ ] Comments added for complex logic
- [ ] Documentation updated (if needed)
- [ ] Tests added/updated
- [ ] All tests pass locally
- [ ] No merge conflicts

### 2. Submitting PR

1. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Create Pull Request** on GitHub

3. **Fill PR template** with:
   - Description of changes
   - Related issue number
   - Type of change (bug fix, feature, etc.)
   - Testing done
   - Screenshots (if UI changes)

### 3. PR Template

```markdown
## Description
Brief description of changes

## Related Issue
Closes #123

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing completed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] Tests pass locally
```

### 4. Review Process

- Maintainers will review your PR
- Address review comments
- Make requested changes
- Push updates to same branch
- PR will be merged once approved

---

## 🧪 Testing Guidelines

### Unit Tests

```java
@Test
void testCreateUser_Success() {
    // Given
    UserDTO userDTO = new UserDTO();
    userDTO.setName("Test User");
    userDTO.setEmail("test@example.com");
    
    // When
    UserDTO result = userService.createUser(userDTO);
    
    // Then
    assertNotNull(result.getId());
    assertEquals("Test User", result.getName());
}
```

### Integration Tests

```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testCreateUser() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test\",\"email\":\"test@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test"));
    }
}
```

---

## 📚 Documentation

### Code Documentation

- Add JavaDoc for public methods
- Explain complex logic with comments
- Update README for new features

### API Documentation

- Update Swagger annotations
- Add examples in API_EXAMPLES.md
- Document new endpoints

---

## 🎨 Feature Development Workflow

1. **Discuss** - Open an issue to discuss the feature
2. **Design** - Plan the implementation
3. **Develop** - Write code following guidelines
4. **Test** - Add comprehensive tests
5. **Document** - Update documentation
6. **Submit** - Create pull request
7. **Review** - Address feedback
8. **Merge** - Feature gets merged

---

## 🐛 Bug Fix Workflow

1. **Reproduce** - Confirm the bug exists
2. **Identify** - Find root cause
3. **Fix** - Implement solution
4. **Test** - Verify fix works
5. **Prevent** - Add tests to prevent regression
6. **Submit** - Create pull request

---

## 💡 Tips for Contributors

- **Start small** - Begin with documentation or small bug fixes
- **Ask questions** - Don't hesitate to ask for help
- **Be patient** - Reviews may take time
- **Stay updated** - Pull latest changes regularly
- **Have fun** - Enjoy contributing!

---

## 📞 Getting Help

- **GitHub Issues** - For bugs and features
- **Discussions** - For questions and ideas
- **Email** - rm2778643@gmail.com

---

## 🏆 Recognition

Contributors will be:
- Listed in README.md
- Mentioned in release notes
- Appreciated in the community

---

**Thank you for contributing! 🎉**

Your contributions make this project better for everyone.
