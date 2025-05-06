# BasicTokenEncryptor

A Java class for generating, encrypting, and verifying secure tokens based on sensitive data (such as nickname and password), using the [Jasypt](http://www.jasypt.org/) library.

## ✨ Features

- Generates encrypted tokens containing nickname, password, and timestamp.
- Verifies tokens based on expected values and expiration time (in hours).
- Supports custom separators when generating tokens.
- Encrypts and decrypts using the `PBEWithMD5AndDES` algorithm.

## 🔧 Requirements

- Java 8 or higher
- [Jasypt](https://mvnrepository.com/artifact/org.jasypt/jasypt) library

### Maven Dependency

```xml
<dependency>
    <groupId>org.jasypt</groupId>
    <artifactId>jasypt</artifactId>
    <version>1.9.3</version>
</dependency>
```
## 🧪 Key Methods
| Method                                                   | Description                                                                          |
| -------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| `encrypt(String token)`                                  | Encrypts a plain text value.                                                         |
| `decrypt(String token)`                                  | Decrypts an encrypted value.                                                         |
| `newToken(String nick, String pass, LocalDateTime date)` | Creates an encrypted token with nickname, password, and date.                        |
| `verifyEncryptedToken(...)`                              | Verifies whether the encrypted token is valid based on provided data and time limit. |
| `setPassword(String password)`                           | Sets the encryption password.                                                        |
| `setTimeInHours(int hours)`                              | Sets the expiration time (in hours) for token validation.                            |



## 🚀 Usage Example
```java
BasicTokenEncryptor encryptor = new BasicTokenEncryptor("securePassword", 2);

// Encrypt nickname and password
String nickname = encryptor.encrypt("user123");
String password = encryptor.encrypt("pass@123");

// Generate token
String token = encryptor.newToken(nickname, password, LocalDateTime.now());

// Verify token within 2 hours
boolean isValid = encryptor.verifyEncryptedToken(token, nickname, password);
```
## ⚠️ Notes
The default token format is: nickname+password+timestamp.

Token expiration is checked based on the time difference in hours.

Invalid or tampered tokens may throw exceptions during decryption