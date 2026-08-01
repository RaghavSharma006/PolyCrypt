# 🔐 PolyCrypt

<div align="center">

### **One Ciphertext. Multiple Secrets. One Polynomial.**

**An experimental multi-key encryption system powered by polynomial interpolation.**

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-green)
![Status](https://img.shields.io/badge/Status-Experimental-red)

</div>

---

## 🚀 Overview

PolyCrypt is an experimental cryptographic system that allows **multiple meaningful messages** to be embedded into a **single ciphertext**.

Each message is associated with a unique secret key. During decryption, only the matching key reveals its corresponding message, while every other embedded secret remains inaccessible.

Instead of traditional one-message-one-ciphertext encryption, PolyCrypt explores a mathematical approach based on **Lagrange Polynomial Interpolation**.

---

## ✨ Features

- 🔑 Multiple secret keys
- 💬 Multiple meaningful messages
- 📦 Single ciphertext output
- 📐 Polynomial interpolation based encryption
- 🔒 Key-specific decryption
- 🌐 Interactive Spring Boot web application
- ⚡ Modern animated interface

---

# 🧠 Encryption Workflow

```
Message + Secret Key
        │
        ▼
Generate Coordinate Points (x,y)
        │
        ▼
Lagrange Polynomial Interpolation
        │
        ▼
Polynomial Serialization
        │
        ▼
Ciphertext
```

---

# 🔓 Decryption Workflow

```
Ciphertext
      │
      ▼
Deserialize Polynomial
      │
      ▼
Evaluate Polynomial at Secret Key
      │
      ▼
Recover Hidden Message
```

Only the correct secret key evaluates to the intended message.

---

# 🛠 Tech Stack

### Backend

- Java 21
- Spring Boot
- Thymeleaf

### Frontend

- HTML5
- CSS3
- JavaScript

### Mathematics

- Lagrange Polynomial Interpolation
- Polynomial Serialization
- Hash-based Coordinate Generation

---

# 🚀 Running Locally

Clone the repository

```bash
git clone https://github.com/<your-username>/PolyCrypt.git
```

Move into the project

```bash
cd PolyCrypt
```

Run

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

Open

```
http://localhost:8080
```

---

# 📸 Screenshots


- Home
  <img width="1917" height="822" alt="image" src="https://github.com/user-attachments/assets/df2dabcc-d7ad-4356-8cc6-1e02d21bec13" />

- Encrypt
  <img width="1912" height="913" alt="image" src="https://github.com/user-attachments/assets/d319c049-ee13-48e4-9baa-b7b4a06feb35" />

- Decrypt
  <img width="1891" height="870" alt="image" src="https://github.com/user-attachments/assets/f2642c5f-7c21-4d19-aedc-07a061873f47" />

- Learn
  <img width="1857" height="856" alt="image" src="https://github.com/user-attachments/assets/bd566e94-c45c-46b6-a72d-0ce1392133d1" />


---

# 📂 Project Structure

```
src
│
├── controller
├── Encrypt.java
├── Decrypt.java
├── Blocker.java
├── PolynomialGenerator.java
├── PolynomialSerializer.java
├── CryptoBridge.java
└── templates
```

---

# ⚠️ Current Limitations

The current implementation uses a **fixed interpolation block size of 20 points (`MAX = 20`)**.

Because every polynomial is generated from a fixed 20-point interpolation rectangle, **a single encryption block cannot represent more than 20 character positions**.

Supporting larger messages requires generating additional interpolation blocks and corresponding polynomials, which is planned for future versions.

---

# 👨‍💻 Development

### Backend

Designed and implemented entirely by **Raghav Sharma**.

### Frontend

Designed with **AI-assisted development** and fully integrated into the backend by **Raghav Sharma**.

---

# ⚠️ Disclaimer

PolyCrypt is an **experimental cryptographic project** developed for research, learning, and demonstration purposes.

It has **not** undergone professional cryptographic review and **should not** be used for protecting sensitive or production data.

---

# 📜 License

This project is released under the **MIT License**.

---

<div align="center">

### ⭐ If you found this project interesting, consider giving it a star!

Made with ❤️ by **Raghav Sharma** for loved ones!

</div>
