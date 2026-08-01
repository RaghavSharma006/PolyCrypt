# 🔐 PolyCrypt

> **One Ciphertext. Multiple Secrets. Infinite Possibilities.**

PolyCrypt is an experimental multi-key encryption system that enables multiple meaningful messages to coexist inside a single ciphertext. Each recipient decrypts only their intended message using a unique secret key, powered by polynomial interpolation.

---

## ✨ Features

- 🔑 Multiple secret keys
- 💬 Multiple meaningful messages
- 📦 Single ciphertext
- 📐 Polynomial interpolation based encryption
- 🔒 Key-specific decryption
- 🌐 Modern web interface
- ⚡ Responsive cyberpunk-inspired UI

---

## 🛠️ Tech Stack

### Backend
- Java 21
- Spring Boot
- Thymeleaf

### Frontend
- HTML
- CSS
- JavaScript

---

## 👨‍💻 Development

### Backend
Designed, implemented, and optimized by **Raghav Sharma**.

### Frontend
Designed with **AI-assisted development** and integrated into the Spring Boot backend by **Raghav Sharma**.

---

## 📖 How It Works

```
Message + Secret Key
        │
        ▼
Generate (x, y) Points
        │
        ▼
Polynomial Interpolation
        │
        ▼
Single Ciphertext
        │
        ▼
Decrypt using Matching Secret Key
```

Each secret key evaluates the polynomial at a unique point, revealing only its corresponding message.

---

## 🚀 Run Locally

```bash
git clone https://github.com/<your-username>/PolyCrypt.git
cd PolyCrypt
./mvnw spring-boot:run
```

Open:

```
http://localhost:8080
```

---

## ⚠️ Disclaimer

PolyCrypt is an experimental cryptographic project developed for research, education, and demonstration purposes. It has not undergone professional cryptographic review and should not be used to secure sensitive production data.

---

## ⭐ Support

If you found this project interesting, consider giving it a ⭐ on GitHub!
