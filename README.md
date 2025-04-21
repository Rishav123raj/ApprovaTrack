# ApprovaTrack - Department Budget Approval System

This is a Spring Boot-based RESTful application for managing budget requests submitted by departments. It enables authorized managers to approve or reject requests, validates constraints such as maximum allowed percentages, and maintains an audit log of all critical actions.

---

## 📌 Features

- Submit, approve, and reject budget requests
- Role-based access (MANAGER can approve/reject)
- Validation rules (10% limit, duplicate requests, positive amounts)
- Audit logging of every approval/rejection
- REST API endpoints
- In-memory authentication
- Unit and integration tests
- (Optional) Email notifications and caching

---

## 🚀 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Web, Spring Data JPA, Spring Security, Spring Validation
- H2 Database (in-memory)
- JavaMailSender (optional)
- JUnit 5 for testing

---

## 🛠️ Project Setup Instructions

### ✅ Prerequisites

- Java 17+
- Maven 3.6+
- Git (optional for cloning)

### 📥 Clone the Repository

```bash
git clone https://github.com/Rishav123raj/ApprovaTrack.git
cd ApprovaTrack
```

⚙️ Build and Run the Project
# Build the project
mvn clean install

# Run the Spring Boot application
mvn spring-boot:run
The application will start on http://localhost:8080

🧪 API Endpoints
Budget Requests

Method	Endpoint	Description
POST	/api/budget-request	Submit a new request
PUT	/api/budget-request/{id}/approve	Approve request (MANAGER only)
PUT	/api/budget-request/{id}/reject	Reject request (MANAGER only)
GET	/api/budget-request/pending	List all pending requests
Audit Logs

Method	Endpoint	Description
GET	/api/audit-logs?entityType=BudgetRequest	Get audit trail records
✅ Validation Rules
✅ Requested amount must be positive

✅ No duplicate requests for the same purpose within 7 days

✅ Request must not exceed 10% of department’s yearly allocation

✅ Only users with the MANAGER role can approve/reject

🧠 Assumptions
The application uses in-memory authentication for simplicity

User roles are not stored in a database

The Department entity is assumed to be pre-populated

# 🗂️ Future Enhancements
JWT-based authentication

UI dashboard for visualization

Role management with persistence

Better audit diff tracking

Deployment on cloud platforms
