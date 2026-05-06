# WMS SaaS - Warehouse Management System

A production-ready multi-tenant SaaS Warehouse Management System designed for Indian small-to-medium warehouses.

## 🏗️ Architecture Overview

```
┌─────────────────────────────────────┐
│        React.js Frontend            │
│    (Protected Routes, Auth State)    │
└──────────────┬──────────────────────┘
               │ REST APIs (Axios)
┌──────────────▼──────────────────────┐
│     Spring Boot Backend              │
│  (JWT, Role-based Authorization)     │
└──────────────┬──────────────────────┘
               │ JPA/Hibernate
┌──────────────▼──────────────────────┐
│     PostgreSQL Database              │
│   (Multi-tenant, Isolated Data)      │
└─────────────────────────────────────┘
```

## 📋 Features

### Authentication
- Email/Password login & signup
- Google OAuth integration
- JWT-based authentication
- Phone number validation (Indian format)
- Secure password hashing with bcrypt

### Multi-Tenant SaaS
- Complete data isolation per user
- Warehouse-level segregation
- Secure API authorization

### Inventory Management
- Transaction-based stock tracking
- Company/Supplier management
- Product management with categories
- Stock movement history
- Low stock alerts

### Sales & Purchase
- Purchase order management
- Sales tracking
- Delivery management (Incoming & Outgoing)
- Order status tracking

### Dashboard
- Real-time inventory analytics
- Sales trends
- Purchase analytics
- Top products
- Profit/Loss calculations

### Notifications
- Email notifications
- SMS notification structure
- Delivery updates
- Stock alerts

## 🛠️ Tech Stack

**Backend:**
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (io.jsonwebtoken)
- PostgreSQL/MySQL
- Lombok
- MapStruct
- Springdoc OpenAPI (Swagger)

**Frontend:**
- React.js 18+
- React Router v6
- Redux Toolkit (State Management)
- Axios (HTTP Client)
- React Query (Data Fetching)
- TailwindCSS (Styling)
- Recharts (Analytics)
- React Hook Form (Forms)

**DevOps:**
- Docker & Docker Compose
- GitHub Actions (CI/CD)
- AWS/Azure ready

## 📁 Project Structure

```
wms-saas/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/wms/
│   │   │   │   ├── config/          # Configuration classes
│   │   │   │   ├── controller/      # REST Controllers
│   │   │   │   ├── service/         # Business logic
│   │   │   │   ├── repository/      # Data access layer
│   │   │   │   ├── entity/          # JPA Entities
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── security/        # JWT, Security configs
│   │   │   │   ├── exception/       # Custom exceptions
│   │   │   │   ├── mapper/          # Entity to DTO mappers
│   │   │   │   └── WmsApplication.java
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       ├── application-dev.yml
│   │   │       ├── application-prod.yml
│   │   │       └── db/migration/    # Liquibase/Flyway migrations
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
│
├── frontend/
│   ├── src/
│   │   ├── pages/               # Page components
│   │   ├── components/          # Reusable components
│   │   ├── store/               # Redux store
│   │   ├── services/            # API services
│   │   ├── utils/               # Utility functions
│   │   ├── hooks/               # Custom hooks
│   │   ├── types/               # TypeScript types
│   │   ├── styles/              # Global styles
│   │   └── App.jsx
│   ├── public/
│   ├── package.json
│   └── Dockerfile
│
├── docker-compose.yml
├── .github/
│   └── workflows/
│       ├── backend-ci.yml
│       └── frontend-ci.yml
└── docs/
    ├── API.md
    ├── DATABASE.md
    ├── DEPLOYMENT.md
    └── DEVELOPMENT.md
```

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Node.js 18+
- PostgreSQL 13+
- Git

### Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend runs on `http://localhost:8080`

### Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend runs on `http://localhost:3000`

### Docker Compose

```bash
docker-compose up
```

Starts:
- PostgreSQL on port 5432
- Spring Boot on port 8080
- React on port 3000

## 🔐 Security Features

- JWT Token-based authentication
- Role-based access control (RBAC)
- Password hashing with bcrypt
- CORS configuration
- SQL injection prevention via parameterized queries
- Input validation on all endpoints
- Rate limiting structure
- Secure environment variable handling
- HTTPS ready for production

## 💾 Database Schema

### Core Entities
- **User** - Authentication & multi-tenancy root
- **Warehouse** - User's warehouse entity
- **Company** - Suppliers/Vendors
- **Product** - Inventory items
- **InventoryTransaction** - Stock movement tracking
- **Purchase** - Purchase orders
- **Sale** - Sales transactions
- **Delivery** - Incoming/Outgoing deliveries
- **Notification** - User notifications

## 📊 API Endpoints

### Authentication
- `POST /api/v1/auth/register` - User signup
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/google` - Google OAuth
- `POST /api/v1/auth/refresh` - Token refresh

### Warehouse
- `GET /api/v1/warehouses` - List warehouses
- `POST /api/v1/warehouses` - Create warehouse
- `PUT /api/v1/warehouses/{id}` - Update warehouse

### Inventory
- `GET /api/v1/companies` - List companies
- `POST /api/v1/companies` - Create company
- `GET /api/v1/products` - List products
- `POST /api/v1/products` - Create product
- `GET /api/v1/inventory/transactions` - Transaction history

### Sales & Purchase
- `POST /api/v1/purchases` - Create purchase
- `POST /api/v1/sales` - Create sale
- `GET /api/v1/deliveries` - List deliveries
- `POST /api/v1/deliveries` - Create delivery

### Dashboard
- `GET /api/v1/dashboard/stats` - Dashboard statistics
- `GET /api/v1/dashboard/charts` - Chart data

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## 📈 Scalability & Future Features

The architecture supports future integrations:
- Barcode/QR code scanning
- Invoice generation with GST
- AI-based inventory forecasting
- Multi-warehouse management
- Advanced reporting & BI
- Mobile app (React Native)
- Payment gateway integration
- Webhook notifications

## 🌍 Localization

- **Currency:** Indian Rupees (₹)
- **Timezone:** IST (UTC+5:30)
- **Language:** English
- **Phone:** Indian format (+91 XXXXX XXXXX)

## 📝 Environment Variables

Create `.env` files:

**backend/.env**
```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/wms_db
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=password
JWT_SECRET=your-secret-key-min-32-chars
JWT_EXPIRATION=86400000
GOOGLE_CLIENT_ID=your-google-client-id
```

**frontend/.env**
```
REACT_APP_API_BASE_URL=http://localhost:8080/api/v1
REACT_APP_GOOGLE_CLIENT_ID=your-google-client-id
```

## 🚢 Deployment

### AWS
- RDS for PostgreSQL
- EC2 or ECS for backend
- S3 + CloudFront for frontend
- ALB for load balancing

### Azure
- Azure Database for PostgreSQL
- App Service for backend
- Static Web Apps for frontend
- Application Gateway

## 📚 Documentation

- [API Documentation](docs/API.md)
- [Database Schema](docs/DATABASE.md)
- [Deployment Guide](docs/DEPLOYMENT.md)
- [Development Setup](docs/DEVELOPMENT.md)

## 📄 License

MIT License - See LICENSE file

## 🤝 Contributing

See CONTRIBUTING.md for guidelines

## 📞 Support

For issues and questions, please create a GitHub issue.

---

**Version:** 1.0.0  
**Last Updated:** 2026-05-06  
**Status:** Production Ready