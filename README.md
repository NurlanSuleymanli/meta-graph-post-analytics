# 📊 Meta Graph API — Instagram Post Analitika Paneli

Instagram hesabının post performansını **Meta Graph API** vasitəsilə real vaxtda analiz edən Spring Boot veb tətbiqi.

🌐 **Canlı Demo:** [meta-graph-post-analytics-production.up.railway.app](https://meta-graph-post-analytics-production.up.railway.app/)

---

## 🚀 Tətbiq Haqqında

Bu layihə Meta (Facebook) Graph API-nə qoşularaq Instagram hesabının son 20 postunu əldə edir və aşağıdakı analitika nəticələrini istifadəçiyə təqdim edir:

- 🏆 Ən yüksək engagement almış **Top 3 post**
- 📊 Həftənin günlərinə görə **like paylanması**
- 🕐 Günün saatlarına görə **like aktivliyi** 
- 📝 Bütün analizlərə əsaslanan **dinamik xülasə mətni**

---

## 🛠️ İstifadə Edilən Texnologiyalar

| Texnologiya | Məqsəd |
|---|---|
| **Java 21** | Əsas proqramlaşdırma dili |
| **Spring Boot 4.1.0** | Veb framework |
| **Spring Cloud OpenFeign** | Meta Graph API ilə HTTP əlaqəsi |
| **Spring MVC + Thymeleaf** | Server-side rendering, UI şablonları |
| **Lombok** | Boilerplate kodun azaldılması |
| **dotenv-java** | `.env` faylından mühit dəyişənlərinin oxunması |
| **Chart.js** | Interaktiv qrafiklər (CDN) |
| **Gradle** | Build aləti |

---

## ⚙️ Quraşdırma və İşə Salma

### Tələblər

- Java 21+
- Gradle (və ya layihədəki `gradlew` wrapper istifadə edilə bilər)
- Meta Developer hesabı və Instagram Business/Creator hesabı

### 1. Layihəni klonla

```bash
git clone https://github.com/NurlanSuleymanli/meta-graph-API.git
cd meta-graph-API
```

### 2. `.env` faylını yarat

Layihənin kök qovluğunda `.env.example` faylı mövcuddur. Onu kopyalayaraq `.env` faylı yarat:

```bash
cp .env.example .env
```

Sonra `.env` faylını öz məlumatlarınla doldur:

```env
META_APP_ID=your_meta_app_id
META_APP_SECRET=your_meta_app_secret
ACCESS_TOKEN=your_instagram_access_token
INSTAGRAM_USER_ID=your_instagram_user_id
```

### 3. Tətbiqi işə sal

```bash
./gradlew bootRun
```

Tətbiq işə düşdükdən sonra brauzerini aç və daxil ol:

```
http://localhost:8080
```

---

## 🔐 Mühit Dəyişənlərinin Qorunması

Həssas məlumatlar (token, şifrə, ID-lər) birbaşa kod içinə yazılmır. Əvəzinə:

1. Bütün məxfi dəyişənlər `.env` faylında saxlanılır
2. `dotenv-java` kitabxanası tətbiq başlayanda `.env` faylını oxuyur və sistem dəyişənlərinə yükləyir
3. `application.yaml` bu dəyişənlərə `${DEYISHEN_ADI}` sintaksisi ilə istinad edir:

```yaml
instagram:
  access-token: ${ACCESS_TOKEN}
  user-id: ${INSTAGRAM_USER_ID}
```

4. `.env` faylı `.gitignore`-a əlavə edilib — GitHub-a yüklənmir

### `.env.example` faylı nədir?

`.env.example` — hansı dəyişənlərin lazım olduğunu göstərən şablondur. İçi boşdur, dəyər yoxdur. Layihəni klonlayan hər kəs bu faylı görür və `.env` faylını özü doldurur.

---

## 📡 API Endpointləri

| Metod | URL | Təsvir |
|---|---|---|
| `GET` | `/` | Analitika panelini göstərir (Thymeleaf UI) |
| `GET` | `/posts/analyze` | JSON formatında analiz nəticəsini qaytarır |

---

## 📁 Layihənin Strukturu

```
meta-graph-API/
├── src/
│   └── main/
│       ├── java/com/nurlansuleymanli/metagraphapi/
│       │   ├── client/          # Feign client (Meta Graph API ilə əlaqə)
│       │   ├── controller/      # HTTP endpoint-lər
│       │   ├── dto/             # Data Transfer Object-lər
│       │   ├── service/         # Biznes məntiqi və analiz
│       │   └── MetaGraphApiApplication.java
│       └── resources/
│           ├── templates/       # Thymeleaf HTML şablonları
│           └── application.yaml
├── .env             # Məxfi dəyişənlər (Git-ə push edilmir)
├── .env.example     # Şablon — hansı dəyişənlərin lazım olduğu 
├── .gitignore
├── build.gradle
└── README.md
```


## ☁️ Deploy

Bu layihə **Railway** platformasında deploy edilib. Canlı tətbiqə aşağıdakı linkdən daxil olmaq mümkündür:

🔗 [https://meta-graph-post-analytics-production.up.railway.app](https://meta-graph-post-analytics-production.up.railway.app/)

### Mühit Dəyişənləri (Railway Variables)

Railway platforması `.env` faylını oxuya bilmir. Buna görə həssas məlumatlar Railway-in **Variables** panelindən əlavə edilib:

| Dəyişən | Təsvir |
|---|---|
| `ACCESS_TOKEN` | Instagram API access token |
| `INSTAGRAM_USER_ID` | Instagram hesabının User ID-si |
| `META_APP_ID` | Meta Developer App ID |
| `META_APP_SECRET` | Meta Developer App Secret |

---

## 👤 Müəllif

**Nurlan Süleymanlı**
- GitHub: [@NurlanSuleymanli](https://github.com/NurlanSuleymanli)
