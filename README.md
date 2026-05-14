# Diet & Fitness Planner - Full Stack Web Application

## Architecture
- **Frontend:** Angular 18 (Standalone Components, Angular Material)
- **Backend:** Spring Boot 3.2 (Java 17)
- **Database:** MySQL
- **Security:** Spring Security + JWT Authentication
- **API:** RESTful architecture

---

## Project Structure

```
Diet and Fitness Planner/
├── database/
│   └── schema.sql              # MySQL schema + sample data
├── backend/                    # Spring Boot Application
│   ├── pom.xml
│   └── src/main/java/com/dietfitness/
│       ├── DietFitnessPlannerApplication.java
│       ├── config/
│       │   ├── SecurityConfig.java       # Spring Security + JWT config
│       │   └── DataInitializer.java      # Seeds roles on startup
│       ├── controller/
│       │   ├── AuthController.java       # /api/auth/** (public)
│       │   ├── UserController.java       # /api/user/** (authenticated)
│       │   └── AdminController.java      # /api/admin/** (admin only)
│       ├── dto/                          # Data Transfer Objects
│       │   ├── RegisterRequest.java
│       │   ├── LoginRequest.java
│       │   ├── JwtResponse.java
│       │   ├── UserProfileDto.java
│       │   ├── UpdateProfileRequest.java
│       │   ├── MealDto.java / MealRequest.java
│       │   ├── WorkoutDto.java / WorkoutRequest.java
│       │   ├── DashboardDto.java
│       │   ├── RewardDto.java
│       │   ├── WellnessTipsDto.java
│       │   ├── HistoryDto.java
│       │   └── ApiResponse.java
│       ├── entity/                       # JPA Entities
│       │   ├── User.java
│       │   ├── Role.java
│       │   ├── Meal.java
│       │   ├── Workout.java
│       │   ├── DailyProgress.java
│       │   ├── MealTracking.java
│       │   ├── WorkoutTracking.java
│       │   ├── Reward.java
│       │   └── Enums (DietPreference, WorkoutLevel, Goal, MealType)
│       ├── exception/
│       │   └── GlobalExceptionHandler.java
│       ├── repository/                   # Spring Data JPA Repos
│       │   ├── UserRepository.java
│       │   ├── RoleRepository.java
│       │   ├── MealRepository.java
│       │   ├── WorkoutRepository.java
│       │   ├── DailyProgressRepository.java
│       │   ├── MealTrackingRepository.java
│       │   ├── WorkoutTrackingRepository.java
│       │   └── RewardRepository.java
│       ├── security/
│       │   ├── JwtTokenProvider.java
│       │   ├── JwtAuthenticationFilter.java
│       │   ├── JwtAuthenticationEntryPoint.java
│       │   └── CustomUserDetailsService.java
│       └── service/
│           ├── AuthService.java
│           ├── UserService.java
│           ├── MealService.java
│           ├── WorkoutService.java
│           ├── ProgressService.java
│           └── AdminService.java
└── frontend/                   # Angular Application
    ├── package.json
    ├── angular.json
    ├── tsconfig.json
    └── src/
        ├── index.html
        ├── main.ts
        ├── styles.scss
        ├── environments/
        │   ├── environment.ts
        │   └── environment.prod.ts
        └── app/
            ├── app.component.ts
            ├── app.config.ts
            ├── app.routes.ts
            ├── models/models.ts
            ├── guards/
            │   ├── auth.guard.ts
            │   └── admin.guard.ts
            ├── interceptors/
            │   └── auth.interceptor.ts
            ├── services/
            │   ├── auth.service.ts
            │   ├── user.service.ts
            │   └── admin.service.ts
            ├── components/
            │   └── navbar/navbar.component.ts
            └── pages/
                ├── landing/landing.component.ts
                ├── login/login.component.ts
                ├── register/register.component.ts
                ├── dashboard/dashboard.component.ts
                ├── profile/profile.component.ts
                ├── history/history.component.ts
                └── admin/admin.component.ts
```

---

## Setup & Running Instructions

### Prerequisites
- Java 17+
- Node.js 18+ & npm
- MySQL 8.0+
- Maven 3.8+

### 1. Database Setup
```sql
-- Run the schema file:
mysql -u root -p < database/schema.sql
```
This creates the `diet_fitness_planner` database, tables, and inserts sample meal/workout data.

### 2. Backend Setup
```bash
cd backend

# Update src/main/resources/application.properties with your MySQL credentials:
# spring.datasource.username=your_username
# spring.datasource.password=your_password

# Build and run
mvn clean install
mvn spring-boot:run
```
Backend runs on `http://localhost:8080`

### 3. Frontend Setup
```bash
cd frontend
npm install
ng serve
```
Frontend runs on `http://localhost:4200`

---

## API Endpoints

### Public (No Auth Required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login, returns JWT |

### User (JWT Required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/user/dashboard` | Full dashboard data |
| GET | `/api/user/profile` | Get user profile |
| PUT | `/api/user/profile` | Update profile |
| GET | `/api/user/meals` | Today's meal plan |
| POST | `/api/user/meals/{id}/toggle` | Toggle meal completion |
| GET | `/api/user/workouts` | Today's workout plan |
| POST | `/api/user/workouts/{id}/toggle` | Toggle workout completion |
| GET | `/api/user/rewards` | Reward & streak info |
| POST | `/api/user/progress/complete-day` | Complete day & calc points |
| GET | `/api/user/history` | Last 10 days history |
| GET | `/api/user/wellness-tips` | Water/sleep tips |

### Admin (JWT + ROLE_ADMIN Required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/admin/users` | List all users |
| GET | `/api/admin/users/{id}/report` | User report |
| GET | `/api/admin/meals` | List all meals |
| POST | `/api/admin/meals` | Add new meal |
| GET | `/api/admin/workouts` | List all workouts |
| POST | `/api/admin/workouts` | Add new workout |

---

## Key Features Implemented

1. **Landing Page** - Hero section + feature cards with CTA
2. **User Registration** - Full validation (frontend + backend), BCrypt encryption
3. **JWT Login** - Token-based auth, auto-attached via HTTP interceptor
4. **Dashboard** - BMI calculation, split-screen meals/workouts, rewards, wellness tips
5. **Meal Plan** - Personalized by diet preference + goal, checkbox tracking
6. **Workout Plan** - Personalized by level + goal, checkbox tracking
7. **Progress Tracking** - Daily completion, stored per user per day
8. **Reward System** - Points (+1/day, +5 bonus/10-day streak, -2 break), 4 badge tiers
9. **Wellness Tips** - Dynamic water intake (based on weight) + sleep suggestions
10. **History** - Last 10 days with meals, workouts, points, streak status
11. **User Profile** - View/edit height, weight, preferences, goal
12. **Admin Panel** - Tabbed view: user list, add meals, add workouts, view all data

---

## Security
- Passwords encrypted with BCrypt
- JWT tokens with HMAC-SHA256 signing
- Role-based access control (ROLE_USER, ROLE_ADMIN)
- CORS configured for Angular dev server
- Input validation on both frontend (reactive forms) and backend (Jakarta Validation)
- Stateless session management
