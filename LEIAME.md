# BasicTokenEncryptor

Uma classe Java para geração, criptografia e verificação de tokens seguros com base em dados sensíveis (como nome de usuário e senha), utilizando a biblioteca [Jasypt](http://www.jasypt.org/).

## ✨ Funcionalidades

- Criação de tokens criptografados contendo nickname, senha e data.
- Verificação de tokens baseando-se em dados esperados e validade temporal (em horas).
- Suporte a separadores customizados na criação do token.
- Criptografia e descriptografia utilizando o algoritmo `PBEWithMD5AndDES`.

## 🔧 Requisitos

- Java 8 ou superior
- Biblioteca [Jasypt](https://mvnrepository.com/artifact/org.jasypt/jasypt)

## 🧪 Métodos Principais

| Método                                                   | Descrição                                                                        |
| -------------------------------------------------------- | -------------------------------------------------------------------------------- |
| `encrypt(String token)`                                  | Criptografa um valor em texto plano.                                             |
| `decrypt(String token)`                                  | Descriptografa um valor criptografado.                                           |
| `newToken(String nick, String pass, LocalDateTime date)` | Cria um token criptografado contendo nickname, senha e data.                     |
| `verifyEncriptedToken(...)`                              | Verifica se o token criptografado é válido com base no tempo e dados fornecidos. |
| `setPassword(String password)`                           | Altera a senha do criptografador.                                                |
| `setTimeInHours(int hours)`                              | Define o tempo de expiração para os tokens.                                      |

### Dependência Maven

```xml
<dependency>
    <groupId>org.jasypt</groupId>
    <artifactId>jasypt</artifactId>
    <version>1.9.3</version>
</dependency>
```
🚀 Exemplo de Uso
```java
BasicTokenEncryptor encryptor = new BasicTokenEncryptor("senhaSegura", 2);

// Gerando nickname e senha criptografados
String nickname = encryptor.encrypt("usuario123");
String password = encryptor.encrypt("senha@123");

// Criando token
String token = encryptor.newToken(nickname, password, LocalDateTime.now());

// Verificando token dentro de 2 horas
boolean valido = encryptor.verifyEncriptedToken(token, nickname, password);
```
## ⚠️ Observações
O formato padrão do token é: nickname+senha+data.

A verificação considera tokens expirados com base no número de horas fornecido.

Tokens inválidos ou manipulados lançarão exceções na descriptografia.