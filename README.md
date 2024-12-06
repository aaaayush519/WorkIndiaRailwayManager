# WorkIndia Railway Manager

## Setup Instructions

1. Clone the repository.
2. Configure the database in `application.properties`.
3. Run `schema.sql` and `data.sql` to set up the database.
4. Start the application using `mvn spring-boot:run`.

## Endpoints

- **Admin**
  - `POST /api/admin/train`: Add train (requires API key).
- **User**
  - `GET /api/user/trains`: Get train availability.
  - `POST /api/user/book`: Book a train seat (requires JWT token).
