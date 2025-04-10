
# SpringTalks

Este é um projeto de chat em tempo real desenvolvido com **Spring Boot** e **WebSocket**, com front-end em HTML + Bootstrap. A aplicação permite que usuários se conectem a salas de bate-papo privadas ou públicas, trocando mensagens em tempo real.

💬 Os usuários acessam o sistema fornecendo apenas o **email**, e então visualizam as salas das quais participam (privadas) e as salas públicas disponíveis. Ao clicar em uma sala, a conexão é automaticamente estabelecida para troca de mensagens em tempo real.

> ⚠️ **Cadastro de usuários e salas no momento é realizado via Swagger ou ferramentas como Insomnia/Postman.** Ainda não há formulário de criação via front-end.

---

## 🛠️ Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring WebSocket  
- Spring Data JPA  
- Swagger OpenAPI (Documentação e Testes de API)  
- HTML + Bootstrap  
- JavaScript (STOMP + SockJS)  
- PostgreSQL  
- Docker  
- IntelliJ IDEA  

---

## 🚀 Como Executar o Projeto

Clone o repositório:

```bash
git clone https://github.com/diegoliveiraa/springtalks.git
```

Acesse o diretório:

```bash
cd springtalks
```

Suba o banco PostgreSQL com Docker:

```bash
docker-compose up -d
```

Execute o projeto com Spring Boot na sua IDE ou via terminal:

```bash
./mvnw spring-boot:run
```

Acesse o front-end via navegador:

```
http://localhost:8080/chat.html
```

Acesse a documentação da API via Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## 📌 Funcionalidades

- Criação de usuários e salas via Swagger ou ferramentas de API  
- Conexão a salas por email (login simplificado)  
- Diferenciação de salas públicas e privadas  
- Envio e recebimento de mensagens em tempo real via WebSocket  
- Histórico de mensagens exibido ao entrar na sala  
- Interface simples e responsiva com Bootstrap  

---

## 🧪 Melhorias Futuras

- Interface web para cadastro de usuários e salas  
- Tratamento de exceções com handlers personalizados  
- Implementação de autenticação com login e senha  
- Testes unitários com JUnit  
- Validações no back-end com mensagens personalizadas  
- Deploy do sistema em ambiente cloud (ex: Heroku ou AWS)

---

## 🌐 Projeto Open Source

Este projeto é **open source**, e você pode contribuir com melhorias, correções ou sugestões via [Pull Request](https://github.com/diegoliveiraa/springtalks/pulls).

---

## 👨‍💻 Autor

Desenvolvido por **Diego Oliveira**  
[LinkedIn](https://www.linkedin.com/in/diegoliveiraa/) | [GitHub](https://github.com/diegoliveiraa)
