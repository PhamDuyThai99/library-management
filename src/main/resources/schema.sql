-- Create users table
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    role VARCHAR(250) NOT NULL CHECK(role IN ('ROLE_MEMBER', 'ROLE_ADMIN'))
);

-- Create book table
CREATE TABLE books(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(250) NOT NULL,
    author VARCHAR(250) NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

-- Create borrow record table
CREATE TABLE borrow_records(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrow_date TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP DEFAULT NULL,
    fine_amount DOUBLE DEFAULT 0.0,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
)