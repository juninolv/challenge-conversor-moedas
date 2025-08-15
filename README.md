# Conversor de Moedas

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=Apache-Maven&logoColor=white)

**Conversor de Moedas** é uma aplicação CLI (Command Line Interface) desenvolvida em **Java com Spring Boot** para converter valores entre diferentes moedas do mundo, utilizando taxas de câmbio atualizadas.

---

## Sobre o Projeto

Este projeto foi criado como parte de um desafio técnico para demonstrar habilidades em:

- **Java e Spring Boot**
- **Consumo de APIs**
- **Tratamento de dados JSON**
- **Boas práticas de Código e Arquitetura**

A aplicação permite que o usuário:

- Converter valores entre moedas (ex: USD → BRL, EUR → JPY)
- Exibir histórico de conversões

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- **Maven 3.9.10**
- **Gson 2.13.1**

---

## Como Executar o Projeto

### Pré-requisitos
- [Java 21](https://adoptium.net/pt-BR/temurin/releases?version=21&os=any&arch=any)
- [Maven](hhttps://maven.apache.org/download.cgi)

### 1. Clonar o Repositório

```bash
git clone https://github.com/juninolv/challenge-conversor-moedas.git
cd challenge-conversor-moedas
```

### 2. Compilar e Executar

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

---

## Exemplos de Uso

### 1. Menu Principal

```
# HOME
#
# 1 - Converter
# 2 - Conversion History
# 0 - Exit
#
# -> 1
```

### 2. Converter Moedas

**Menu:**

```
# CURRENCY CONVERTER
#
# Type - [ARS, BOB, BRL, CLP, COP, USD, EUR, CAD, MXN, RUB, UAH, ZAR]
#
# 0 - Exit
```

**Valores:**

```
# Enter SOURCE -> uah
# Enter TARGET -> zar
# Set AMOUNT -> 112.89
```

**Resultado:**

```
# Fetching...

# Conversion: UAH -> ZAR
# Rate: 0.4234
# Result: 47.797626
```

### 3. Histórico de Conversões

**Menu:**

```
# CONVERSION HISTORY
#
# 0 - Exit
```

**Valores:**

```
# Set LIMIT -> 10
```

**Resultado:**

```
# Fetching...

# Index: #1
# Conversion: UAH -> ZAR
# Rate: 0.4234
# Result: 47.797626
# Date: 2025-08-15T01:48:18.622855700

# Index: #2
# Conversion: CLP -> EUR
# Rate: 8.9773E-4
# Result: 69.45018826
# Date: 2025-08-15T01:48:05.443591457
```

---

## Estrutura do Projeto

```
.
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── com.oracle.cli
        │       ├── dto
        │       │   └── Conversion.java
        │       ├── model
        │       │   ├── CurrencyType.java
        │       │   └── ScreenSelector.java
        │       ├── service
        │       │   ├── ConverterService.java
        │       │   └── ScreenService.java
        │       ├── util
        │       │   ├── config
        │       │   │   ├── ApplicationConfig.java
        │       │   │   └── ScreensConfig.java
        │       │   ├── exception
        │       │   │   └── ExitException.java
        │       │   └── Http.java
        │       ├── view
        │       │   ├── ConverterView.java
        │       │   ├── HistoryView.java
        │       │   ├── HomeView.java
        │       │   └── ScreenBase.java
        │       └── MainApplication.java
        └── resources
            ├── application-dev.properties
            └── application.properties
```

---

## Contato
**Juninho Oliveira** – [GitHub](https://github.com/juninolv) | [LinkedIn](https://www.linkedin.com/in/juninholv/)

---
**Star este repositório** se achou útil!
